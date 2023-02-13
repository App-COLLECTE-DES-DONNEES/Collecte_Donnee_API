package com.assurance.entities;

import com.assurance.entities.config.JournalData;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity @EntityListeners(AuditingEntityListener.class)
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@SQLDelete(sql = "UPDATE organisation SET deleted = true WHERE id=?")
@Where(clause="deleted=false")
public class Organisation extends JournalData {
    @Id
    @GeneratedValue(generator = "UUID")
    @Type(type="uuid-char")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;
    private String nom;
    private String adresse;
    private String tel1;
    private String tel2;
    private boolean parent;
    private String InsuranceName;

    @ManyToOne
    private Organisation parentOrganisation;

    @OneToMany(mappedBy = "parentOrganisation")
    @JsonIgnore
    private Set<Organisation> childOrganisations;

    @OneToMany(mappedBy = "organisation")
    @JsonIgnore
    private Set<Assurance> assurances;

}
