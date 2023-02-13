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
@SQLDelete(sql="UPDATE OccupantRestraintSystem SET deleted=true where id=?")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OccupantRestraintSystem extends JournalData {
    private Long code;
    private String value;
    @OneToMany(mappedBy = "occupantRestraintSystem")
    private List<PersonAccident> personAccidents;
}
