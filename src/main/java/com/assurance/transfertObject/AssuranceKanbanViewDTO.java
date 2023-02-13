package com.assurance.transfertObject;

import com.assurance.entities.Assurance;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AssuranceKanbanViewDTO {
    private String legend;
    private List<ChildKanbanDTO> assurances;
    private int size;
}
