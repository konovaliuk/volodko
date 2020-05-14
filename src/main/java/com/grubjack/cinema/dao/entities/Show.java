package com.grubjack.cinema.dao.entities;

import com.grubjack.cinema.dao.enums.DayOfWeek;
import com.grubjack.cinema.dao.enums.TimeOfDay;

public class Show implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "day")
    private DayOfWeek day;
    @Column(name = "time")
    private TimeOfDay time;
    @Column(name = "movie")
    private String movie;
}
