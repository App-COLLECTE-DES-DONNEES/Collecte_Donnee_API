package com.ditros.mcd.dao;

import com.ditros.mcd.model.entity.User;
import com.ditros.mcd.model.entity.VehicleAccident;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDao extends JpaRepository<User, Long> {
    Optional<User> findByKeycloakId(String keycloakId);
}
