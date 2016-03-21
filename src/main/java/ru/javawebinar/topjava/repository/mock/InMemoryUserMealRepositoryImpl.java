package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.TimeUtil;
import ru.javawebinar.topjava.util.UserMealsUtil;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * GKislin
 * 15.09.2015.
 */

@Repository
public class InMemoryUserMealRepositoryImpl implements UserMealRepository {
    private Map<Integer, Map<Integer, UserMeal>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        UserMealsUtil.MEAL_LIST.forEach(meal -> this.save(1, meal));
        UserMealsUtil.MEAL_LIST_2.forEach(meal -> this.save(2, meal));
    }

    @Override
    public UserMeal save(int userId, UserMeal userMeal) {
        if (userMeal.isNew()) {
            userMeal.setId(counter.incrementAndGet());
        } else if (get(userId, userMeal.getId()) == null)
            return null;
        Map<Integer, UserMeal> userMeals = repository.computeIfAbsent(userId, ConcurrentHashMap::new);
        userMeals.put(userMeal.getId(), userMeal);
        return userMeal;
    }

    @Override
    public boolean delete(int userId, int id) {
        return repository.get(userId) != null && repository.get(userId).remove(id) != null;
    }

    @Override
    public UserMeal get(int userId, int id) {
        Map<Integer, UserMeal> userMeals = repository.get(userId);
        if (userMeals == null) return null;
        else return userMeals.get(id);
    }

    @Override
    public Collection<UserMeal> getBetween(int userId, LocalDateTime fromDate, LocalDateTime toDate) {
        return repository.get(userId).values().stream()
                .filter(meal -> TimeUtil.isBetween(meal.getDateTime(), fromDate, toDate))
                .sorted((meal1, meal2) -> meal1.getDateTime().compareTo(meal2.getDateTime()))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<UserMeal> getAll(int userId) {
        Map<Integer, UserMeal> userMeals = repository.get(userId);
        return userMeals == null ? Collections.EMPTY_LIST
                : userMeals.values().stream()
                .sorted((meal1, meal2) -> meal1.getDateTime().compareTo(meal2.getDateTime()))
                .collect(Collectors.toList());
    }
}
