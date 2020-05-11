package com.grubjack.cinema.dao.impl;

import com.grubjack.cinema.dao.ShowDao;
import com.grubjack.cinema.exception.DaoException;
import com.grubjack.cinema.model.DayOfWeek;
import com.grubjack.cinema.model.Show;
import com.grubjack.cinema.model.Ticket;
import com.grubjack.cinema.model.TimeOfDay;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * {@code ShowDaoImpl} implementation of interface {@code ShowDao} for jdbc operations with entity {@code Show}
 */
public class ShowDaoImpl implements ShowDao {
    private static Logger log = LoggerFactory.getLogger(ShowDaoImpl.class);
    private static final String CREATE_SHOW_SQL = "INSERT INTO shows (day, time, movie) VALUES (?,?,?)";
    private static final String CREATE_TICKET_SQL = "INSERT INTO tickets (line, seat, price, show_id) VALUES (?,?,?,?)";
    private static final String UPDATE_SHOW_SQL = "UPDATE shows SET day=?, time=?, movie=? WHERE id=?";
    private static final String DELETE_SHOW_SQL = "DELETE FROM shows WHERE id=?";
    private static final String FIND_SHOW_SQL = "SELECT * FROM shows WHERE id=?";
    private static final String FIND_ALL_SHOW_SQL = "SELECT * FROM shows ORDER BY day,time";
    private static final String FIND_SHOW_BY_DATE = "SELECT * FROM shows WHERE day=?";
    private static final String FIND_SHOW_BY_TIME = "SELECT * FROM shows WHERE time=?";
    private static final String FIND_SHOW_BY_DATE_TIME = "SELECT * FROM shows WHERE day=? AND time=?";
    private static final String FIND_SHOW_BY_MOVIE = "SELECT * FROM shows WHERE UPPER(movie) LIKE UPPER(?)";
    private static final String FIND_SHOW_BY_TICKET = "SELECT s.id AS id, s.day,s.time,s.movie,t.id AS tid FROM shows s INNER JOIN tickets t ON s.id = t.show_id WHERE t.id=?";
    private DataSource dataSource;

