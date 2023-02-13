package com.assurance.transfertObject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Produit {

    private String produitId;
    private String libelle;
    private String description;
    private double prix;
    private int delaiValidite;
    private String img;

}
