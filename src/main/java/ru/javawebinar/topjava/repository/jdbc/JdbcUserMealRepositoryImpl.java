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

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private DataSource dataSource;

    private SimpleJdbcInsert insertMeal;
    private SimpleJdbcInsert insertUserMealLink;

    @PostConstruct
    public void postConstruct() {
        this.insertMeal = new SimpleJdbcInsert(dataSource)
                .withTableName("MEALS")
                .usingGeneratedKeyColumns("id");
        this.insertUserMealLink = new SimpleJdbcInsert(dataSource)
                .withTableName("USERS_MEALS");
    }

    @Override
    public UserMeal save(UserMeal userMeal, int userId) {
        Objects.requireNonNull(userMeal);
        MapSqlParameterSource mealsMap = new MapSqlParameterSource()
                .addValue("id", userMeal.getId())
                .addValue("date_time", Timestamp.valueOf(userMeal.getDateTime()))
                .addValue("description", userMeal.getDescription())
                .addValue("calories", userMeal.getCalories());
        if (userMeal.isNew()) {
            Number newKey = insertMeal.executeAndReturnKey(mealsMap);
            userMeal.setId(newKey.intValue());
            MapSqlParameterSource userMealMap = new MapSqlParameterSource()
                    .addValue("user_id", userId)
                    .addValue("meal_id", userMeal.getId());
            insertUserMealLink.execute(userMealMap);
        } else {
            namedParameterJdbcTemplate.update(
                    "UPDATE meals SET date_time=:date_time, description=:description, calories=:calories" +
                    " WHERE id=:id", mealsMap);
        }
        return userMeal;
    }

    @Override
    public boolean delete(int id, int userId) {
        return jdbcTemplate.update("DELETE FROM meals WHERE id=?", id) != 0;
    }

    @Override
    public UserMeal get(int id, int userId) {
        Collection<UserMeal> meals = jdbcTemplate.query("SELECT * FROM meals WHERE id=?", ROW_MAPPER, id);
        return DataAccessUtils.singleResult(meals);
    }

    @Override
    public List<UserMeal> getAll(int userId) {
        return jdbcTemplate.query("SELECT * FROM meals WHERE id IN (SELECT meal_id FROM users_meals WHERE user_id=?) ORDER BY date_time DESC", ROW_MAPPER, userId);
    }

    @Override
    public List<UserMeal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        Objects.requireNonNull(startDate);
        Objects.requireNonNull(endDate);
        return jdbcTemplate.query("SELECT * FROM meals WHERE date_time BETWEEN ? AND ? AND id IN (SELECT meal_id FROM users_meals WHERE user_id=?) ORDER BY date_time DESC ", ROW_MAPPER, Timestamp.valueOf(startDate), Timestamp.valueOf(endDate), userId);
    }
}
