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

    private String name;
    private String email;
    private String phone;
    private String address;
    @Enumerated(EnumType.STRING)
    private Role role;

}
