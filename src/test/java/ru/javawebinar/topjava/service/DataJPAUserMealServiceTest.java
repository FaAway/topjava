package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.UserMeal;

import static ru.javawebinar.topjava.MealTestData.MEAL1_ID;

/**
 * Created by FarAway on 11.04.2016.
 */

@ActiveProfiles(Profiles.DATAJPA)
public class DataJPAUserMealServiceTest extends UserMealServiceTest {
    @Test
    public void testGet() throws Exception {
        UserMeal actual = service.getWithUser(MEAL1_ID);
        UserTestData.MATCHER.assertEquals(UserTestData.USER, actual.getUser());
    }
}
