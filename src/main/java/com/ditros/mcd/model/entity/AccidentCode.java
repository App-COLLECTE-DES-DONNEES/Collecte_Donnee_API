package com.ditros.mcd.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccidentCode {
    @Id
    @GenericGenerator(name = "code", strategy = "com.ditros.mcd.model.entity.seq.AccidentIdGenerator")
    @GeneratedValue(generator = "code")
    private String code;

    @OneToOne(mappedBy = "code")
    private Accident accident;
}
