package com.sa.java17gda.yoyo_allegro.component;

import com.sa.java17gda.yoyo_allegro.model.AppUser;
import com.sa.java17gda.yoyo_allegro.model.Auction;
import com.sa.java17gda.yoyo_allegro.model.UserRole;
import com.sa.java17gda.yoyo_allegro.model.YoyoCategory;
import com.sa.java17gda.yoyo_allegro.repository.AppUserRepository;
import com.sa.java17gda.yoyo_allegro.repository.AuctionRepository;
import com.sa.java17gda.yoyo_allegro.repository.UserRoleRepository;
import com.sa.java17gda.yoyo_allegro.repository.YoyoCategoryRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.security.authentication.AuthenticationProvider;

import javax.jws.soap.SOAPBinding;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class DataInitializer implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuctionRepository auctionRepository;

    @Autowired
    private YoyoCategoryRepository yoyoCategoryRepository;





    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        createInitialRoles();
        createInitialUsers();
        createInitialCategories();
        createCategoriesByPlayStyle();
                
    }

    private void createInitialUsers() {
        addUser("admin", "admin", "ROLE_USER", "ROLE_ADMIN");
        addUser("user", "user", "ROLE_USER");
    }

    private void addUser(String username, String password, String ... roles) {
        Set<UserRole> userRoles = new HashSet<>();

        for (String role : roles) {
            Optional<UserRole> singleRole = userRoleRepository.findByName(role);
            if (singleRole.isPresent()) {
                userRoles.add(singleRole.get());
            }
        }
        Optional<AppUser> searchedAppUser = appUserRepository.findByUsername(username);
        if (!searchedAppUser.isPresent()) {
            AppUser appUser = AppUser.builder().username(username)
                    .password(passwordEncoder.encode(password))
                    .roles(userRoles).build();

            appUserRepository.save(appUser);
        }
    }

    private void createInitialRoles() {
        addRole("ROLE_USER");
        addRole("ROLE_ADMIN");

    }

    private void addRole(String name) {
        Optional<UserRole> searchedRole = userRoleRepository.findByName(name);
        if (!searchedRole.isPresent()) {

            UserRole role = new UserRole();
            role.setName(name);

            userRoleRepository.save(role);
        }
    }

    private void createCategories (String category) {
        Optional<YoyoCategory> searchedCategory = yoyoCategoryRepository.findByCategory(category);
        if (!searchedCategory.isPresent()) {

            YoyoCategory yoyoCategory = new YoyoCategory();
            yoyoCategory.setCategory(category);

            yoyoCategoryRepository.save(yoyoCategory);
        }

    }

    private void createInitialCategories() {
        createCategories("MATERIAL_PLASTIC");
        createCategories("MATERIAL_METAL");
        createCategories("CONDITION_NEW");
        createCategories("CONDITION_USED");


    }

    private void createCategoriesByPlayStyle() {
        createCategories("1A");
        createCategories("3A");
        createCategories("5A");
        createCategories("2A");
        createCategories("4A");

    }
}
