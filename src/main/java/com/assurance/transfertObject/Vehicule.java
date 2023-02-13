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
public class Vehicule  {

    private String vehiculeId;
    private String typeVehicule;
    private String carrosserie;
    private int placeAssise;
    private String chassis;
    private Date dateMiseEnCirculation;
    private Date premiereMiseEnCirculation;
    private int puissAdmin; // Puissance Administrative
    private int poidsTotalCha; // poids total en charge
    private int poidsVide;
    private int chargeUtile; // charge utile
    private int cylindre; // cm3

    private MarqueVehicule marqueVehicule;


}
