package com.ditros.mcd.model.entity;

import com.ditros.mcd.model.entity.inherited.JournalData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql="UPDATE vehicle_image SET deleted = true where id=?")
@Where(clause="deleted=false")
public class VehicleImage extends JournalData {
    private String name;
   @ManyToOne
    private VehicleAccident vehicleAccident;
}
