package lv.startup.BalticWaterTemp.core.services;

import lv.startup.BalticWaterTemp.core.database.FavoriteLocationRepository;
import lv.startup.BalticWaterTemp.core.database.LocationRepository;
import lv.startup.BalticWaterTemp.core.database.UserRepository;
import lv.startup.BalticWaterTemp.core.dto.FavoriteLocationDTO;
import lv.startup.BalticWaterTemp.core.entity.FavoriteLocation;
import lv.startup.BalticWaterTemp.core.entity.Location;
import lv.startup.BalticWaterTemp.core.entity.User;
import lv.startup.BalticWaterTemp.core.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteLocationService {

    @Autowired
    private FavoriteLocationRepository favoriteLocationRepository;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private UserRepository userRepository;

    public void saveFavoriteLocation(FavoriteLocationDTO dto) {
        User user = userRepository.findByEmail(dto.getUserEmail());
        Location location = locationRepository.findByLocationId(dto.getLocationId());
        if (user == null) {
            throw new EntityNotFoundException("User not found");
        }
        if (location == null) {
            throw new EntityNotFoundException("Location not found");
        }
        favoriteLocationRepository.save(new FavoriteLocation(dto.toFavoriteLocationId(), user, location));
    }

    public void deleteFavoriteLocation(FavoriteLocationDTO dto) {
        favoriteLocationRepository.deleteByUserEmailAndLocationId(dto.getUserEmail(), dto.getLocationId());
    }

    public List<FavoriteLocation> findByUserEmail(String userEmail) {
        return favoriteLocationRepository.findByUserEmail(userEmail);
    }
}
