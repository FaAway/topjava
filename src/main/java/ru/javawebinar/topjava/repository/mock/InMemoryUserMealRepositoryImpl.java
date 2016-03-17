package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.TimeUtil;
import ru.javawebinar.topjava.util.UserMealsUtil;

import java.time.LocalDate;
import java.util.List;
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
    private static final Logger LOG = LoggerFactory.getLogger(InMemoryUserMealRepositoryImpl.class);
    private Map<Integer, Map<Integer, UserMeal>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        UserMealsUtil.MEAL_LIST.forEach(meal -> this.save(1, meal));
        UserMealsUtil.MEAL_LIST_2.forEach(meal -> this.save(2, meal));
    }

    @Override
    public UserMeal save(int userId, UserMeal userMeal) {
        LOG.info("save " + userMeal + " by userId= " + userId);
        if (userMeal.isNew()) {
            userMeal.setId(counter.incrementAndGet());
        }
        repository.computeIfAbsent(userId, ConcurrentHashMap::new);
        repository.get(userId).put(userMeal.getId(), userMeal);
        return userMeal;
    }

    @Override
    public boolean delete(int userId, int id) {
        LOG.info("delete id=" + id + " by userId=" + userId);
        return repository.get(userId) != null && repository.get(userId).remove(id) != null;
    }

    @Override
    public UserMeal get(int userId, int id) {
        LOG.info("get id=" + id + " by userId= " + userId);
        return repository.get(userId).get(id);
    }

    @Override
    public List<UserMeal> getAllBetweenDates(int userId, LocalDate fromDate, LocalDate toDate) {
        LOG.info("getAllBetweenDates by userId= " + userId);
        return repository.get(userId).values().stream()
                .filter(meal -> TimeUtil.isBetween(meal.getDateTime().toLocalDate(), fromDate, toDate))
                .sorted((meal1, meal2) -> meal1.getDateTime().compareTo(meal2.getDateTime()))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserMeal> getAll(int userId) {
        LOG.info("getAll by userId= " + userId);
        return repository.get(userId).values().stream()
                .sorted((meal1, meal2) -> meal1.getDateTime().compareTo(meal2.getDateTime()))
                .collect(Collectors.toList());
    }
}
