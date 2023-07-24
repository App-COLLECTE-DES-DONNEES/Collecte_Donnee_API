package com.ditros.mcd.dao;

import com.ditros.mcd.model.entity.CareInjury;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface CareInjuryDao extends JpaRepository<CareInjury, Long> {
    @Query("select t from CareInjury t " +
    "where t.care.id=:id " +
    "and t.injury.id=:id1Long "+
    "and t.activeStatus = true " +
    "order by t.createdDate desc")
    List<CareInjury> getidCareInjuryByIdCare(@Param("id") Long id, @Param("id1Long") Long id1Long);
  
}
