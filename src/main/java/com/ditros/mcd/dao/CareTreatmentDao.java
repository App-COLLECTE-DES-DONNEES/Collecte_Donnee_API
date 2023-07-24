package com.ditros.mcd.dao;

import com.ditros.mcd.model.entity.CareTreatment;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface CareTreatmentDao extends JpaRepository<CareTreatment, Long> {
    @Query("select t from CareTreatment t " +
            "where t.care.id=:id " +
            // "and t.treatment.id=:id1 "+
            "and t.activeStatus = true " +
            "order by t.createdDate desc")
    List<CareTreatment> getCareTreatmentByIdCare(@Param("id") Long id);
    @Query("select t from CareTreatment t " +
    "where t.care.id=:id " +
    "and t.treatment.id=:id1Long "+
    "and t.activeStatus = true " +
    "order by t.createdDate desc")
    List<CareTreatment> getidCareTreatmentByIdCare(@Param("id") Long id, @Param("id1Long") Long id1Long);
  
    
}
