package com.ditros.mcd.model.entity;

import com.ditros.mcd.model.entity.inherited.JournalData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@SQLDelete(sql="UPDATE VehicleModel SET deleted=true where id=?")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VehicleModel extends JournalData {
    private Long code;
    private String value;

    @ManyToOne
    private VehicleBrand  vehiculeBrand;
}
