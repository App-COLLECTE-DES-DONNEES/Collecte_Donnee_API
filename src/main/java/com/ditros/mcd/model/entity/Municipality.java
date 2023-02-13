package com.ditros.mcd.model.entity;


import com.ditros.mcd.model.entity.inherited.JournalData;
import lombok.*;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@SQLDelete(sql="UPDATE Municipality SET deleted=true where id=?")
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class Municipality extends JournalData {
    private String code;
    private String name;

    @OneToMany(mappedBy = "municipality")
    private List<Accident> accidents;

    @ManyToOne
    private Department department;

}
