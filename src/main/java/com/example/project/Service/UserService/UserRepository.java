package com.example.project.Service.UserService;

import com.example.project.Model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static class UserMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new User(
                    rs.getInt("user_id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("role"),
                    rs.getBoolean("enabled")
            );
        }
    }

    public Optional<User> findByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        return jdbcTemplate.query(sql, new UserMapper(), username).stream().findFirst();
    }

    public int save(User user) {
        String sql = "INSERT INTO users (username, password, role, enabled) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getRole(), user.isEnabled());
    }
}
