package lv.startup.BalticWaterTemp.repository;

import lv.startup.BalticWaterTemp.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
