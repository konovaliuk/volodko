package com.grubjack.cinema.web.command;

import com.grubjack.cinema.exception.DaoException;
import com.grubjack.cinema.model.Role;
import com.grubjack.cinema.model.User;
import com.grubjack.cinema.service.ServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.grubjack.cinema.util.ConfigManager.LOGGED_USER_ATTR;
import static com.grubjack.cinema.util.ConfigManager.SHOW_ID_PARAM;

public class CancelMovieCommand implements Command {
    private static Logger log = LoggerFactory.getLogger(CancelMovieCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DaoException {
        log.info("Executing with session id {}", request.getSession().getId());
        String showId = request.getParameter(SHOW_ID_PARAM);
        if (showId != null && !showId.isEmpty()) {
            log.info("Delete show with id {}", Integer.parseInt(showId));
            User loggedUser = (User) request.getSession().getAttribute(LOGGED_USER_ATTR);
            if (loggedUser != null && loggedUser.hasRole(Role.ROLE_ADMIN)) {
                ServiceFactory.getInstance().getShowService().delete(Integer.parseInt(showId));
            } else {
                log.warn("Access denied: user {} without permissions tried to cancel movie with id {}", loggedUser, showId);
            }
        }
        return new ShowScheduleCommand().execute(request, response);
    }
}
