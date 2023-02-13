package com.ditros.mcd.model.entity;


import com.ditros.mcd.model.entity.inherited.JournalData;
import com.ditros.mcd.enumeration.AccidentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@SQLDelete(sql="UPDATE accident SET deleted = true where id=?")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Where(clause="deleted=false")
public class Accident extends JournalData {

    @OneToOne( cascade = CascadeType.ALL)
    private AccidentCode code;

    @Column(nullable = false)
    private String crashDate;

    @Column(nullable = false)
    private String crashTime;

    @Column(nullable = false)
    private String place;

    private double latitude;
    private double longitude;

    @ManyToOne
    private AccidentType accidentType;

    @ManyToOne
    private Municipality municipality;

    @ManyToOne
    private AccidentBrightnessCondition brightnessCondition;
    @ManyToOne
    private AccidentClimaticCondition climaticCondition;
    @ManyToOne
    private RoadTrafficControl trafficControl;
    @ManyToOne
    private AccidentSeverity accidentSeverity;
    @ManyToOne
    private AccidentImpactType impactType;

    @OneToMany(mappedBy = "accident", cascade = CascadeType.ALL)
    private List<VehicleAccident> vehicleAccidents;

    //Road
    @ManyToOne
    private Road road;
    @ManyToOne
    private RoadType roadType;

    @ManyToOne
    private RoadCategory roadCategory;

    @Column(nullable = false)
    private int speedLimit;

    @ManyToOne
    private RoadBlock block;

    @ManyToOne
    private RoadState roadState;

    @ManyToOne
    private RoadIntersection roadIntersection;

    @ManyToOne
    private RoadTrafficControl roadTrafficControl;

    @ManyToOne
    private Virage virage;

    @ManyToOne
    private RoadSlopSection roadSlopSection;

    @ManyToOne
    private User policeAgent;

    @OneToMany(mappedBy = "accident", cascade = CascadeType.ALL)
    private List<PersonAccident> personAccidentList;

    @ManyToOne
    private City city;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(255) default 'OPENED'")
    private AccidentStatus status;

    private LocalDateTime accidentDate;

    @Lob
    private byte[] agentSignature;

    @Lob
    private byte[] managerSignature;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<DirectCause> directCauses;

    @OneToOne(mappedBy = "accident", cascade = CascadeType.ALL)
    private AccidentReport accidentReport;


    @OneToMany(mappedBy = "accident", cascade = CascadeType.ALL)
    private List<AccidentImage> accidentImages;

    private String drawing;

    @ManyToOne
    private Organization organization;


    @Override
    public String toString() {
        return place + " "+ city.getName();
    }




}
