package work365.work.dto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import work365.work.model.Role;
@Repository
public interface RoleDto extends JpaRepository<Role,String> {
    Role findByRoleName(String roleName);
}
