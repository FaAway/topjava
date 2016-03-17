package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.web.user.AdminRestController;

/**
 * Created by FarAway on 18.03.2016.
 */
public class SpringContext {
    public static ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml");;
    public AdminRestController adminUserController;
}
