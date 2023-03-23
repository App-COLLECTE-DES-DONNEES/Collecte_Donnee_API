package com.ditros.mcd.model.entity;

import com.ditros.mcd.model.entity.inherited.JournalData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@SQLDelete(sql="UPDATE accident_report SET deleted=true where id=?")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class AccidentReport extends JournalData {

    private String authors;
    private String assist;
    @Column(columnDefinition = "TEXT")
    private String saw;
    @Column(columnDefinition = "TEXT")
    private String circulation;
    private String patrol;
    private boolean printed = false;
    @OneToOne
    private Accident accident;
}
