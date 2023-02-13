package com.ditros.mcd.model.entity;

import com.ditros.mcd.model.entity.inherited.JournalData;
import com.ditros.mcd.enumeration.CareStatus;
import com.ditros.mcd.enumeration.InsuranceVisa;
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
@SQLDelete(sql="UPDATE Care SET deleted=true where id=?")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Where(clause="deleted=false")
public class Care extends JournalData {
    private LocalDateTime crashDate;
    private String firstname;
    private String lastname;
    @OneToOne(cascade = CascadeType.ALL)
    private CareCode code;
    private LocalDateTime birthDate;
    @ManyToOne
    private User employee;

    @OneToOne(mappedBy = "care")
    private PersonAccident personAccident;
    @OneToOne
    private PersonTraumaSeverity personTraumaSeverity;
    @OneToOne
    private PersonGender personGender;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(255) default 'OPENED'")
    private CareStatus status;

    @OneToMany(mappedBy = "care", cascade = CascadeType.ALL)
    private List<CareTreatment> treatments;

    @OneToMany(mappedBy = "care", cascade = CascadeType.ALL)
    private List<CareInjury> injuries;

    @OneToMany(mappedBy = "care", cascade = CascadeType.ALL)
    private List<CareExamination> examinations;

    private String identityCardNumber;
    private String driveLicenceNumber;
    private String passportNumber;
    private String telephone;
    private boolean consumalcohol;
    private boolean consumdrugs;

    private String weigth;
    private String temperature;
    private String pulse;
    private String tension;
    private String params;
    private String description;

    @OneToMany(cascade = CascadeType.ALL)
    private List<CareContact> careContact;
    @ManyToOne
    private Organization organization;
    private String plate;
    public double getTreatmentsCost(){
        if(this.getTreatments() == null)
            return 0;
        double sum = 0;
        for(CareTreatment treatment:this.getTreatments() ){
            sum += treatment.getTreatment().getPrice();
        }
        return sum;
    }

    public double getExaminationsCost(){
        if(this.getExaminations() == null)
            return 0;
        double sum = 0;
        for(CareExamination e:this.getExaminations() ){
            sum += e.getExamination().getPrice();
        }
        return sum;
    }

    public double getAmountAccepted(){
        double sum = 0;
        if(this.getExaminations() != null){
            for(CareExamination e:this.getExaminations() ){
                if(e.getInsuranceVisa().equals(InsuranceVisa.ACCEPTED))
                    sum += e.getExamination().getPrice();
            }
        }
        if(this.getTreatments() != null){
            for(CareTreatment t : this.getTreatments() ){
                if(t.getInsuranceVisa().equals(InsuranceVisa.ACCEPTED))
                    sum += t.getTreatment().getPrice();
            }
        }
        return sum;
    }
}
