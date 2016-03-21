package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.web.meal.UserMealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * User: gkislin
 * Date: 22.08.2014
 */
public class SpringMain {
    public static void main(String[] args) {
        // java 7 Automatic resource management
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println(Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            System.out.println(adminUserController.create(new User(1, "userName1", "email1", "password1", Role.ROLE_ADMIN)));
            System.out.println(adminUserController.create(new User(2, "userName2", "email2", "password2", Role.ROLE_USER)));
            UserMealRestController userMealRestController = appCtx.getBean(UserMealRestController.class);

            System.out.println(userMealRestController.get(1));
            userMealRestController.delete(1);
            userMealRestController.create(new UserMeal(LocalDateTime.now(), "моцарелла", 500));
            System.out.println(userMealRestController.getAll());
        }

    }
}
