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
@Table(name = "users", schema = "Time-Tracking")
public class User implements Serializable {

    @Id
    @Column(name = "id_users")
    private Integer idUsers;

    @Column(name = "nickname")
    private String nickname;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @JsonIgnore
    private List<PendingActivities> pendingActivities;
}
