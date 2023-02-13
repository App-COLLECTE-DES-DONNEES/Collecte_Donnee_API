package com.ditros.mcd.model.dto;

import com.ditros.mcd.model.entity.DocumentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor @NoArgsConstructor
public class DocumentReq {
    private Long id;
    private String identification;
    private String recipientFirstname;
    private String recipientLastname;
    private String issueDate;
    private String expireAt;
    private String image;
    private Long type;
    private Long person;
    private Long vehicle;
}
