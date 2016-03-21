package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.service.UserMealService;
import ru.javawebinar.topjava.to.UserMealWithExceed;
import ru.javawebinar.topjava.util.TimeUtil;
import ru.javawebinar.topjava.util.UserMealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */

@Controller
public class UserMealRestController {
    private static final Logger LOG = LoggerFactory.getLogger(UserMealRestController.class);
    @Autowired
    private UserMealService service;

    public UserMeal get(int id) {
        int userId = LoggedUser.id();
        LOG.info("got Meal {} fro User {}", id, userId);
        return service.get(userId, id);
    }

    public void delete(int id)  {
        int userId = LoggedUser.id();
        LOG.info("delete id=" + id + "by userId=" + userId);
        service.delete(userId, id);
    }

    public List<UserMealWithExceed> getAll() {
        int userId = LoggedUser.id();
        LOG.info("getAll by userId=" + userId);
        return UserMealsUtil.getWithExceeded(service.getAll(userId), LoggedUser.getCaloriesPerDay());
    }

    public void update(UserMeal userMeal) {
        int userId = LoggedUser.id();
        LOG.info("save Meal {} by User {}", userMeal.getId(), userId);
        service.update(userId, userMeal);
    }

    public UserMeal create(UserMeal userMeal) {
        userMeal.setId(null);
        int userId = LoggedUser.id();
        LOG.info("save Meal {} by User {}", userMeal.getId(), userId);
        return service.save(userId, userMeal);
    }

    public List<UserMealWithExceed> getBetween(LocalDate fromDate, LocalTime fromTime, LocalDate toDate, LocalTime toTime) {
        int userId = LoggedUser.id();
        LOG.info("getBetween dates {} - {}, for time {} - {}, for User {}", fromDate, toDate, fromTime, toTime, userId);
        return UserMealsUtil.getFilteredWithExceeded(service.getBetweenDates(userId,
                fromDate == null ? TimeUtil.MIN_DATE : fromDate,  toDate == null ? TimeUtil.MAX_DATE : toDate),
                fromTime == null ? LocalTime.MIN : fromTime, toTime == null ? LocalTime.MAX: toTime,
                LoggedUser.getCaloriesPerDay());
    }
}
