package com.sa.java17gda.yoyo_allegro.service;

import com.sa.java17gda.yoyo_allegro.model.AppUser;
import com.sa.java17gda.yoyo_allegro.model.dto.request.RegisterUserRequest;
import com.sa.java17gda.yoyo_allegro.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.sql.Timestamp;
import java.time.Instant;
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
        appUser.setFirstName(request.getFirstName());
        appUser.setLastName(request.getLastName());
        appUser.setRoles(userRoleService.getDefaultUserRoles());
        appUser.setAddress(request.getAddress());
        appUser.setCity(request.getCity());
        appUser.setProvince(request.getProvince());
        appUser.setEmail(request.getEmail());
        appUser.setAccountCreationDate(Timestamp.from(Instant.now()));

        return Optional.of(appUserRepository.save(appUser));
    }


    public void updateAddress (AppUser appUser, RegisterUserRequest update) {

        if(appUser.getUsername() != null) {
        appUser.setFirstName(update.getFirstName());
        appUser.setLastName(update.getLastName());
        appUser.setEmail(update.getEmail());
        appUser.setAddress(update.getAddress());
        appUser.setCity(update.getCity());
        appUser.setZipCode(update.getZipCode());
        appUser.setProvince(update.getProvince());

        appUserRepository.save(appUser);

        }

    }
}


