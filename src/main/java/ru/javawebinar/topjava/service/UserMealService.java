package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;

/**
 * GKislin
 * 15.06.2015.
 */
public interface UserMealService {
    UserMeal save(int userId, UserMeal userMeal);

    UserMeal update(int userId, UserMeal userMeal);

    void delete(int userId, int id) throws NotFoundException;

    UserMeal get(int userId, int id) throws NotFoundException;

    Collection<UserMeal> getAll(int userId);

    Collection<UserMeal> getBetween(int userId, LocalDateTime fromDate, LocalDateTime toDate);

    default Collection<UserMeal> getBetweenDates(int userId, LocalDate fromDate, LocalDate toDate) {
        return getBetween(userId, LocalDateTime.of(fromDate, LocalTime.MIN), LocalDateTime.of(toDate, LocalTime.MAX));
    }
}
