package com.ditros.mcd.model.entity;

import com.ditros.mcd.model.entity.inherited.JournalData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Entity @SQLDelete(sql="UPDATE person_accident SET deleted=true where id=?")
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Where(clause="deleted=false")
public class PersonAccident extends JournalData {

    private int personNumber;
    private int vehicleNumber;
    private int vehicleLinkedPedestrian;
    private String drivingLicenceYear;
    private int age;

    @ManyToOne
    private Accident accident;
    @ManyToOne
    private PersonRoadType roadType;
    @ManyToOne
    private PersonAlcoholConsumption alcoholConsumption;
    @ManyToOne
    private AlcoholTestStatus testStatus;
    private double testValue;
    @ManyToOne
    private AlcoholTestType testType;
    @ManyToOne
    private AlcoholTestResult testResult;
    @ManyToOne
    private PersonDrugUse drugUse;
    @ManyToOne
    private SeatingRange range;
    @ManyToOne
    private SeatingPlace place;
    @ManyToOne
    private PersonTraumaSeverity traumaSeverity;
    @ManyToOne
    private PersonAction action;
    @ManyToOne
    private WearingHelmet wearingHelmet;
    @ManyToOne
    private OccupantRestraintSystem occupantRestraintSystem;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Person person;
    @OneToOne
    private Care care;

    @OneToMany(mappedBy = "personAccident", cascade = CascadeType.ALL)
    private List<PersonImage> personImages;

    @ManyToOne
    private Profession profession;

    private String drivingLicence;
    private String drivingLicenceType;

}
