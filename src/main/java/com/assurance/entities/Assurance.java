package com.assurance.entities;

import com.assurance.transfertObject.AssurancePOJO;
import com.assurance.entities.config.JournalData;
import com.assurance.transfertObject.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@Entity @EntityListeners(AuditingEntityListener.class)
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@SQLDelete(sql = "UPDATE assurance SET deleted = true WHERE id=?")
@Where(clause="deleted=false")
public class Assurance extends JournalData {
    @Id
    @GeneratedValue(generator = "UUID")
    @Type(type="uuid-char")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;
    private String police;
    private String name;
    private String souscripteur;
    private String pf;
    private double primeAssurance;
    @ManyToOne
    private Organisation organisation;
    private String vehicule;
    private LocalDateTime date_debut;
    private LocalDateTime date_fin;
    private String pdfPath;

    private String customerFirstName;
    private String customerLastName;
    private String customerEmail;
    private String customerPhone;
    private String customerCni;
    private String customerBirthdate;
    private String chassis;
    private String cat;
    private String marque;
    private String description;


    public Assurance(AssurancePOJO assurancePOJO){
        this.id = assurancePOJO.getId();
        this.police = assurancePOJO.getPolice();
        this.name = assurancePOJO.getName();
        this.souscripteur = assurancePOJO.getSouscripteur();
        this.pf = assurancePOJO.getPf();
        this.primeAssurance = assurancePOJO.getPrimeAssurance();
        this.vehicule = assurancePOJO.getVehicule();
        this.date_debut = DateUtil.DateDayFromText("yyyy-MM-dd", assurancePOJO.getDate_debut()).atStartOfDay();
        this.date_fin = DateUtil.DateDayFromText("yyyy-MM-dd", assurancePOJO.getDate_fin()).plusDays(1L).atStartOfDay();
        this.organisation = assurancePOJO.getOrganisation();
        this.customerBirthdate = assurancePOJO.getCustomer().getBirthdate();
        this.customerCni = assurancePOJO.getCustomer().getCni();
        this.customerPhone = assurancePOJO.getCustomer().getPhone();
        this.customerEmail = assurancePOJO.getCustomer().getEmail();
        this.customerFirstName = assurancePOJO.getCustomer().getFirstName();
        this.customerLastName = assurancePOJO.getCustomer().getLastName();
        this.cat = assurancePOJO.getCat();
        this.chassis = assurancePOJO.getChassis();
        this.marque = assurancePOJO.getMarque();
        this.description = assurancePOJO.getDescription();
    }

}
