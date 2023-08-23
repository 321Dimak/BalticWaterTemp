package lv.startup.BalticWaterTemp.core.database;

import lv.startup.BalticWaterTemp.core.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, String> {

    @Query("SELECT l FROM Location l WHERE l.id = :locationId")
    Location findByLocationId(@Param("locationId") String locationId);
}
