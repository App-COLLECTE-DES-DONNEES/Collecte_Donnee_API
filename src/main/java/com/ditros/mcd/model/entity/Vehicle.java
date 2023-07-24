package com.ditros.mcd.model.entity;

import com.ditros.mcd.model.entity.inherited.JournalData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@SQLDelete(sql="UPDATE vehicle SET deleted=true where id=?")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Vehicle extends JournalData {
    private String chassis;
    private Long cylinderCapacity;
    private Long fabricationYear;

    @ManyToOne
    private VehicleType type;
    @ManyToOne
    private VehicleBrand brand;

    @ManyToOne
    private VehicleModel model;

    @OneToMany(mappedBy = "vehicle")
    private List<VehicleAccident> vehicleAccidentList;
    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
    private List<DocumentVehicle> documents;

}
