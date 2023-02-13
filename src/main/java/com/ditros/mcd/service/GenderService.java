package com.ditros.mcd.service;

import com.ditros.mcd.dao.PersonGenderDao;
import com.ditros.mcd.model.dto.GenderResp;
import com.ditros.mcd.model.entity.PersonGender;
import com.ditros.mcd.model.mappers.GenderMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GenderService {

    private GenderMapper genderMapper;

    private PersonGenderDao genderDao;

    public List<GenderResp> getAll(String lang) {
        if(lang==null) lang="fr";
        List<PersonGender> genders = genderDao.findByLang(lang);

        return genders.stream()
                .map(gender -> genderMapper.fromGender(gender))
                .collect(Collectors.toList());
    }
}
