package com.ditros.mcd.model.mappers;

import com.ditros.mcd.model.dto.PersonResp;
import com.ditros.mcd.model.dto.PersonRoadTypeResp;
import com.ditros.mcd.model.entity.Person;
import com.ditros.mcd.model.entity.PersonRoadType;
import com.ditros.mcd.util.DateUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PersonMapper {
    private GenderMapper genderMapper;
    public PersonResp fromPerson(Person person) {

        if(person==null) return null;
        PersonResp personResp = new PersonResp();
        personResp.setId(person.getId());
        personResp.setFirstName(person.getFirstName());
        personResp.setLastName(person.getLastName());
        personResp.setBirthDateOms(person.getBirthDateOms());
        personResp.setBirthDate(DateUtil.textFromDate("yyyy-MM-dd", person.getBirthDate()));
        personResp.setGender(genderMapper.fromGender(person.getGender()));

        return personResp;
    }
}
