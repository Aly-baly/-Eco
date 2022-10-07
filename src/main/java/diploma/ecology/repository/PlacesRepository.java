package diploma.ecology.repository;

import diploma.ecology.models.Places;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlacesRepository extends JpaRepository<Places, Integer> {

    List<Places> findByWorked(boolean worked);

    List<Places> findByNameContaining(String title);


}
