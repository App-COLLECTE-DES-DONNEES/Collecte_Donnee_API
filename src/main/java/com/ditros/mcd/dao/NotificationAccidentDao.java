package com.ditros.mcd.dao;


import com.ditros.mcd.model.entity.Notification;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NotificationAccidentDao extends JpaRepository<Notification, Long> {
      @Query("SELECT distinct a from Notification a " +
            "order by a.createdDate desc")
    Page<Notification> getNotificationByOrganization( String search, Pageable pageable);

}
