package com.ditros.mcd.dao;

import com.ditros.mcd.model.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionDao extends JpaRepository<Region,Long> {
}
