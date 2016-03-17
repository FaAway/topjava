package ru.javawebinar.topjava.web.user;

import org.springframework.stereotype.Component;
import ru.javawebinar.topjava.util.UserMealsUtil;

/**
 * GKislin
 * 06.03.2015.
 */

@Component
public class LoggedUser {
    private static int id = 1;

    public static int id() {
        return id;
    }

    public static void set(int id) {
        LoggedUser.id = id;
    }

    public static int getCaloriesPerDay() {
        return UserMealsUtil.DEFAULT_CALORIES_PER_DAY;
    }
}
