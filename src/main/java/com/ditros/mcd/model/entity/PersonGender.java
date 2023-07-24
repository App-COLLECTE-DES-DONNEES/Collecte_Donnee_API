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
@SQLDelete(sql="UPDATE PersonGender SET deleted=true where id=?")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class PersonGender extends JournalData {
    private Long code;
    private String value;

    @OneToMany(mappedBy = "gender")
    private List<Person> person;
}
