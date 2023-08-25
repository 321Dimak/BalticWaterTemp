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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThresholdSetService {

    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LocationRepository locationRepository;
    private final double PREDEFINED_TEMPERATURE = 20.0;//method for fetching

    @Autowired
    private JavaMailSender mailSender;

    private void sendTemperatureAlertEmail(String userEmail, double clientTemperature) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            String alertType = clientTemperature > PREDEFINED_TEMPERATURE ? "high" : "low";
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

    public void setTempThreshold(TempThresholdDTO dto) {
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
            existingNotification.setTemperature(dto.getTemperature());
            notificationRepository.save(existingNotification);
        } else {
            notificationRepository.save(new Notification(dto.getUserEmail(), dto.getLocationId(), dto.getTemperature(), dto.getLevel(), user, location));
        }
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
                double clientTemperature = notification.getTemperature();
                if (clientTemperature > PREDEFINED_TEMPERATURE) {
                    notification.setTempHigherAlert(true);
                    sendTemperatureAlertEmail(notification.getUserEmail(), clientTemperature);
                } else if (clientTemperature < PREDEFINED_TEMPERATURE) {
                    notification.setTempLowerAlert(true);
                    sendTemperatureAlertEmail(notification.getUserEmail(), clientTemperature);
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
            notificationRepository.save(new Notification(dto.getUserEmail(), dto.getLocationId(), dto.getTemperature(), dto.getLevel(), user, location));
        }
    }

}
