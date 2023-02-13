package com.ditros.mcd.service;

import com.ditros.mcd.dao.PersonActionDao;
import com.ditros.mcd.model.dto.ActionResp;
import com.ditros.mcd.model.entity.PersonAction;
import com.ditros.mcd.model.mappers.ActionMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ActionService {

    private ActionMapper actionMapper;
    private PersonActionDao actionDao;

    public List<ActionResp> getAll(String lang) {
        if(lang==null) lang="fr";
        List<PersonAction> actions = actionDao.findByLang(lang);
        return actions.stream()
                .map(action -> actionMapper.fromAction(action))
                .collect(Collectors.toList());
    }
}
