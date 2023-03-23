package com.ditros.mcd.service;

import com.ditros.mcd.dao.VehicleTypeDao;
import com.ditros.mcd.model.dto.VehicleTypeResp;
import com.ditros.mcd.model.entity.VehicleType;
import com.ditros.mcd.model.mappers.VehicleTypeMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service @AllArgsConstructor
public class VehicleTypeService {

    private VehicleTypeMapper vehicleTypeMapper;
    private VehicleTypeDao vehicleTypeDao;

    public List<VehicleTypeResp> getAll(String lang) {
        if(lang==null) lang="fr";
        List<VehicleType> vehicleTypes = vehicleTypeDao.findByLang(lang);

        return vehicleTypes.stream()
            .map(vehicleType -> vehicleTypeMapper.fromVehicleModel(vehicleType))
            .collect(Collectors.toList());
    }
}
