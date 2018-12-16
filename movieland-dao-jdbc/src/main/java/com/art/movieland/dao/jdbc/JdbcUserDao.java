package com.art.movieland.dao.jdbc;

import com.art.movieland.dao.UserDao;
import com.art.movieland.dao.jdbc.mapper.UserRowMapper;
import com.art.movieland.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JdbcUserDao implements UserDao {
    private static final String GET_USER_BY_ID = "SELECT u.id, u.name, u.email, u.password, u.role " +
            "FROM users u " +
            "WHERE id = ?";
    private static final String GET_USER_BY_EMAIL = "SELECT u.id, u.name, u.email, u.password, u.role " +
            "FROM users u " +
            "WHERE u.email = ?";
    private static final UserRowMapper USER_ROW_MAPPER = new UserRowMapper();

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcUserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User getById(int id) {
        return jdbcTemplate.queryForObject(GET_USER_BY_ID, USER_ROW_MAPPER, id);
    }

    @Override
    public Optional<User> getByEmail(String email) {
        try {
            User user = jdbcTemplate.queryForObject(GET_USER_BY_EMAIL, USER_ROW_MAPPER, email);
            return Optional.of(user);
        } catch (EmptyResultDataAccessException e) {
            logger.error("There is no such a user. ", e);
            return Optional.empty();
        }
    }
}
