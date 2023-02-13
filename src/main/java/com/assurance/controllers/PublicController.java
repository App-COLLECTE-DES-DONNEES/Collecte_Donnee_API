package com.assurance.controllers;

import com.assurance.controllers.configuration.ApiResponseHandler;
import com.assurance.entities.Assurance;
import com.assurance.services.AssuranceService;
import com.assurance.transfertObject.AssuranceValidityDTO;
import org.jboss.resteasy.spi.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private AssuranceService assuranceService;

    @GetMapping("/assurances/validity/{imma}")
    public ResponseEntity<Object> qr(@PathVariable String imma){
        try{
            AssuranceValidityDTO a = assuranceService.IsInsuranceValid(imma);
            return ApiResponseHandler.generateResponse(HttpStatus.OK, true, "Insurance validity", a);
        }
        catch (Exception e) {
            e.printStackTrace();
            return ApiResponseHandler.generateResponse(HttpStatus.OK, false, e.getMessage(), new AssuranceValidityDTO("UNRECOGNIZED",false));
        }

    }

    @GetMapping("/qrcode/{id}")
    public ResponseEntity<byte[]> qr(@PathVariable final UUID id) throws WriterException, IOException, com.google.zxing.WriterException {
        Assurance a = assuranceService.getAssuranceById(id);
        System.out.println("qrcode en cours de fabrication...");

        byte[] bytes = com.catis.Controller.configuration.QRCodeGenerator.getQRCodeImage(a.getId().toString(), 85, 85);
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);

        return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.CREATED);
    }
}
