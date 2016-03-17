package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.service.UserMealService;
import ru.javawebinar.topjava.util.TimeUtil;
import ru.javawebinar.topjava.util.exception.IllegalAccessException;
import ru.javawebinar.topjava.util.exception.NotFoundException;
import ru.javawebinar.topjava.web.user.LoggedUser;

import java.time.LocalDate;
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
    @Autowired
    private LoggedUser loggedUser;

    public UserMeal save(UserMeal userMeal) throws IllegalAccessException {
        LOG.info("save id=" + userMeal.getId() +" by userId=" + loggedUser.id());
        if (userMeal.isNew())
            return service.save(loggedUser.id(), userMeal);
        else
            try {
                service.get(loggedUser.id(), userMeal.getId());
                return service.save(loggedUser.id(), userMeal);
            } catch (NotFoundException e) {
                LOG.warn("REST: Trying to save save meal that already belongs to another user. Current userID=" + loggedUser.id() + ", id=" + userMeal.getId());
                throw new IllegalAccessException("REST: Trying to save save meal that already belongs to another user. Current userID=" + loggedUser.id() + ", id=" + userMeal.getId());
            }
    }

    public void delete(int id) throws NotFoundException {
        LOG.info("delete id=" + id + "by userId=" + loggedUser.id());
        if (service.get(loggedUser.id(), id) != null)
            service.delete(loggedUser.id(), id);
    }

    public UserMeal get(int id) throws NotFoundException {
        LOG.info("get id=" + id + " userId=" + loggedUser.id());
        return service.get(loggedUser.id(), id);
    }

    public List<UserMeal> getAll() {
        LOG.info("getAll by userId=" + loggedUser.id());
        return service.getAll(loggedUser.id());
    }

    public List<UserMeal> getAllBetweenDates(LocalDate fromDate, LocalDate toDate) {
        LOG.info(String.format("getAllBetweenDates {%s,%s} by userId=%d",
                TimeUtil.DATE_FORMATTER.format(fromDate), TimeUtil.DATE_FORMATTER.format(toDate), loggedUser.id()));
        return service.getAllBetweenDates(loggedUser.id(), fromDate, toDate);
    }
}
