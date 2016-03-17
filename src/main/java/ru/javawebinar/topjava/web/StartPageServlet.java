package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.SpringContext;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.web.user.AdminRestController;
import ru.javawebinar.topjava.web.user.LoggedUser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by FarAway on 17.03.2016.
 */

public class StartPageServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(MealServlet.class);
    AdminRestController adminUserController;

    @Override
    public void init() throws ServletException {
        super.init();
        adminUserController = SpringContext.appCtx.getBean(AdminRestController.class);
        adminUserController.create(new User(1, "userName1", "email1", "password1", Role.ROLE_ADMIN));
        adminUserController.create(new User(2, "userName2", "email2", "password2", Role.ROLE_USER));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("userId");
        int userId = Integer.parseInt(id);
        if (adminUserController.get(userId) != null) {
            LoggedUser.set(userId);
            resp.sendRedirect("startpage");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", adminUserController.getAll());
        req.setAttribute("loggedUserId", LoggedUser.id());
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
