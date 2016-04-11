package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * GKislin
 * 27.03.2015.
 */
@Repository
public class DataJpaUserMealRepositoryImpl implements UserMealRepository{

    @Autowired
    private ProxyUserMealRepository proxy;

    @Autowired
    private ProxyUserRepository userProxy;

    @Override
    public UserMeal save(UserMeal userMeal, int userId) {
        userMeal.setUser(userProxy.findOne(userId));
        if (userMeal.isNew() || get(userMeal.getId(), userId) != null)
            return proxy.save(userMeal);
        else return null;
    }

    @Override
    public boolean delete(int id, int userId) {
        //return (get(id, userId) == null) ? false : proxy.delete(id) != 0;
        return proxy.delete(id, userId) != 0;
    }

    @Override
    public UserMeal get(int id, int userId) {
        //UserMeal m =  proxy.findOne(id);
        //return (m.getUser().getId() == userId) ? m : null;
        return proxy.findOneByIdAndUserId(id, userId);
    }

    @Override
    public List<UserMeal> getAll(int userId) {
        return proxy.findByUserIdOrderByDateTimeDesc(userId);
    }

    @Override
    public List<UserMeal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return proxy.findByUserIdAndDateTimeBetweenOrderByDateTimeDesc(userId, startDate, endDate);
    }

    public UserMeal getWithUser(int id) {
        return proxy.findWithUser(id);
    }
}
