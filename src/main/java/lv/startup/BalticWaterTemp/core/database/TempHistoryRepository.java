package lv.startup.BalticWaterTemp.core.database;

import lv.startup.BalticWaterTemp.core.entity.TempHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TempHistoryRepository extends JpaRepository<TempHistory, String> {
}
