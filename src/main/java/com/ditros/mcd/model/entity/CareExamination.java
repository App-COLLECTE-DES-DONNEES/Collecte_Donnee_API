package com.ditros.mcd.model.entity;

import com.ditros.mcd.model.entity.inherited.JournalData;
import com.ditros.mcd.enumeration.InsuranceVisa;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@SQLDelete(sql="UPDATE CareExamination SET deleted=true where id=?")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Where(clause="deleted=false")
public class CareExamination extends JournalData {
    @ManyToOne(cascade = CascadeType.ALL)
    private Care care;

    @ManyToOne(fetch = FetchType.EAGER)
    private Examination examination;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(255) default 'PENDING'")
    private InsuranceVisa insuranceVisa;

    private LocalDateTime date;
}
