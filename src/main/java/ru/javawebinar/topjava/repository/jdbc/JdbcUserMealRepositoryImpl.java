package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * User: gkislin
 * Date: 26.08.2014
 */

@Repository
public class JdbcUserMealRepositoryImpl implements UserMealRepository {
    private static final BeanPropertyRowMapper<UserMeal> ROW_MAPPER = BeanPropertyRowMapper.newInstance(UserMeal.class);
    /*private static final RowMapper<UserMeal> ROW_MAPPER = ((rs, rowNum) -> //All work without custom RowMapper
        new UserMeal(rs.getInt("id"), rs.getTimestamp("date_time").toLocalDateTime(),
                rs.getString("description"), rs.getInt("calories"))
    );*/

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private DataSource dataSource;

    private SimpleJdbcInsert insertMeal;

    @PostConstruct
    public void postConstruct() {
        this.insertMeal = new SimpleJdbcInsert(dataSource)
                .withTableName("MEALS")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public UserMeal save(UserMeal userMeal, int userId) {
        Objects.requireNonNull(userMeal);
        MapSqlParameterSource mealsMap = new MapSqlParameterSource()
                .addValue("id", userMeal.getId())
                .addValue("date_time", Timestamp.valueOf(userMeal.getDateTime()))
                .addValue("description", userMeal.getDescription())
                .addValue("calories", userMeal.getCalories())
                .addValue("user_id", userId);
        if (userMeal.isNew()) {
            Number newKey = insertMeal.executeAndReturnKey(mealsMap);
            userMeal.setId(newKey.intValue());
        } else {
            namedParameterJdbcTemplate.update(
                    "UPDATE meals SET date_time=:date_time, description=:description, calories=:calories" +
                    " WHERE id=:id AND user_id=:user_id", mealsMap);
        }
        return userMeal;
    }

    @Override
    public boolean delete(int id, int userId) {
        return jdbcTemplate.update("DELETE FROM meals WHERE id=? AND user_id=?", id, userId) != 0;
    }

    @Override
    public UserMeal get(int id, int userId) {
        Collection<UserMeal> meals = jdbcTemplate.query("SELECT * FROM meals WHERE id=? AND user_id=?", ROW_MAPPER, id, userId);
        return DataAccessUtils.singleResult(meals);
    }

    @Override
    public List<UserMeal> getAll(int userId) {
        return jdbcTemplate.query("SELECT * FROM meals WHERE user_id=? ORDER BY date_time DESC", ROW_MAPPER, userId);
    }

    @Override
    public List<UserMeal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        Objects.requireNonNull(startDate);
        Objects.requireNonNull(endDate);
        return jdbcTemplate.query("SELECT * FROM meals WHERE date_time BETWEEN ? AND ? AND user_id=?) ORDER BY date_time DESC ", ROW_MAPPER, Timestamp.valueOf(startDate), Timestamp.valueOf(endDate), userId);
    }
}
