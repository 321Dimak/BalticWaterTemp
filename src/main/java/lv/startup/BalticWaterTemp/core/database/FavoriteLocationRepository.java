package lv.startup.BalticWaterTemp.core.database;

import lv.startup.BalticWaterTemp.core.entity.FavoriteLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteLocationRepository extends JpaRepository<FavoriteLocation, String> {
}
