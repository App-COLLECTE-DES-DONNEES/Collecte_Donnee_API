package com.ditros.mcd.model.entity;

import com.ditros.mcd.model.entity.inherited.JournalData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Entity
@SQLDelete(sql="UPDATE region SET deleted=true where id=?")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Where(clause="deleted=false")
public class Region extends JournalData {

    private String code;
    private String name;
    @ManyToOne
    private Country country;
    @OneToMany(mappedBy = "region")
    private List<Department> departments;
}
