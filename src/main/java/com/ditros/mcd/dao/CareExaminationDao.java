package com.ditros.mcd.dao;

import com.ditros.mcd.model.entity.CareExamination;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface CareExaminationDao extends JpaRepository<CareExamination, Long> {
    @Query("select t from CareExamination t " +
    "where t.care.id=:id " +
    "and t.examination.id=:id1Long "+
    "and t.activeStatus = true " +
    "order by t.createdDate desc")
    List<CareExamination> getidCareExaminationByIdCare(@Param("id") Long id, @Param("id1Long") Long id1Long);
  
}