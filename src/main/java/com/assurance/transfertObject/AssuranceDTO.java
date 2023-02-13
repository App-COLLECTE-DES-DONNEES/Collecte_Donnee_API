package com.assurance.transfertObject;

import com.assurance.entities.Assurance;
import com.assurance.entities.Organisation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AssuranceDTO {
    private UUID id;
    private String police;
    private String name;
    private String souscripteur;
    private String pf;
    private double primeAssurance;
    private Organisation organisation;
    private String vehicule;
    private String date_debut;
    private String date_fin;
    @JsonIgnore
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
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
    public AssuranceDTO(Assurance assurance){
        this.id = assurance.getId();
        this.police = assurance.getPolice();
        this.name = assurance.getName();
        this.souscripteur = assurance.getSouscripteur();
        this.pf = assurance.getPf();
        this.primeAssurance = assurance.getPrimeAssurance();
        this.vehicule = assurance.getVehicule();
        this.date_debut = assurance.getDate_debut().format(formatter);
        this.date_fin = assurance.getDate_fin().format(formatter);
        this.organisation =assurance.getOrganisation();
        this.organisation = assurance.getOrganisation();
        this.customerBirthdate = assurance.getCustomerBirthdate();
        this.customerCni = assurance.getCustomerCni();
        this.customerPhone = assurance.getCustomerPhone();
        this.customerEmail = assurance.getCustomerEmail();
        this.customerFirstName = assurance.getCustomerFirstName();
        this.customerLastName = assurance.getCustomerLastName();
        this.chassis = assurance.getChassis();
        this.cat = assurance.getCat();
        this.marque = assurance.getMarque();
        this.description = assurance.getDescription();
    }
}
