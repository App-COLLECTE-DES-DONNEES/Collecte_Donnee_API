package com.ditros.mcd.dao;

import com.ditros.mcd.model.entity.PersonImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonImageDao extends JpaRepository<PersonImage, Long> {
}
