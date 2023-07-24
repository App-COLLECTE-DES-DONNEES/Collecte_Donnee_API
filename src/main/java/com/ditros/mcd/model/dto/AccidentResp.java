package com.ditros.mcd.model.dto;

import com.ditros.mcd.model.entity.VehicleAction;
import com.ditros.mcd.model.entity.VehicleBrand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccidentResp {
    private List<AccidentSeverityResp> accidentSeverityResp;
    private List<AccidentTypeResp> accidentTypeResp;
    private List<ActionResp> actionResp;
    private List<BrandResp> brandResp;
    private List<BrightnessConditionResp> brightnessConditionResp;
    private List<ClimaticConditionResp> climaticConditionResp;
    private List<GenderResp> genderResp;
    private List<ImpactTypeResp> impactTypeResp;
    private List<MunicipalityResp> municipalityResp;
    private List<OccupantRestraintSystemResp> occupantRestraintSystemResp;
    private List<PersonRoadTypeResp> personRoadTypeResp;
    private List<RoadBlockResp> blockResp;
    private List<RoadCategoryResp> roadCategoryResp;
    private List<RoadIntersectionResp> roadIntersectionResp;
    private List<RoadResp> roadResp;
    private List<RoadSlopSectionResp> roadSlopSectionResp;
    private List<RoadStateResp> roadStateResp;
    private List<RoadTypeResp> roadTypeResp;
    private List<WearingHelmetResp> wearingHelmetResp;
    private List<VirageResp> virageResp;
    private List<VehicleTypeResp> vehicleTypeResp;
    private List<VehicleModelResp> vehicleModelResp;
    private List<TraumaSeverityResp> traumaSeverityResp;
    private List<TrafficControlResp> controlResp;
    private List<SpecialFunctionResp> specialFunctionResp;
    private List<SeatingPlaceResp> seatingPlaceResp;
    private List<SeatingRangeResp> seatingRangeResp;
    private List<AlcoholTestTypeResp> alcoholTestTypeResp;
    private List<AlcoholTestStatusResp> alcoholTestStatusResp;
    private List<AlcoholConsumptionResp> alcoholConsumptionResp;
    private List<PersonDrugUseResp> personDrugUseResp;
    private List<VehicleActionResp> vehicleActionResp;
    private List<AlcoholTestResultResp> alcoholTestResultResp;
    private List<CityResp> cityResp;
    private List<DirectCauseResp> directCauseResp;
    private List<ProfessionResp> professionResp;
    private List<DocumentTypeResp> documentTypeResp;


}
