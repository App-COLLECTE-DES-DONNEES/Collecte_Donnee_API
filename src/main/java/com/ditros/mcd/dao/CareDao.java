package com.ditros.mcd.dao;

import com.ditros.mcd.model.entity.Accident;
import com.ditros.mcd.model.entity.Care;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CareDao extends JpaRepository<Care, Long> {
    List<Care> findByActiveStatusTrueOrderByCreatedDateDesc(Pageable pageable);
    
    @Query("SELECT distinct c from Care c " +
            "join c.organization o " +
            "where o.id in ?1 " +
            "and (?2 = '' " +
            "or ((lower(c.firstname) LIKE lower(CONCAT('%',?2,'%'))) " +
            "or ((lower(c.lastname)) LIKE lower(CONCAT('%',?2,'%'))) " +
            "or ((lower(c.identityCardNumber)) LIKE lower(CONCAT('%',?2,'%')))))" +
            "and c.activeStatus = true " +
            "order by c.modifiedDate desc")
    Page<Care> getCareByOrganization(List<Long> ids, String search, Pageable pageable);


    @Query("select c from Care c " +
            "where ((lower(c.firstname) LIKE lower(CONCAT('%',:name,'%'))) " +
            "or ((lower(c.lastname)) LIKE lower(CONCAT('%',:name,'%'))) " +
            "or ((lower(c.identityCardNumber)) LIKE lower(CONCAT('%',:name,'%'))))" +
            "and c.status = 'OPENED'" +
            "and c.activeStatus = true " +
            "order by c.createdDate desc")
    List<Care> findOpenedCareByName(@Param("name") String name);



    @Query("select c from Care c " +
            "where lower(c.firstname) LIKE lower(CONCAT('%',:name,'%')) " +
            "and (c.status = 'OPENED' OR c.status = 'CLOSED')" +
            "and c.activeStatus = true " +
            "order by c.createdDate desc")
    List<Care> findOpenedOrClosedCareByName(@Param("name") String name);

    @Query("select c from Care c " +
            "where c.personTraumaSeverity = ?1 " +
            "and (c.status = 'OPENED')" +
            "and c.activeStatus = true " +
            "and c.employee.organization.id = ?2 " +
            "order by c.createdDate desc")
    List<Care> findOpenedByTrauma(Long traumaId, Long hostpitalId);




}
