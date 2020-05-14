package com.example.demo.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "schedule", schema = "Time-Tracking")
public class Schedule implements Serializable {

    @Id
    @Column(name = "id_schedule")
    private Integer idSchedule;
    @Column(name = "time")
    private Time time;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id_users", name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id_pending_activities", name = "activitie_id")
    private PendingActivities pendingActivities;
}
