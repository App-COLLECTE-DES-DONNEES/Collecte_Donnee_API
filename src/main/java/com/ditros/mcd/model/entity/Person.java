package com.ditros.mcd.model.entity;

import com.ditros.mcd.model.entity.inherited.JournalData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import javax.print.Doc;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@SQLDelete(sql="UPDATE person SET deleted=true where id=?")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Person extends JournalData {
    private String firstName;
    private String lastName;
    private String identityNumber;
    private String phone;
    private String birthDateOms;
    private LocalDateTime birthDate;
    private String address;
    private String street;
    private String postal;
    private String lang;
    private String tel1;
    private String tel2;
    private double latitude;
    private double longitude;

    @ManyToOne
    private PersonGender gender;

    @OneToOne(mappedBy = "person")
    private User user;
    @OneToMany(mappedBy ="person")
    private List<PersonAccident> personAccidentList;
    @OneToMany(mappedBy ="person", cascade = CascadeType.ALL)
    private List<DocumentPerson> documents;
}
