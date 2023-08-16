package lv.startup.BalticWaterTemp.repository;

import lv.startup.BalticWaterTemp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
