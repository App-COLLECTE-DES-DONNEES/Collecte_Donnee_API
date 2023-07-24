package com.ditros.mcd.service;

import com.ditros.mcd.dao.VehicleModelDao;
import com.ditros.mcd.model.dto.VehicleModelResp;
import com.ditros.mcd.model.entity.VehicleModel;
import com.ditros.mcd.model.mappers.VehicleModelMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service @AllArgsConstructor
public class VehicleModelService {

    private VehicleModelMapper vehicleModelMapper;

    private VehicleModelDao vehicleModelDao;

    public List<VehicleModelResp> getAll(String lang) {
        if(lang==null) lang="fr";
        List<VehicleModel> vehicleModels = vehicleModelDao.findByLang(lang);

        return vehicleModels.stream()
                .map(vehicleModel -> vehicleModelMapper.fromVehicleModel(vehicleModel))
                .collect(Collectors.toList());
    }
}
