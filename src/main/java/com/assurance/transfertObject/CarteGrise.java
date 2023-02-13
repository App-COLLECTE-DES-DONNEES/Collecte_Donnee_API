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
public class CarteGrise  {

    private String carteGriseId;

    private String numImmatriculation;

    private String preImmatriculation;// immatriculation précédente

    private Date dateDebutValid; // debut de validité

    private Date dateFinValid;// fin de validité
    private String ssdt_id;
    private String commune;
    private double montantPaye;
    private boolean vehiculeGage; // véhicule gagé
    private String genreVehicule;
    private String enregistrement;

    private Date dateDelivrance;
    private String lieuDedelivrance;// lieu de délivrance
    private String centre_ssdt;

    private ProprietaireVehicule proprietaireVehicule;

    private Vehicule vehicule;

    private Produit produit;

}
