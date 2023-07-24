package com.ditros.mcd.dao;

import com.ditros.mcd.model.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationDao extends JpaRepository<Organization, Long> {
}
