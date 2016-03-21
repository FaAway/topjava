package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;
import java.util.Collection;

/**
 * GKislin
 * 06.03.2015.
 */
public interface UserMealRepository {
    UserMeal save(int userId, UserMeal userMeal);

    // false if not found
    boolean delete(int userId, int id);

    UserMeal get(int userId, int id);

    Collection<UserMeal> getBetween(int userId, LocalDateTime fromDate, LocalDateTime toDate);

    Collection<UserMeal> getAll(int userId);

}
