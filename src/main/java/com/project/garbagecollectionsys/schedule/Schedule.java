package com.project.garbagecollectionsys.schedule;

import jakarta.persistence.*;
import com.project.garbagecollectionsys.route.Route;
import java.time.LocalTime;
import com.project.garbagecollectionsys.enums.DayOfWeek;
import lombok.Data;

@Entity
@Table(name = "schedules")
@Data
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "route_id", nullable = false)
    private Route route;

    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    private LocalTime collectionTime;

    private String frequency;
}
