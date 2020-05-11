package com.grubjack.cinema.web.command;

import com.grubjack.cinema.exception.DaoException;
import com.grubjack.cinema.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.grubjack.cinema.service.ServiceFactory.getInstance;
import static com.grubjack.cinema.util.ConfigManager.*;

public class CheckLoginCommand implements Command {

    private static Logger log = LoggerFactory.getLogger(CheckLoginCommand.class);

    public String execute(HttpServletRequest request, HttpServletResponse response) throws DaoException {
        log.info("Executing with session id {}", request.getSession().getId());
        String login = request.getParameter(LOGIN_PARAM);
        String password = request.getParameter(PASSWORD_PARAM);
        request.getSession().setAttribute(LOGIN_PARAM, login);
        request.getSession().setAttribute(PASSWORD_PARAM, password);
        if (getInstance().getUserService().checkLogin(login, password)) {
            User user = getInstance().getUserService().getByEmail(login);
            request.getSession().setAttribute(LOGGED_USER_ATTR, user);
        } else {
            log.warn("User with session id {} has entered invalid credentials", request.getSession().getId());
            request.setAttribute("wrongPassword", "");
            return new LoginCommand().execute(request, response);
        }
        String fromPage = request.getParameter(FROM_PARAM);
        log.info("referer page:  {}", fromPage);
        return (fromPage != null) ? fromPage.substring(request.getContextPath().length()) : new LoginCommand().execute(request, response);
    }
}