package com.assurance.transfertObject;

import com.assurance.entities.Assurance;
import com.assurance.entities.Organisation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrganisationKanbanViewDTO {
    private String legend;
    private List<ChildKanbanDTO> assurances;
    private int size;
}
