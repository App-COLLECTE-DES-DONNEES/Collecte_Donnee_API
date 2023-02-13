package com.ditros.mcd.service;

import com.ditros.mcd.dao.AlcoholTestStatusDao;
import com.ditros.mcd.dao.VehicleActionDao;
import com.ditros.mcd.model.dto.AlcoholTestStatusResp;
import com.ditros.mcd.model.dto.VehicleActionResp;
import com.ditros.mcd.model.entity.AlcoholTestStatus;
import com.ditros.mcd.model.entity.VehicleAction;
import com.ditros.mcd.model.mappers.AlcoholTestStatusMapper;
import com.ditros.mcd.model.mappers.VehicleActionMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class VehicleActionService {
    private VehicleActionMapper vehicleActionMapper;
    private VehicleActionDao vehicleActionDao;

    public List<VehicleActionResp> getAll(String lang) {
        if(lang==null) lang="fr";
        List<VehicleAction> actions = vehicleActionDao.findByLang(lang);

        return actions.stream()
                .map(action -> vehicleActionMapper.fromVehicleAction(action))
                .collect(Collectors.toList());
    }
}
