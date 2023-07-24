package com.ditros.mcd.service;

import com.ditros.mcd.dao.VirageDao;
import com.ditros.mcd.model.dto.VirageResp;
import com.ditros.mcd.model.entity.Virage;
import com.ditros.mcd.model.mappers.VirageMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service @AllArgsConstructor
public class VirageService {

    private VirageMapper virageMapper;
    private VirageDao virageDao;

    public List<VirageResp> getAll(String lang) {
        if(lang==null) lang="fr";
        List<Virage> virages = virageDao.findByLang(lang);

        return virages.stream()
                .map(virage -> virageMapper.fromVirage(virage))
                .collect(Collectors.toList());
    }
}
