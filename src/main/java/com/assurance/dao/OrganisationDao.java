package com.assurance.dao;

import com.assurance.entities.Assurance;
import com.assurance.entities.Organisation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface OrganisationDao extends CrudRepository<Organisation, UUID> {
    Page<Organisation> findByActiveStatusTrueAndParentTrue(Pageable pageable);

    List<Organisation> findByActiveStatusTrueAndParentTrue();
    List<Organisation> findByActiveStatusTrueAndParentOrganisation_ActiveStatusTrueAndParentOrganisation_Id(UUID id, Pageable pageable);
    List<Organisation> findByActiveStatusTrueAndParentOrganisation_ActiveStatusTrueAndParentOrganisation_Id(UUID id);
    List<Organisation> findByActiveStatusTrueAndParentOrganisation_ActiveStatusTrueAndParentOrganisation_Nom(String nomOrganisation);
}
