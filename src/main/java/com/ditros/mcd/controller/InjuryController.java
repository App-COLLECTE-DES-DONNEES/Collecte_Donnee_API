package com.ditros.mcd.controller;

import com.ditros.mcd.service.InjuryService;
import com.ditros.mcd.util.ApiResponseHandler;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/injuries")
public class InjuryController {

    private InjuryService injuryService;

    @GetMapping("/search")
    public ResponseEntity<Object> getSearchInjuries(@RequestParam(name = "name") String name,
                                                    @RequestHeader("lang") String lang){
        try{
            return ApiResponseHandler.generateResponse(HttpStatus.OK,
                    true,
                    "Injury list successfully got",
                    injuryService.getInjuriesByName(name, lang)
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
    @GetMapping()
    public ResponseEntity<Object> getInjuries(@RequestParam(name = "page", defaultValue = "0") int page,
                                              @RequestParam(name = "size", defaultValue = "10") int size,
                                              @RequestHeader("lang") String lang){
        try{
            return ApiResponseHandler.generateResponse(HttpStatus.OK,
                    true,
                    "Injury list successfully got",
                    injuryService.getInjuries(size, page, lang)
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
}
