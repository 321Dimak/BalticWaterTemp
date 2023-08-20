package lv.startup.BalticWaterTemp.core.database;

import lv.startup.BalticWaterTemp.core.entity.LevelHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LevelHistoryRepository extends JpaRepository<LevelHistory, String> {
}
