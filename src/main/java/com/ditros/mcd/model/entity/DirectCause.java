package com.ditros.mcd.model.entity;

import com.ditros.mcd.model.entity.inherited.JournalData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
@SQLDelete(sql="UPDATE direct_cause SET deleted=true where id=?")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class DirectCause extends JournalData {
    private String name;
    @ManyToMany(mappedBy = "directCauses")
    private List<Accident> accidents;
}
