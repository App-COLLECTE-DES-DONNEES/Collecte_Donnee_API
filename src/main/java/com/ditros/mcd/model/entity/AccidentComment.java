package com.ditros.mcd.model.entity;

import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.SQLDelete;

import com.ditros.mcd.model.entity.inherited.JournalData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@SQLDelete(sql="UPDATE AccidentComment SET deleted = true where id=?")

@AllArgsConstructor
@NoArgsConstructor
public class AccidentComment extends JournalData {
    
    
    private String user;
    private String Content;
    @ManyToOne
    private Accident accident;
   
}