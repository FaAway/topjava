package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;

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

    public static final UserMeal MEAL1 = new UserMeal(ID1, LocalDateTime.parse("2015-05-30 10:00:00"), "Завтрак", 500);
    public static final UserMeal MEAL2 = new UserMeal(ID2, LocalDateTime.parse("2015-05-30 13:00:00"), "Обед", 1000);
    public static final UserMeal MEAL3 = new UserMeal(ID3, LocalDateTime.parse("2015-05-30 20:00:00"), "Ужин", 500);
    public static final UserMeal MEAL4 = new UserMeal(ID4, LocalDateTime.parse("2015-05-31 10:00:00"), "Завтрак", 1000);
    public static final UserMeal MEAL5 = new UserMeal(ID5, LocalDateTime.parse("2015-05-31 13:00:00"), "Обед", 500);
    public static final UserMeal MEAL6 = new UserMeal(ID6, LocalDateTime.parse("2015-05-31 20:00:00"), "Ужин", 510);

}
