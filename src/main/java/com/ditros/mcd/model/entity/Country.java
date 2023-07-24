package com.ditros.mcd.model.entity;


import javax.persistence.*;

import com.ditros.mcd.model.entity.inherited.JournalData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.Entity;
import java.util.List;


@Entity
@SQLDelete(sql="UPDATE Country SET deleted=true where id=?")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Country extends JournalData {
    private Long countryPhoneCode;
    private String currency;
    private String name;

    @OneToMany(mappedBy = "country")
    private List<Region> regions;

    @OneToMany(mappedBy = "country")
    private List<City> cities;

}
