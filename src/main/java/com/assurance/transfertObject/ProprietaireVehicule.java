package com.assurance.transfertObject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ProprietaireVehicule  {

    private String proprietaireVehiculeId;
    private Partenaire partenaire;
    private String description;

}
