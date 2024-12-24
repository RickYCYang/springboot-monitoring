package net.javaguides.springboot.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import net.javaguides.springboot.entity.User;

@Component
public class UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public User findById(long id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[] {id},
                new BeanPropertyRowMapper<>(User.class));

    }
}
