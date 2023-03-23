package com.ditros.mcd.service;

import com.ditros.mcd.dao.WearingHelmetDao;
import com.ditros.mcd.model.dto.WearingHelmetResp;
import com.ditros.mcd.model.entity.WearingHelmet;
import com.ditros.mcd.model.mappers.WearingHelmetMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service @AllArgsConstructor
public class WearingHelmetService {

    private WearingHelmetMapper helmetMapper;
    private WearingHelmetDao helmetDao;

    public List<WearingHelmetResp> getAll(String lang) {
        if(lang==null) lang="fr";
        List<WearingHelmet> wearingHelmets = helmetDao.findByLang(lang);

        return wearingHelmets.stream()
                .map(wearingHelmet -> helmetMapper.fromWearingHelmet(wearingHelmet))
                .collect(Collectors.toList());
    }

}
