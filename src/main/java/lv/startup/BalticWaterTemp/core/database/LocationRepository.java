package lv.startup.BalticWaterTemp.core.database;

import lv.startup.BalticWaterTemp.core.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, String> {

    Location findByLocationId(String id);
}
