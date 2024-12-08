package com.project.garbagecollectionsys.users;

import com.project.garbagecollectionsys.enums.Role;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String phone;
    private String route; // this will be a dropdown menu
    private String password;
    private String role; // user

}
