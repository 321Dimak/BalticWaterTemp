package lv.startup.BalticWaterTemp.core.services;

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
import org.springframework.stereotype.Service;

@Service
public class ThresholdSetService {

    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LocationRepository locationRepository;

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
