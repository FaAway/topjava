package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * GKislin
 * 13.03.2015.
 */
public class MealTestData {

    public static final ModelMatcher<UserMeal, String> MATCHER = new ModelMatcher<>(UserMeal::toString);

    public static final int GLOBAL_SEQ_START = 100000;
    public static final int ID1 = GLOBAL_SEQ_START + 2;
    public static final int ID2 = GLOBAL_SEQ_START + 3;
    public static final int ID3 = GLOBAL_SEQ_START + 4;
    public static final int ID4 = GLOBAL_SEQ_START + 5;
    public static final int ID5 = GLOBAL_SEQ_START + 6;
    public static final int ID6 = GLOBAL_SEQ_START + 7;

    public static final LocalDateTime MEAL1_TIME = LocalDateTime.parse("2015-05-30T10:00:00");
    public static final LocalDateTime MEAL2_TIME = LocalDateTime.parse("2015-05-30T13:00:00");
    public static final LocalDateTime MEAL3_TIME = LocalDateTime.parse("2015-05-30T20:00:00");
    public static final LocalDateTime MEAL4_TIME = LocalDateTime.parse("2015-05-31T10:00:00");
    public static final LocalDateTime MEAL5_TIME = LocalDateTime.parse("2015-05-31T13:00:00");
    public static final LocalDateTime MEAL6_TIME = LocalDateTime.parse("2015-05-31T20:00:00");

    public static final UserMeal MEAL1 = new UserMeal(ID1, MEAL1_TIME, "Завтрак", 500);
    public static final UserMeal MEAL2 = new UserMeal(ID2, MEAL2_TIME, "Обед", 1000);
    public static final UserMeal MEAL3 = new UserMeal(ID3, MEAL3_TIME, "Ужин", 500);
    public static final UserMeal MEAL4 = new UserMeal(ID4, MEAL4_TIME, "Завтрак", 1000);
    public static final UserMeal MEAL5 = new UserMeal(ID5, MEAL5_TIME, "Обед", 500);
    public static final UserMeal MEAL6 = new UserMeal(ID6, MEAL6_TIME, "Ужин", 510);

    public static final List<UserMeal> USER_MEALS = Arrays.asList(MEAL1, MEAL2, MEAL3, MEAL4, MEAL5, MEAL6);

    public static final UserMeal MEAL_FOR_UPDATE = new UserMeal(ID1, LocalDateTime.of(2016, 3, 26, 15, 2, 10), "Breakfast", 200);
    public static final UserMeal MEAL_FOR_SAVE = new UserMeal(LocalDateTime.of(2016, 3, 26, 15, 2, 10), "Breakfast", 200);


}
