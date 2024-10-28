package com.project.garbagecollectionsys.complaint;

import jakarta.persistence.*;
import lombok.Data;
import com.project.garbagecollectionsys.users.User;
import com.project.garbagecollectionsys.route.Route;
import com.project.garbagecollectionsys.enums.Status;
import java.time.LocalDateTime;

@Entity
@Table(name = "complaints")
@Data
public class Complaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;

    private String description;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime date;

}
