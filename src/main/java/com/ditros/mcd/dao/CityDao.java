package com.ditros.mcd.dao;

import com.ditros.mcd.model.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityDao extends JpaRepository<City, Long> {
}
