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
public class CareCode {
    @Id
    @GenericGenerator(name = "code", strategy = "com.ditros.mcd.model.entity.seq.CareIdGenerator")
    @GeneratedValue(generator = "code")
    private String code;

    @OneToOne(mappedBy = "code")
    private Care care;
}
