package com.grubjack.cinema.dao.entities;

@Entity
@Table(name = "user_roles", schema = "restaurant")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRoles {
    @Column(name = "role")
    private String role;

    @OneToOne(referencedColumnName = "id", name = "id")
    @JsonIgnore
    private User clientUser;
}
