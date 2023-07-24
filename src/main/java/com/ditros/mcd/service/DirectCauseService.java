package com.ditros.mcd.service;

import com.ditros.mcd.dao.DirectCauseDao;
import com.ditros.mcd.model.dto.ActionResp;
import com.ditros.mcd.model.dto.DirectCauseResp;
import com.ditros.mcd.model.entity.DirectCause;
import com.ditros.mcd.model.entity.PersonAction;
import com.ditros.mcd.model.mappers.DirectCauseMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DirectCauseService {
    private DirectCauseMapper directCauseMapper;
    private DirectCauseDao directCauseDao;

    public List<DirectCauseResp> getAll(String lang) {
        if(lang==null) lang="fr";
        List<DirectCause> directCauses = directCauseDao.findByLang(lang);
        return directCauses.stream()
                .map(action -> directCauseMapper.fromDirectCause(action))
                .collect(Collectors.toList());
    }
}
