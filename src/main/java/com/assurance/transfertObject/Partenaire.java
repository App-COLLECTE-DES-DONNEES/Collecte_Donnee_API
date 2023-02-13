package com.assurance.transfertObject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Partenaire {


    private String partenaireId;
    private String nom;
    private String prenom;

    private Date dateNaiss;
    private String lieuDeNaiss; // lieu de naissance
    private String passport;

    private String permiDeConduire;

    private String cni;

    private String telephone;

    private String email;

    private String numeroContribuable;

}
