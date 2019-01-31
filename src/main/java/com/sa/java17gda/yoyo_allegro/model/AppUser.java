package com.sa.java17gda.yoyo_allegro.model;

import lombok.*;
import org.springframework.data.repository.cdi.Eager;
import org.springframework.security.authentication.AuthenticationProvider;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Set;
@Builder
@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String province;
    private String city;
    private String address;
    private int zipCode;
    private Timestamp accountCreationDate;
    private String accountStatus;



    @ManyToMany (fetch = FetchType.EAGER)
    private Set<UserRole> roles;
}
