package com.sa.java17gda.yoyo_allegro.model.dto.request;

import lombok.Data;

@Data
public class RegisterUserRequest {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String province;
    private String city;
    private String address;
    private int zipCode;

}
