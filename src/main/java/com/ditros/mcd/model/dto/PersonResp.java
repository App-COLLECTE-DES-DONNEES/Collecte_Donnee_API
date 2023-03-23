package com.ditros.mcd.model.dto;

import com.ditros.mcd.model.entity.PersonAccident;
import com.ditros.mcd.model.entity.PersonGender;
import com.ditros.mcd.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor
public class PersonResp {
    private Long id;
    private String firstName;
    private String lastName;
    private String birthDateOms;
    private String birthDate;
    private GenderResp gender;
}
