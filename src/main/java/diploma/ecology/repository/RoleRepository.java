package diploma.ecology.repository;

import java.util.Optional;

import diploma.ecology.models.ERole;
import diploma.ecology.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}