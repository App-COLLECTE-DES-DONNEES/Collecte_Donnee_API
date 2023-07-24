package com.ditros.mcd.service;

import com.ditros.mcd.dao.SeatingPlaceDao;
import com.ditros.mcd.model.dto.SeatingPlaceResp;
import com.ditros.mcd.model.entity.SeatingPlace;
import com.ditros.mcd.model.mappers.SeatingPlaceMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SeatingPlaceService {
    private SeatingPlaceMapper seatingPlaceMapper;
    private SeatingPlaceDao seatingPlaceDao;

    public List<SeatingPlaceResp> getAll(String lang) {
        if(lang==null) lang="fr";
        List<SeatingPlace> seatingRanges = seatingPlaceDao.findByLang(lang);

        return seatingRanges.stream()
                .map(roadType -> seatingPlaceMapper.fromPlace(roadType))
                .collect(Collectors.toList());
    }
}
