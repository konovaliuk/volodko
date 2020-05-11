package com.grubjack.cinema.to;

import com.grubjack.cinema.model.DayOfWeek;
import com.grubjack.cinema.model.TimeOfDay;

/**
 * Created by Volodko Volodymyr
 */
public class TicketWithShow {

    private int id;
    private int line;
    private int seat;
    private int price;
    private DayOfWeek dayOfWeek;
    private TimeOfDay timeOfDay;
    private String movieName;


    public TicketWithShow(int id, int line, int seat, int price, DayOfWeek dayOfWeek, TimeOfDay timeOfDay, String movieName) {
        this.id = id;
        this.line = line;
        this.seat = seat;
        this.price = price;
        this.dayOfWeek = dayOfWeek;
        this.timeOfDay = timeOfDay;
        this.movieName = movieName;
    }

    public int getId() {
        return id;
    }

    public int getRow() {
        return line;
    }

    public int getSeat() {
        return seat;
    }

    public int getPrice() {
        return price;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public TimeOfDay getTimeOfDay() {
        return timeOfDay;
    }

    public String getMovieName() {
        return movieName;
    }
}
