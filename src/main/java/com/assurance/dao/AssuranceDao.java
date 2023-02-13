package com.assurance.dao;

import com.assurance.entities.Assurance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface AssuranceDao extends CrudRepository<Assurance, UUID> {

    @Query("select a from Assurance a " +
            "where a.activeStatus = true " +
            "and a.vehicule = ?1 " +
            "and a.organisation.id = ?2 ")
    List<Assurance> finddByImmatriculation(String immatriculation, UUID organisationId);

    @Query("select a from Assurance a " +
            "where year(a.createdDate) = year(current_date) " +
            "and  month(a.createdDate) = ?1")
    List<Assurance> getAllByMonth(Integer month);

    @Query("select a from Assurance a " +
            "where year(a.date_fin) = year(current_date) " +
            "and  a.date_fin < current_date " +
            "and a.activeStatus = true")
    List<Assurance> getExpiredThisYear();

    @Query("select a from Assurance a " +
            "where a.date_fin > current_date " +
            "and a.activeStatus = true")
    List<Assurance> getValid();

    @Query("select a from Assurance a " +
            "where a.activeStatus = true " +
            "and a.date_fin > current_date " +
            "group by a.primeAssurance")
    List<Assurance> getValidAssuranceGroupbyPrime();

    List<Assurance>findByPrimeAssurance(double prime);

    @Query("select a from Assurance a " +
            "where a.activeStatus = true " +
            "and a.vehicule = ?1 " +
            "and a.date_fin > current_date ")
    List<Assurance> getValidByImmatriculation(String immatriculation);

    Page<Assurance> findByActiveStatusTrueAndOrganisation_IdOrderByCreatedDateDesc(Pageable pageable, UUID id);
    List<Assurance> findByActiveStatusTrue();
    List<Assurance> findByActiveStatusTrueAndPrimeAssuranceLessThanEqualAndPrimeAssuranceGreaterThan(double prime, double min);
}
