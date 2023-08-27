package lv.startup.BalticWaterTemp.core.services;

import jakarta.mail.internet.MimeMessage;
import lv.startup.BalticWaterTemp.core.database.LocationRepository;
import lv.startup.BalticWaterTemp.core.database.NotificationRepository;
import lv.startup.BalticWaterTemp.core.database.UserRepository;
import lv.startup.BalticWaterTemp.core.dto.LevelThresholdDTO;
import lv.startup.BalticWaterTemp.core.dto.TempThresholdDTO;
import lv.startup.BalticWaterTemp.core.entity.Location;
import lv.startup.BalticWaterTemp.core.entity.Notification;
import lv.startup.BalticWaterTemp.core.entity.User;
import lv.startup.BalticWaterTemp.core.exceptions.EntityNotFoundException;
import lv.startup.BalticWaterTemp.core.responses.SaveTempNotificationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

@Service
public class ThresholdSetService {

    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private ApiService apiService;

    @Autowired
    private JavaMailSender mailSender;

    private void sendTemperatureAlertEmail(String userEmail, String locationId, double clientTemperature) {
        try {
            ResponseEntity<String> response = apiService.fetchTempFromApi(locationId);
            JSONObject jsonObject = new JSONObject(response.getBody());
            JSONArray records = jsonObject.getJSONArray("records");
            double apiTemperature = 0.0;
            if (records.length() > 0) {
                JSONObject firstRecord = records.getJSONObject(0);
                apiTemperature = firstRecord.getDouble("VALUE");
            }

            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            String alertType = clientTemperature > apiTemperature ? "high" : "low";
            String htmlMsg = "<h3>Temperature Alert!</h3><p>The temperature " + clientTemperature + "°C is " + alertType + ".</p>";
            helper.setText(htmlMsg, true);
            helper.setTo(userEmail);
            helper.setSubject("Temperature Alert from BalticWaterTemp");
            helper.setFrom("balticwatertemperature@gmail.com");
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public SaveTempNotificationResponse setTempThreshold(TempThresholdDTO dto) {
        User user = userRepository.findByEmail(dto.getUserEmail());
        Location location = locationRepository.findByLocationId(dto.getLocationId());
        if (user == null) {
            throw new EntityNotFoundException("User not found");
        }
        if (location == null) {
            throw new EntityNotFoundException("Location not found");
        }
        Notification existingNotification = notificationRepository.findById(dto.toNotificationKey()).orElse(null);
        if (existingNotification != null) {
            existingNotification.setTemperature(dto.getTemperature());
            notificationRepository.save(existingNotification);
        } else {
            notificationRepository.save(new Notification(dto.toNotificationKey(), dto.getTemperature(), 999999.9, user, location));
        }
        return new SaveTempNotificationResponse("Success");
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void resetTemperatureAlerts() {
        List<Notification> notifications = notificationRepository.findAll();
        for (Notification notification : notifications) {
            notification.setTempLowerAlert(false);
            notification.setTempHigherAlert(false);
            notificationRepository.save(notification);
        }
    }

    @Scheduled(fixedRate = 14400000)
    public void checkTemperatures() {
        List<Notification> notifications = notificationRepository.findAll();
        for (Notification notification : notifications) {
            if (!notification.isTempLowerAlert() && !notification.isTempHigherAlert()) {
                ResponseEntity<String> response = apiService.fetchTempFromApi(notification.getId().getLocationId());
                JSONObject jsonObject = new JSONObject(response.getBody());
                JSONArray records = jsonObject.getJSONArray("records");
                double apiTemperature = 0.0;
                if (records.length() > 0) {
                    JSONObject firstRecord = records.getJSONObject(0);
                    apiTemperature = firstRecord.getDouble("VALUE");
                }
                double clientTemperature = notification.getTemperature();
                if (clientTemperature > apiTemperature) {
                    notification.setTempHigherAlert(true);
                    sendTemperatureAlertEmail(notification.getId().getUserEmail(), notification.getId().getLocationId(), clientTemperature);
                } else if (clientTemperature < apiTemperature) {
                    notification.setTempLowerAlert(true);
                    sendTemperatureAlertEmail(notification.getId().getUserEmail(), notification.getId().getLocationId(), clientTemperature);
                }
                notificationRepository.save(notification);
            }
        }
    }
public void setLevelThreshold(LevelThresholdDTO dto) {
        User user = userRepository.findByEmail(dto.getUserEmail());
        Location location = locationRepository.findByLocationId(dto.getLocationId());
        if (user == null) {
            throw new EntityNotFoundException("User not found");
        }
        if (location == null) {
            throw new EntityNotFoundException("Location not found");
        }
        Notification existingNotification = notificationRepository.findByUserEmailAndLocationId(dto.getUserEmail(), dto.getLocationId());
        if (existingNotification != null) {
            existingNotification.setLevel(dto.getLevel());
            notificationRepository.save(existingNotification);
        } else {
            notificationRepository.save(new Notification(dto.toNotificationKey(), 999999999.9, dto.getLevel(), user, location));
        }
    }

}
