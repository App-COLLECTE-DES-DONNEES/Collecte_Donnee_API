package com.ditros.mcd.model.entity;

import com.ditros.mcd.model.entity.inherited.JournalData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@SQLDelete(sql="UPDATE Injury SET deleted=true where id=?")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Injury extends JournalData {
    private String name;
    @OneToMany(mappedBy = "injury")
    private List<CareInjury> careInjuries;
}
