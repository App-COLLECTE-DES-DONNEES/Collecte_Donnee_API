package com.ditros.mcd.model.entity;


import com.ditros.mcd.model.entity.inherited.JournalData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.util.List;

@Entity
@SQLDelete(sql="UPDATE Department SET deleted=true where id=?")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Department extends JournalData {

    private String code;
    private String name;
    @ManyToOne
    private Region region;

    @OneToMany(mappedBy = "department")
    private List<Municipality> municipalities;
}
