package com.ditros.mcd.model.mappers;

import com.ditros.mcd.model.dto.*;
import com.ditros.mcd.model.entity.Care;
import com.ditros.mcd.model.entity.CareContact;
import com.ditros.mcd.model.entity.Person;
import com.ditros.mcd.service.AccidentService;
import com.ditros.mcd.util.DateUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sun.util.calendar.LocalGregorianCalendar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CareMapper {
    private AccidentService accidentService;

    public CareResp fromCare(Care care){
        CareResp careResp = new CareResp();
        careResp.setNom(care.getLastname());
        careResp.setPrenom(care.getFirstname());
        careResp.setCode(care.getCode()==null? "":care.getCode().getCode());
        careResp.setTelephone(care.getTelephone());
        careResp.setDateNaiss(DateUtil.textFromDate("yyyy-MM-dd", care.getBirthDate()));
        careResp.setPassport(care.getPassportNumber());
        careResp.setPermis(care.getDriveLicenceNumber());
        careResp.setAccparams(new AccParamReq(care.getPersonTraumaSeverity().getId().toString(), care.isConsumalcohol()?"oui":"non", care.isConsumdrugs()?"oui":"non"));
        careResp.setParametre(new CareParamReq(care.getWeigth(), care.getTemperature(), care.getPulse(), care.getTension(), care.getParams()==null? new ArrayList<>() : Arrays.asList(care.getParams().split(",", -1))));
        careResp.setCni(care.getIdentityCardNumber());
        careResp.setGender(care.getPersonGender().getValue());
        careResp.setCrashDate(DateUtil.textFromDate("yyyy-MM-dd HH:mm", care.getCrashDate()));
        careResp.setStatus(care.getStatus());
        careResp.setId(care.getId());
        careResp.setAmount(care.getExaminationsCost() + care.getTreatmentsCost());
        careResp.setHopital(care.getEmployee().getOrganization().getName());
        careResp.setAmountAccepted(care.getAmountAccepted());
        careResp.setTrauma(care.getPersonTraumaSeverity().getValue());
        careResp.setInsurance(accidentService.getInsuranceName(care.getPersonAccident()));
        careResp.setAccidentRole(care.getPersonAccident()==null? "UNKNOW" : care.getPersonAccident().getRoadType().getValue());
        careResp.setDescription(care.getDescription());
        return careResp;
    }
    public CareFolderResp fromCareFolder(Care care){
        CareFolderResp careResp = new CareFolderResp();
        careResp.setNom(care.getLastname());
        careResp.setPrenom(care.getFirstname());
        careResp.setCode(care.getCode()==null? "":care.getCode().getCode());
        careResp.setTelephone(care.getTelephone());
        careResp.setDateNaiss(DateUtil.textFromDate("yyyy-MM-dd", care.getBirthDate()));
        careResp.setPassport(care.getPassportNumber());
        careResp.setPermis(care.getDriveLicenceNumber());
        careResp.setAccparams(new AccParamReq(care.getPersonTraumaSeverity().getId().toString(), care.isConsumalcohol()?"oui":"non", care.isConsumdrugs()?"oui":"non"));
        careResp.setParametre(new CareParamReq(care.getWeigth(), care.getTemperature(), care.getPulse(), care.getTension(), care.getParams()==null? new ArrayList<>() : Arrays.asList(care.getParams().split(",", -1))));
        careResp.setCni(care.getIdentityCardNumber());
        careResp.setGender(care.getPersonGender().getId());
        careResp.setCrashDate(DateUtil.textFromDate("yyyy-MM-dd HH:mm", care.getCrashDate()));
        careResp.setStatus(care.getStatus());
        careResp.setId(care.getId());
        careResp.setAmountAccepted(care.getAmountAccepted());
        careResp.setContacts(
                care.getCareContact().stream().map(careContact -> {
                    ContactReq c = new ContactReq(careContact.getId(),
                            careContact.getPerson().getFirstName(),
                            careContact.getPerson().getLastName(),
                            careContact.getPerson().getIdentityNumber(),
                            null,
                            careContact.getPerson().getPhone(),
                            careContact.getPerson().getGender() ==null? 0 : careContact.getPerson().getGender().getId(),
                            careContact.getRelationShip());
                    c.setId(careContact.getId());
                    return c;
                }).collect(Collectors.toList())
        );
        careResp.setInsurance(accidentService.getInsuranceName(care.getPersonAccident()));
        careResp.setDescription(care.getDescription());
        return careResp;
    }

}