    public ShowDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void create(Show show) throws DaoException {
        log.info("Creating new show");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(CREATE_SHOW_SQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, show.getDayOfWeek().toString());
            preparedStatement.setString(2, show.getTimeOfDay().toString());
            preparedStatement.setString(3, show.getMovieName());
            preparedStatement.execute();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                show.setId(resultSet.getInt(1));
                log.info("Show is created with id = " + show.getId());
            }
            if (show.getTickets().size() > 0) {
                preparedStatement = connection.prepareStatement(CREATE_TICKET_SQL);
                for (Ticket ticket : show.getTickets()) {
                    preparedStatement.setInt(1, ticket.getLine());
                    preparedStatement.setInt(2, ticket.getSeat());
                    preparedStatement.setInt(3, ticket.getPrice());
                    preparedStatement.setInt(4, show.getId());
                    preparedStatement.addBatch();
                }
                preparedStatement.executeBatch();
            }
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                log.error("Can't rollback connection", e1);
            }
            log.error("Can't create show", e);
            throw new DaoException("Can't create show", e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    log.error("Can't close result set", e);
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    log.error("Can't close preparedStatement", e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.error("Can't close connection", e);
                }
            }
        }
    }

    @Override
    public void update(Show show) throws DaoException {
        log.info("Updating show with id = " + show.getId());
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_SHOW_SQL);
            preparedStatement.setString(1, show.getDayOfWeek().toString());
            preparedStatement.setString(2, show.getTimeOfDay().toString());
            preparedStatement.setString(3, show.getMovieName());
            preparedStatement.setInt(4, show.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error("Can't update show", e);
            throw new DaoException("Can't update show", e);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    log.error("Can't close preparedStatement", e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.error("Can't close connection", e);
                }
            }
        }
    }

    @Override
    public void delete(int id) throws DaoException {
        log.info("Deleting show with id " + id);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(DELETE_SHOW_SQL);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            log.error("Can't delete show", e);
            throw new DaoException("Can't delete show", e);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    log.error("Can't close preparedStatement", e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.error("Can't close connection", e);
                }
            }
        }
    }

    @Override
    public Show find(int id) throws DaoException {
        log.info("Finding show with id {}", id);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Show show = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(FIND_SHOW_SQL);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                show = new Show();
                show.setId(id);
                show.setDayOfWeek(DayOfWeek.valueOf(resultSet.getString("day")));
                show.setTimeOfDay(TimeOfDay.convert(resultSet.getString("time")));
                show.setMovieName(resultSet.getString("movie"));
            }
        } catch (SQLException e) {
            log.error("Can't find show", e);
            throw new DaoException("Can't find show", e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    log.error("Can't close result set", e);
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    log.error("Can't close preparedStatement", e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.error("Can't close connection", e);
                }
            }
        }
        return show;
    }

    @Override
    public List<Show> findAll() throws DaoException {
        log.info("Finding all shows");
        List<Show> result = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(FIND_ALL_SHOW_SQL);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Show show = new Show();
                show.setId(resultSet.getInt("id"));
                show.setDayOfWeek(DayOfWeek.valueOf(resultSet.getString("day")));
                show.setTimeOfDay(TimeOfDay.convert(resultSet.getString("time")));
                show.setMovieName(resultSet.getString("movie"));
                result.add(show);
            }
        } catch (SQLException e) {
            log.error("Can't find shows", e);
            throw new DaoException("Can't find shows", e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    log.error("Can't close result set", e);
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    log.error("Can't close preparedStatement", e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.error("Can't close connection", e);
                }
            }
        }
        return result;
    }

    @Override
    public List<Show> findByDay(DayOfWeek dayOfWeek) throws DaoException {
        log.info("Finding shows by day " + dayOfWeek);
        List<Show> result = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(FIND_SHOW_BY_DATE);
            preparedStatement.setString(1, dayOfWeek.name());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Show show = new Show();
                show.setId(resultSet.getInt("id"));
                show.setDayOfWeek(dayOfWeek);
                show.setTimeOfDay(TimeOfDay.convert(resultSet.getString("time")));
                show.setMovieName(resultSet.getString("movie"));
                result.add(show);
            }
        } catch (SQLException e) {
            log.error("Can't find shows", e);
            throw new DaoException("Can't find shows", e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    log.error("Can't close result set", e);
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    log.error("Can't close statement", e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.error("Can't close connection", e);
                }
            }
        }
        return result;
    }

    @Override
    public List<Show> findByTime(TimeOfDay timeOfDay) throws DaoException {
        log.info("Finding shows by time " + timeOfDay);
        List<Show> result = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(FIND_SHOW_BY_TIME);
            preparedStatement.setString(1, timeOfDay.toString());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Show show = new Show();
                show.setId(resultSet.getInt("id"));
                show.setDayOfWeek(DayOfWeek.valueOf(resultSet.getString("day")));
                show.setTimeOfDay(timeOfDay);
                show.setMovieName(resultSet.getString("movie"));
                result.add(show);
            }
        } catch (SQLException e) {
            log.error("Can't find shows", e);
            throw new DaoException("Can't find shows", e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    log.error("Can't close result set", e);
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    log.error("Can't close preparedStatement", e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.error("Can't close connection", e);
                }
            }
        }
        return result;
    }

    @Override
    public Show findByDayAndTime(DayOfWeek dayOfWeek, TimeOfDay timeOfDay) throws DaoException {
        log.info("Finding shows by day {} and time {} ", dayOfWeek, timeOfDay);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Show show = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(FIND_SHOW_BY_DATE_TIME);
            preparedStatement.setString(1, dayOfWeek.name());
            preparedStatement.setString(2, timeOfDay.toString());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                show = new Show();
                show.setId(resultSet.getInt("id"));
                show.setDayOfWeek(dayOfWeek);
                show.setTimeOfDay(timeOfDay);
                show.setMovieName(resultSet.getString("movie"));
            }
        } catch (SQLException e) {
            log.error("Can't find shows", e);
            throw new DaoException("Can't find shows", e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    log.error("Can't close result set", e);
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    log.error("Can't close preparedStatement", e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.error("Can't close connection", e);
                }
            }
        }
        return show;
    }

    @Override
    public List<Show> findByMovie(String movie) throws DaoException {
        log.info("Finding shows by '{}'" + movie);
        List<Show> result = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(FIND_SHOW_BY_MOVIE);
            preparedStatement.setString(1, movie);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Show show = new Show();
                show.setId(resultSet.getInt("id"));
                show.setDayOfWeek(DayOfWeek.valueOf(resultSet.getString("day")));
                show.setTimeOfDay(TimeOfDay.convert(resultSet.getString("time")));
                show.setMovieName(resultSet.getString("movie"));
                result.add(show);
            }
        } catch (SQLException e) {
            log.error("Can't find shows", e);
            throw new DaoException("Can't find shows", e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    log.error("Can't close result set", e);
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    log.error("Can't close preparedStatement", e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.error("Can't close connection", e);
                }
            }
        }
        return result;
    }

    @Override
    public Show findByTicket(int ticketId) throws DaoException {
        log.info("Finding shows by ticket with id {}" + ticketId);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Show show = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(FIND_SHOW_BY_TICKET);
            preparedStatement.setInt(1, ticketId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                show = new Show();
                show.setId(resultSet.getInt("id"));
                show.setDayOfWeek(DayOfWeek.valueOf(resultSet.getString("day")));
                show.setTimeOfDay(TimeOfDay.convert(resultSet.getString("time")));
                show.setMovieName(resultSet.getString("movie"));
            }
        } catch (SQLException e) {
            log.error("Can't find show", e);
            throw new DaoException("Can't find show", e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    log.error("Can't close result set", e);
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    log.error("Can't close preparedStatement", e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.error("Can't close connection", e);
                }
            }
        }
        return show;
    }
}
