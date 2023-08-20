package lv.startup.BalticWaterTemp.core.database;

import lv.startup.BalticWaterTemp.core.entity.Stations;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StationsRepository extends JpaRepository<Stations, String> {
}
