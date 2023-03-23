package com.ditros.mcd.controller;

import com.ditros.mcd.model.dto.InsuranceDecisionReq;
import com.ditros.mcd.service.ExaminationService;
import com.ditros.mcd.service.InjuryService;
import com.ditros.mcd.util.ApiResponseHandler;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/examinations")
public class ExaminationController {
    private ExaminationService examinationService;

    @GetMapping("/search")
    public ResponseEntity<Object> getExamByName(@RequestParam(name = "name") String name,
                                                @RequestHeader("lang") String lang){
        try{if(lang==null) lang="fr";
            return ApiResponseHandler.generateResponse(HttpStatus.OK,
                    true,
                    "Exam list successfully got",
                    examinationService.getExamByName(name, lang.toLowerCase())
            );
        }catch (Exception e){
            e.printStackTrace();
            return ApiResponseHandler.generateResponse(HttpStatus.OK,
                    false,
                    "Error while getting exams :"+e.getMessage(),
                    null
            );
        }
    }

    @GetMapping
    public ResponseEntity<Object> getExams(@RequestParam(name = "page", defaultValue = "0") int page,
                                              @RequestParam(name = "size", defaultValue = "10") int size,
                                           @RequestHeader("lang") String lang){
        try{
            if(lang==null) lang="fr";
            return ApiResponseHandler.generateResponse(HttpStatus.OK,
                    true,
                    "Injury list successfully got",
                    examinationService.getExams(size, page, lang.toLowerCase())
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

    @PostMapping("/insurance/decision-examination")
    public ResponseEntity<Object> judgeExamination(@RequestBody InsuranceDecisionReq insuranceDecisionReq){
        try{
            return ApiResponseHandler.generateResponse(HttpStatus.OK,
                    true,
                    "Treatment successfully judged",
                    examinationService.decision(insuranceDecisionReq)
            );
        }catch (Exception e){
            e.printStackTrace();
            return ApiResponseHandler.generateResponse(HttpStatus.OK,
                    false,
                    "Error while judging examination :"+e.getMessage(),
                    null
            );
        }
    }



}
