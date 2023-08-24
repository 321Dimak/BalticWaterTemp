package lv.startup.BalticWaterTemp.core.services;

import lv.startup.BalticWaterTemp.core.database.LocationRepository;
import lv.startup.BalticWaterTemp.core.entity.Location;
import lv.startup.BalticWaterTemp.core.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    public String findLocationNameById(String locationId) {
        Location location = locationRepository.findById(locationId).orElse(null);
        return location != null ? location.getName() : "Unknown Location: " + location.getId();
    }

    public Location findLocationById(String locationId) {
        return locationRepository.findById(locationId)
                .orElseThrow(() -> new EntityNotFoundException("Location with ID " + locationId + " not found"));
    }
}
