package com.project.garbagecollectionsys.users;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;

}
