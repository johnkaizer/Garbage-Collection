package com.project.garbagecollectionsys.route;

import com.project.garbagecollectionsys.users.User;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "routes")
@Data
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String routeName;
    private String landmarks;
    private String scheduledDay;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private User driver;

}
