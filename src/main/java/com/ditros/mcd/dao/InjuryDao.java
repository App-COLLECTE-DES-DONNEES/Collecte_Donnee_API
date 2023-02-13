package com.ditros.mcd.dao;

import com.ditros.mcd.model.entity.Injury;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InjuryDao extends JpaRepository<Injury, Long> {
    @Query("select i from Injury i " +
            "where lower(i.name) LIKE lower(CONCAT('%',:name,'%')) " +
            "and i.activeStatus = true " +
            "and i.lang = :lang " +
            "order by i.createdDate desc")
    List<Injury> getInjuryByName(@Param("name") String name, @Param("lang") String lang);

    Page<Injury> findByActiveStatusTrueAndLangOrderByNameDesc(String lang, Pageable pageable);


}
