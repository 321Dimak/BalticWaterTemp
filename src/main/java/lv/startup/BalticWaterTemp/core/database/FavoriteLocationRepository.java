package lv.startup.BalticWaterTemp.core.database;

import lv.startup.BalticWaterTemp.core.entity.FavoriteLocation;
import lv.startup.BalticWaterTemp.core.entity.FavoriteLocationKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteLocationRepository extends JpaRepository<FavoriteLocation, FavoriteLocationKey> {

    @Modifying
    @Query("DELETE FROM FavoriteLocation f WHERE f.id.userEmail = :userEmail AND f.id.locationId = :locationId")
    void deleteByUserEmailAndLocationId(@Param("userEmail") String userEmail, @Param("locationId") String locationId);

    List<FavoriteLocation> findByUserEmail(String userEmail);
}
