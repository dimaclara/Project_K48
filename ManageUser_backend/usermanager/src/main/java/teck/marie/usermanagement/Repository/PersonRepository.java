package teck.marie.usermanagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import teck.marie.usermanagement.Entity.Person;


public interface PersonRepository extends JpaRepository<Person, Long> {
}
