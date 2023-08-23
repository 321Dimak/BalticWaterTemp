package lv.startup.BalticWaterTemp.core.database;

import lv.startup.BalticWaterTemp.core.entity.Stations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationsRepository extends JpaRepository<Stations, String> {
}
