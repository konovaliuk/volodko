package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "pending_activities", schema = "Time-Tracking")
public class PendingActivities implements Serializable {
    @Id
    @Column(name = "id_pending_activities")
    private Integer idPendingActivities;

    @Column(name = "activitie")
    private String activitie;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pendingActivities")
    @JsonIgnore
    private List<Schedule> schedules;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id_users", name = "user_id")
    private User user;
}
