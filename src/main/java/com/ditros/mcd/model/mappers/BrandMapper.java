package com.ditros.mcd.model.mappers;

import com.ditros.mcd.model.dto.BrandResp;
import com.ditros.mcd.model.entity.VehicleBrand;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class BrandMapper {

    public BrandResp fromBrand(VehicleBrand brand) {
        BrandResp brandResp = new BrandResp();
        BeanUtils.copyProperties(brand, brandResp);

        return brandResp;
    }
}
