package com.ditros.mcd.model.entity;

import com.ditros.mcd.model.entity.inherited.JournalData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@SQLDelete(sql="UPDATE accident_type SET deleted=true where id=?")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Where(clause="deleted=false")
public class AccidentType extends JournalData {
    private Long code;
    private String value;

    @OneToMany(mappedBy = "accidentType")
    private List<Accident> accidents;

}
