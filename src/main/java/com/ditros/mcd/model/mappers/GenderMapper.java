package com.ditros.mcd.model.mappers;

import com.ditros.mcd.model.dto.GenderResp;
import com.ditros.mcd.model.entity.PersonGender;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class GenderMapper {

    public GenderResp fromGender(PersonGender gender) {
        GenderResp genderResp = new GenderResp();
        BeanUtils.copyProperties(gender, genderResp);

        return genderResp;
    }
}
