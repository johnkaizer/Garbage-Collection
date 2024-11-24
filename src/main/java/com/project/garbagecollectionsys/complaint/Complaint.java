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
    private String phone;
    private String Route;  //DROP DOWN MENU IN THE UI
    private String description;
    private String status;
    private LocalDateTime date;

}
