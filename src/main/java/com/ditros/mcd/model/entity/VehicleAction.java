package com.ditros.mcd.model.entity;

import com.ditros.mcd.model.entity.inherited.JournalData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql="UPDATE VehicleAction SET deleted=true where id=?")

public class VehicleAction extends JournalData {

    private Long code;
    private String value;

    @OneToMany(mappedBy = "action")
    private List<VehicleAccident> vehicleAccidentList;
}
