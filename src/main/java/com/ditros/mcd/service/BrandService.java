package com.ditros.mcd.service;

import com.ditros.mcd.dao.VehicleBrandDao;
import com.ditros.mcd.model.dto.BrandResp;
import com.ditros.mcd.model.entity.VehicleBrand;
import com.ditros.mcd.model.mappers.BrandMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BrandService {

    private BrandMapper brandMapper;

    private VehicleBrandDao brandDao;

    public List<BrandResp> getAll(String lang) {
        List<VehicleBrand> brands = brandDao.findByLang(lang);

        return brands.stream()
                .map(brand -> brandMapper.fromBrand(brand))
                .collect(Collectors.toList());
    }

}
