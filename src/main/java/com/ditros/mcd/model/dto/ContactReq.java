package com.ditros.mcd.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ContactReq {
    private Long id;
    private String firstName;
    private String lastName;
    private String cni;
    private String telephone;
    private Long gender;
    private String filiation;
}
