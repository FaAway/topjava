package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.exception.ExceptionUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.Collection;

/**
 * GKislin
 * 06.03.2015.
 */

@Service
public class UserMealServiceImpl implements UserMealService {

    @Autowired
    private UserMealRepository repository;

    @Override
    public UserMeal save(int userId, UserMeal userMeal) {
        return repository.save(userId, userMeal);
    }

    @Override
    public UserMeal update(int userId, UserMeal userMeal) {
        return ExceptionUtil.check(repository.save(userId, userMeal), userId);
    }

    @Override
    public void delete(int userId, int id) throws NotFoundException {
        ExceptionUtil.check(repository.delete(userId, id), id);
    }

    @Override
    public UserMeal get(int userId, int id) throws NotFoundException {
        return ExceptionUtil.check(repository.get(userId, id), id);
    }

    @Override
    public Collection<UserMeal> getBetween(int userId, LocalDateTime fromDate, LocalDateTime toDate) {
        return repository.getBetween(userId, fromDate, toDate);
    }

    @Override
    public Collection<UserMeal> getAll(int userId) {
        return repository.getAll(userId);
    }
}
