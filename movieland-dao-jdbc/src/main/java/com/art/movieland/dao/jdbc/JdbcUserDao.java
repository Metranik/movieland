package com.art.movieland.dao.jdbc;

import com.art.movieland.dao.UserDao;
import com.art.movieland.dao.jdbc.mapper.UserRowMapper;
import com.art.movieland.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcUserDao implements UserDao {
    private static final String GET_USER_BY_ID = "SELECT u.id, u.name, u.email, u.nick " +
            "FROM users u " +
            "WHERE id = ?";
    private static final UserRowMapper USER_ROW_MAPPER = new UserRowMapper();

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcUserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User getById(int id) {
        return jdbcTemplate.queryForObject(GET_USER_BY_ID, USER_ROW_MAPPER, id);
    }
}
