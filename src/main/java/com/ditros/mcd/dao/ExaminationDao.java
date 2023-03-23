package com.ditros.mcd.dao;

import com.ditros.mcd.model.entity.Examination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExaminationDao extends JpaRepository<Examination, Long> {
    @Query("select e from Examination e " +
            "where lower(e.name) LIKE lower(CONCAT('%',:name,'%')) " +
            "and e.activeStatus = true " +
            "and e.lang = :lang " +
            "order by e.createdDate desc")
    List<Examination> getExaminationByName(@Param("name") String name, @Param("lang") String lang);

    Page<Examination> findByActiveStatusTrueAndLangOrderByNameDesc(String lang, Pageable pageable);
}
