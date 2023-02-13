package com.assurance.transfertObject;

import lombok.*;

@Data
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class AssuranceValidityDTO {
    private String insurranceName;
    private boolean valid;
}
