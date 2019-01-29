package com.sa.java17gda.yoyo_allegro.repository;

import com.sa.java17gda.yoyo_allegro.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository <AppUser, Long>  {
    Optional<AppUser> findByUsername(String username);
}
