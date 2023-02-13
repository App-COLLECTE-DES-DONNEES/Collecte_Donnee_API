package com.ditros.mcd.model.entity;

import com.ditros.mcd.model.entity.inherited.JournalData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@SQLDelete(sql="UPDATE InsuranceContract SET deleted=true where id=?")
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class InsuranceContract extends JournalData {
    private String contractNumber;
    private String name;
    private String subscriber;
    private String pf;
    private double primeAssurance;
    private String numberPlate;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    @ManyToOne
    private Vehicle vehicle;
}
