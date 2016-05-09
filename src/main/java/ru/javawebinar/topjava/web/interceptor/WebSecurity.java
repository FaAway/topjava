package ru.javawebinar.topjava.web.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import ru.javawebinar.topjava.web.meal.AbstractUserMealController;

/**
 * Created by FarAway on 08.05.2016.
 */
public class WebSecurity {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractUserMealController.class);

    private static WebSecurity instance;
    private WebSecurity() {}

    public static WebSecurity getInstance() {
        if (instance == null) instance = new WebSecurity();
        return instance;
    }

    public boolean checkUserId(Authentication authentication, int id) {
        WebAuthenticationDetails details = (WebAuthenticationDetails) authentication.getDetails();
        LOG.info(details.toString());
        return (id == 3);
    }
}

