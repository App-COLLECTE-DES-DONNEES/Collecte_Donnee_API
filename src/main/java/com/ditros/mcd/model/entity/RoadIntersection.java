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
@SQLDelete(sql="UPDATE Intersection SET deleted=true where id=?")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class RoadIntersection extends JournalData {
    private long code;
    private String value;

    @OneToMany(mappedBy = "roadIntersection")
    private List<Accident> accidents;
}
