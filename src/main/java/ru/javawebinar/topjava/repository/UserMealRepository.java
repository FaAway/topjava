package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.UserMeal;

import java.util.Collection;

/**
 * Created by FarAway on 13.03.2016.
 */
public interface UserMealRepository {
    UserMeal save(UserMeal meal);
    void delete(int id);
    UserMeal get(int id);
    Collection<UserMeal> getAll();

}
