package com.ditros.mcd.controller;

import com.ditros.mcd.model.dto.InsuranceDecisionReq;
import com.ditros.mcd.service.InjuryService;
import com.ditros.mcd.service.TreatmentService;
import com.ditros.mcd.util.ApiResponseHandler;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/treatments")
public class TreatmentController {
    private TreatmentService treatmentService;

    @GetMapping("/search")
    public ResponseEntity<Object> getTreatmentByName(@RequestParam(name = "name") String name,
                                                     @RequestHeader("lang") String lang){
        try{
            if(lang==null) lang="fr";
            return ApiResponseHandler.generateResponse(HttpStatus.OK,
                    true,
                    "treatment list successfully got",
                    treatmentService.getTreatmentByName(name, lang.toLowerCase())
            );
        }catch (Exception e){
            e.printStackTrace();
            return ApiResponseHandler.generateResponse(HttpStatus.OK,
                    false,
                    "Error while getting treatments :"+e.getMessage(),
                    null
            );
        }
    }

    @GetMapping
    public ResponseEntity<Object> getTreatment(@RequestParam(name = "page", defaultValue = "0") int page,
                                           @RequestParam(name = "size", defaultValue = "10") int size,
                                               @RequestHeader("lang") String lang){
        try{
            if(lang==null) lang="fr";
            return ApiResponseHandler.generateResponse(HttpStatus.OK,
                    true,
                    "Injury list successfully got",
                    treatmentService.getTreats(size, page, lang.toLowerCase())
            );
        }catch (Exception e){
            e.printStackTrace();
            return ApiResponseHandler.generateResponse(HttpStatus.OK,
                    false,
                    "Error while getting injuries :"+e.getMessage(),
                    null
            );
        }
    }

    @PostMapping("/insurance/decision-treatment")
    public ResponseEntity<Object> judgeTreatment(@RequestBody InsuranceDecisionReq insuranceDecisionReq){
        try{
            return ApiResponseHandler.generateResponse(HttpStatus.OK,
                    true,
                    "Treatment successfully approved",
                    treatmentService.decision(insuranceDecisionReq)
            );
        }catch (Exception e){
            e.printStackTrace();
            return ApiResponseHandler.generateResponse(HttpStatus.OK,
                    false,
                    "Error while approving Treatment :"+e.getMessage(),
                    null
            );
        }
    }

}
