package com.sa.java17gda.yoyo_allegro.repository;

import com.sa.java17gda.yoyo_allegro.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository <UserRole, Long> {
    Optional<UserRole> findByName(String name);
}
