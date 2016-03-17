package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDate;
import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
public interface UserMealRepository {
    UserMeal save(int userId, UserMeal userMeal);

    // false if not found
    boolean delete(int userId, int id);

    UserMeal get(int userId, int id);

    List<UserMeal> getAll(int userId);

    List<UserMeal> getAllBetweenDates(int userId, LocalDate fromDate, LocalDate toDate);
}
