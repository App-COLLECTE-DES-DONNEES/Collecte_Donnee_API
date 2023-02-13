package com.ditros.mcd.dao;

import com.ditros.mcd.model.entity.Accident;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AccidentDao extends JpaRepository<Accident, Long> {
    
    @Query("SELECT distinct a from Accident a " +
            "join a.organization o " +
            "where o.id in ?1 " +
            "and (?2 = '' " +
            "or lower(a.code) like lower(concat('%', ?2,'%')) " +
            "or lower(a.place) like lower(concat('%', ?2,'%')) )" +
            "and a.activeStatus = true " +
            "order by a.modifiedDate desc")
    Page<Accident> getAccidentByOrganization(List<Long> ids, String search, Pageable pageable);


    @Query("SELECT distinct a from Accident a " +
            "join a.vehicleAccidents v " +
            "where v.insuranceName = ?1 " +
            "and v.isInsuranceValid = true " +
            "and a.activeStatus = true " +
            "order by a.modifiedDate desc")
    Page<Accident> getInsurranceAccident(String insuranceName, Pageable pageable);


    @Query("select DISTINCT a from Accident a " +
            "left join a.personAccidentList per " +
            "on ((per is not null) " +
            "and per.vehicleNumber = ?2 " +
            "and per.activeStatus = true) "+
            "where a.id = ?1 ")
    @EntityGraph(attributePaths = "personAccidentList")
    Optional<Accident> getAccidentWithValid(Long accidentId, int vehiculeNumber);




}
