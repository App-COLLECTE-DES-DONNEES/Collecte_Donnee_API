package com.ditros.mcd.model.entity;


import com.ditros.mcd.model.entity.inherited.JournalData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.Entity;

import javax.persistence.OneToOne;


@Entity

@Getter @Setter
@SQLDelete(sql="UPDATE Notification SET deleted=true where id=?")
@AllArgsConstructor @NoArgsConstructor
public class Notification extends JournalData{

private String Date;
private String hours;
private String city;
private Statut status;
private String imageString;
private String description;

public enum Statut {
        ALLOWED,
        DISABLED
    
    }



}
