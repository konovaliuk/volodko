package com.grubjack.cinema.dao.entities;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "tickets", schema = "restaurant")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "line")
    private Integer line;
    @Column(name = "seat")
    private Integer seat;
    @Column(name = "price")
    private Integer price;
    @Column(name = "sold")
    private Boolean sold;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "show_id")
    private Show show;
}
