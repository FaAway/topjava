package ru.javawebinar.topjava.util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.to.UserMealWithExceed;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by FarAway on 03.03.2016.
 */
public class TestUserMealsUtil {
    private List<UserMeal> mealList;

    @Before
    public void initList() {
        mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );
    }


    @Test
    public void testFilter() {
        List<UserMealWithExceed> result = Arrays.asList(
                new UserMealWithExceed(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500, false),
                new UserMealWithExceed(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000, true)
        );
        List<UserMealWithExceed> list = UserMealsUtil.getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);
        assertThat(list, is(result));
    }

    @Test
    public void compareCycleFilterAndLambdaFilter() {
        List<UserMealWithExceed> resultLambda = UserMealsUtil.getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);
        List<UserMealWithExceed> resultCycle = UserMealsUtil.getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);

        assertThat(resultCycle, is(resultLambda));
    }

    @After
    public void tearDown() {
        mealList = null;
    }
}
