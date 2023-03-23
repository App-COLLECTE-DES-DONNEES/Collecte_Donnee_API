package com.ditros.mcd.model.entity;


import com.ditros.mcd.model.entity.inherited.JournalData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@SQLDelete(sql="UPDATE vehicle_accident SET deleted=true where id=?")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Where(clause="deleted=false")
public class VehicleAccident extends JournalData {

    private Long vehicleAccidentNumber;
    private String plateNumber;

    @ManyToOne
    private VehicleSpecialFunction specialFunction;

    @ManyToOne(cascade = CascadeType.ALL)
    private Vehicle vehicle;

    @ManyToOne
    private VehicleAction action;

    @ManyToOne
    private Accident accident;

    private String insuranceName;

    private boolean isInsuranceValid;

    @OneToMany(mappedBy = "vehicleAccident", cascade = CascadeType.ALL)
    private List<VehicleImage> vehicleImages;



}
