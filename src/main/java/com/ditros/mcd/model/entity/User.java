package com.ditros.mcd.model.entity;

import com.ditros.mcd.model.entity.inherited.JournalData;
import com.ditros.mcd.enumeration.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@SQLDelete(sql="UPDATE user SET deleted=true where id=?")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Where(clause="deleted=false")
public class User extends JournalData {
    private String keycloakId;
    @OneToOne
    private Person person;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(255) default 'EXECUTIVE'")
    private UserType type;
    @ManyToOne
    private Organization organization;
}