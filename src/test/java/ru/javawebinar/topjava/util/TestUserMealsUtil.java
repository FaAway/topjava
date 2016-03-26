package ru.javawebinar.topjava.util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.to.UserMealWithExceed;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Created by FarAway on 03.03.2016.
 */
public class TestUserMealsUtil {
    private List<UserMeal> mealList;

    public static final ModelMatcher<UserMealWithExceed, TestUserMeal> MATCHER = new ModelMatcher<>(u -> ((u instanceof TestUserMeal) ? (TestUserMeal) u : new TestUserMeal(u)));

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
        MATCHER.assertCollectionEquals(result, list);
    }

    @Test
    public void compareCycleFilterAndLambdaFilter() {
        List<UserMealWithExceed> resultLambda = UserMealsUtil.getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);
        List<UserMealWithExceed> resultCycle = UserMealsUtil.getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);

        MATCHER.assertCollectionEquals(resultCycle, resultLambda);
    }

    @After
    public void tearDown() {
        mealList = null;
    }

    public static class TestUserMeal extends UserMealWithExceed {

        public TestUserMeal(UserMealWithExceed userMeal) {
            this(userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(), userMeal.isExceed());
        }

        public TestUserMeal(LocalDateTime dateTime, String description, int calories, boolean isExceed) {
            super(dateTime, description, calories, isExceed);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            TestUserMeal that = (TestUserMeal) o;

            return Objects.equals(this.getDateTime(), that.getDateTime()) &&
                   Objects.equals(this.getDescription(), that.getDescription()) &&
                   Objects.equals(this.getCalories(), that.getCalories()) &&
                   Objects.equals(this.isExceed(), that.isExceed());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getDateTime(), getDescription(), getCalories(), isExceed());
        }
    }
}
