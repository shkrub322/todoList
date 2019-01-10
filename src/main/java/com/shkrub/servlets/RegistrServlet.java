package com.shkrub.servlets;

import com.shkrub.dao.UserDao;
import com.shkrub.dao.UserRoleDao;
import com.shkrub.dao.impl.UserDaoImpl;
import com.shkrub.dao.impl.UserRoleDaoImpl;
import com.shkrub.entities.UserRoles;
import com.shkrub.entities.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegistrServlet", urlPatterns = "/registr")
public class RegistrServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDao userDao = new UserDaoImpl();
        UserRoleDao userRoleDao = new UserRoleDaoImpl();
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (login.equals("") || password.equals("")){
            resp.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        }
        else {
            userDao.persist(new Users(login, password));
            userRoleDao.persist(new UserRoles(login));
        }
    }
}
