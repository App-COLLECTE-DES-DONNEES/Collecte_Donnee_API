package com.assurance.transfertObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class CustomerReq {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String cni;
    private String birthdate;
    private String occupation;
}
