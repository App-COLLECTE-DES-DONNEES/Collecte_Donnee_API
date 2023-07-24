package com.ditros.mcd.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@SQLDelete(sql="UPDATE document_person SET deleted=true where id=?")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Where(clause="deleted=false")
public class DocumentPerson extends Document{
    @ManyToOne
    private Person person;
}
