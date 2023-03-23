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
@SQLDelete(sql="UPDATE road_block SET deleted=true where id=?")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Where(clause="deleted=false")
public class RoadBlock extends JournalData {
    private Long code;
    private String value;

    @OneToMany(mappedBy = "block")
    private List<Accident> accidents;
}
