package com.sa.java17gda.yoyo_allegro.model;

import lombok.*;
import org.springframework.data.repository.cdi.Eager;
import org.springframework.security.authentication.AuthenticationProvider;

import javax.persistence.*;
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

    @ManyToMany (fetch = FetchType.EAGER)
    private Set<UserRole> roles;
}
