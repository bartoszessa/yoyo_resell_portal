package com.sa.java17gda.yoyo_allegro.service;

import com.sa.java17gda.yoyo_allegro.model.UserRole;
import com.sa.java17gda.yoyo_allegro.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserRoleService     {
    @Value("${yoyo_allegro.user.defaultRoles}")
    private String[] defaultRoles;

    @Autowired
    private UserRoleRepository userRoleRepository;

    public Set<UserRole> getDefaultUserRoles() {
        Set<UserRole> userRoles = new HashSet<>();

        for (String role : defaultRoles) {
            Optional<UserRole> singleRole = userRoleRepository.findByName(role);
            if (singleRole.isPresent()) {
                userRoles.add(singleRole.get());
            }
        }
        return userRoles;
    }

}
