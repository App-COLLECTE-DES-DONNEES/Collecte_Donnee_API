package com.ditros.mcd.dao;

import com.ditros.mcd.model.entity.Municipality;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MunicipalityDao  extends JpaRepository<Municipality, Long> {
}
