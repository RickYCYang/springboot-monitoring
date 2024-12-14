package net.javaguides.springboot.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import net.javaguides.springboot.entity.User;


public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findByEmail(String email);
}
