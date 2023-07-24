package com.ditros.mcd.dao;

import com.ditros.mcd.model.entity.Examination;
import com.ditros.mcd.model.entity.Treatment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TreatmentDao extends JpaRepository<Treatment, Long> {
    @Query("select t from Treatment t " +
            "where lower(t.name) LIKE lower(CONCAT('%',:name,'%')) " +
            "and t.activeStatus = true " +
            "and t.lang = :lang " +
            "order by t.createdDate desc")
    List<Treatment> getTreatmentByName(@Param("name") String name, @Param("lang") String lang);

    Page<Treatment> findByActiveStatusTrueAndLangOrderByNameDesc(String lang, Pageable pageable);
}
