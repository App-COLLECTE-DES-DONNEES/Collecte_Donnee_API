package com.ditros.mcd.dao;

import com.ditros.mcd.model.entity.CareTreatment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CareTreatmentDao extends JpaRepository<CareTreatment, Long> {
}
