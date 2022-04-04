package tudor.training.spring.boot.lab.lab.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import tudor.training.spring.boot.lab.lab.models.Person;

import java.util.List;

public interface PersonRepo extends JpaRepository<Person,Integer> {
    List<Person> findByLastName(String lastName);
}
