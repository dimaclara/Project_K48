package teck.marie.usermanagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import teck.marie.usermanagement.Entity.Users;

public interface UserRepository extends JpaRepository<Users, Integer> {
    Users findByEmail(String email);
}
