package ru.javawebinar.topjava.service;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;

/**
 * Created by FarAway on 11.04.2016.
 */

@ActiveProfiles(Profiles.JDBC)
public class JDBCUserMealServiceTest extends UserMealServiceTest {
}
