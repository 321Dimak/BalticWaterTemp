package lv.startup.BalticWaterTemp.core.database;

import lv.startup.BalticWaterTemp.core.entity.FavoriteLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteLocationRepository extends JpaRepository<FavoriteLocation, String> {

    @Modifying
    @Query("DELETE FROM FavoriteLocation f WHERE f.userEmail = :userEmail AND f.locationId = :locationId")
    void deleteByUserEmailAndLocationId(@Param("userEmail") String userEmail, @Param("locationId") String locationId);

    List<FavoriteLocation> findByUserEmail(String userEmail);
}
