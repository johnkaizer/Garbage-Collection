package com.project.garbagecollectionsys.drivers;

import com.project.garbagecollectionsys.enums.Role;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Drivers")
@Data
public class Drivers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String phone;
    private String routeAssigned; // this will be a dropdown menu
    private String password;
    private String role;
}
