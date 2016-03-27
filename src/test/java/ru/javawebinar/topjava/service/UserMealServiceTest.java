package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

/**
 * Created by FarAway on 25.03.2016.
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserMealServiceTest {
    @Autowired
    protected UserMealService service;

    @Autowired
    private DbPopulator dbPopulator;

    @Before
    public void setUp() throws Exception {
        dbPopulator.execute();
    }

    @Test
    public void testGet() throws Exception {
        UserMeal meal = service.get(ID1, USER_ID);
        MealTestData.MATCHER.assertEquals(MealTestData.MEAL1, meal);
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(ID1, USER_ID);
        List<UserMeal> meals_desc = new ArrayList<>(USER_MEALS);
        meals_desc.remove(0);
        Collections.reverse(meals_desc);
        MealTestData.MATCHER.assertCollectionEquals(meals_desc, service.getAll(USER_ID));
    }

    @Test
    public void testGetBetweenDates() throws Exception {
        List<UserMeal> expectedMeals = Arrays.asList(MEAL3, MEAL2, MEAL1);
        MealTestData.MATCHER.assertCollectionEquals(expectedMeals , service.getBetweenDates(MEAL1_TIME.toLocalDate(), MEAL1_TIME.toLocalDate(), USER_ID));
    }

    @Test
    public void testGetBetweenDateTimes() throws Exception {
        List<UserMeal> expectedMeals = Arrays.asList(MEAL4, MEAL3, MEAL2);
        MealTestData.MATCHER.assertCollectionEquals(expectedMeals , service.getBetweenDateTimes(MEAL2_TIME, MEAL4_TIME, USER_ID));

    }

    @Test
    public void testGetAll() throws Exception {
        //WAY1
        /*List<UserMeal> mealsDesc = new ArrayList<>();
        USER_MEALS.stream()
                .collect(Collectors.toCollection(ArrayDeque::new))
                .descendingIterator()
                .forEachRemaining(mealsDesc::add);*/
        //WAY2
        List<UserMeal> mealsDesc = new ArrayList<>(USER_MEALS);
        Collections.reverse(mealsDesc);

        MealTestData.MATCHER.assertCollectionEquals(mealsDesc, service.getAll(USER_ID));
    }

    @Test
    public void testUpdate() throws Exception {
        service.update(MEAL_FOR_UPDATE, USER_ID);
        MealTestData.MATCHER.assertEquals(MEAL_FOR_UPDATE, service.get(MEAL_FOR_UPDATE.getId(), USER_ID));
    }

    @Test(expected = NotFoundException.class)
    public void testUpdateForeignMeal() throws Exception {
        service.update(MEAL_FOR_UPDATE, USER_ID);
        MealTestData.MATCHER.assertEquals(MEAL_FOR_UPDATE, service.get(MEAL_FOR_UPDATE.getId(), ADMIN_ID));
    }

    @Test
    public void testSave() throws Exception {
        UserMeal mealAfterSave = service.save(MEAL_FOR_SAVE, USER_ID);
        MealTestData.MATCHER.assertEquals(mealAfterSave, service.get(MEAL_FOR_SAVE.getId(), USER_ID));
    }
}