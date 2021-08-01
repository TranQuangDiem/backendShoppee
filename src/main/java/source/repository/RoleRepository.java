package source.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import source.entity.Role;
@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findById(long id);
}
