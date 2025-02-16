package net.javaguides.springboot.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import net.javaguides.springboot.entity.User;


public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findByEmail(String email);

    @Query(value = "SELECT * FROM users WHERE id = :id", nativeQuery = true)
    public User findByIdSql(Long id);

    @Query(value = "SELECT * FROM users WHERE email like CONCAT('%', :email, '%')",
            nativeQuery = true)
    public User findByEmailSql(String email);
}
