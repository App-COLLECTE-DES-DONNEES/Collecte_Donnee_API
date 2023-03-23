package com.ditros.mcd.service;

import com.ditros.mcd.dao.SeatingRangeDao;
import com.ditros.mcd.model.dto.SeatingRangeResp;
import com.ditros.mcd.model.entity.SeatingRange;
import com.ditros.mcd.model.mappers.SeatingRangeMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SeatingRangeService {

    private SeatingRangeMapper seatingRangeMapper;
    private SeatingRangeDao seatingRangeDao;

    public List<SeatingRangeResp> getAll(String lang) {
        if(lang==null) lang="fr";
        List<SeatingRange> seatingRanges = seatingRangeDao.findByLang(lang);

        return seatingRanges.stream()
                .map(roadType -> seatingRangeMapper.fromRange(roadType))
                .collect(Collectors.toList());
    }
}
