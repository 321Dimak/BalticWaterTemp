package lv.startup.BalticWaterTemp.core.database;

import lv.startup.BalticWaterTemp.core.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaUserRepository extends JpaRepository<User, String> {
}
