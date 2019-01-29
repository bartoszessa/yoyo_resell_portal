package com.sa.java17gda.yoyo_allegro.service;

import com.sa.java17gda.yoyo_allegro.model.AppUser;
import com.sa.java17gda.yoyo_allegro.model.dto.request.RegisterUserRequest;
import com.sa.java17gda.yoyo_allegro.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private UserRoleService userRoleService;

    public Optional<AppUser> register(RegisterUserRequest request) {
        Optional<AppUser> appUserOptional = appUserRepository.findByUsername(request.getUsername());
            if (appUserOptional.isPresent()) {
                return Optional.empty();
            }
            AppUser appUser = new AppUser();
            appUser.setUsername(request.getUsername());
            appUser.setPassword(encoder.encode(request.getPassword()));
            appUser.setRoles(userRoleService.getDefaultUserRoles());

        return Optional.of(appUserRepository.save(appUser));
        }
    }


