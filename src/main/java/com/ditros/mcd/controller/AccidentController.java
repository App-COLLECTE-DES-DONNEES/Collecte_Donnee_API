package com.ditros.mcd.controller;

import com.ditros.mcd.dao.CareDao;
import com.ditros.mcd.model.dto.*;
import com.ditros.mcd.model.entity.Care;
import com.ditros.mcd.model.mappers.AccidentMapper;
import com.ditros.mcd.service.AccidentService;
import com.ditros.mcd.util.ApiResponseHandler;
import com.ditros.mcd.util.UserInfoIn;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController @AllArgsConstructor
@Slf4j @RequestMapping("/api/v1/accidents")
public class AccidentController {

    private AccidentService accidentService;
    private AccidentMapper accidentMapper;
    private CareDao careDao;
    private HttpServletRequest request;


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> declareAccident(
            @RequestPart(name = "accidentReq") String accidentReq
    ){
        try{
            log.info("DECLARE ACCIDENT REQUEST");
            Gson gson = new Gson();
            AccidentReq req = gson.fromJson(accidentReq,AccidentReq.class);
            return ApiResponseHandler.generateResponse(HttpStatus.OK,
                    true, "accident successfully declared",
                    accidentService.declareAccident(req, UserInfoIn.getUserId(request)) );
        }catch(Exception e){
            e.printStackTrace();
            return ApiResponseHandler.generateResponse(HttpStatus.OK,false, "Error while declaring accident :"+e.getMessage(), null );

        }
    }

    @PostMapping(value = "/save-vehicle-image",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> saveVehicleImage(
            @RequestPart(name = "image") MultipartFile vehicule
    ){
        try{
            log.info("SAVE VEHICLE IMAGE REQUEST...");
            return ApiResponseHandler.generateResponse(HttpStatus.OK,
                    true, "vehicle image successfully saved",
                    accidentService.saveVehiculeImage(vehicule) );
        }catch(Exception e){
            e.printStackTrace();
            return ApiResponseHandler.generateResponse(HttpStatus.OK,false, "Error while declaring accident :"+e.getMessage(), null );

        }
    }
    @PostMapping(value = "/save-vehicle-document-image",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> saveDocumentVehicleImage(
            @RequestPart(name = "image") MultipartFile vehicule
    ){
        try{
            log.info("SAVE VEHICLE DOCUMENT IMAGE REQUEST...");
            return ApiResponseHandler.generateResponse(HttpStatus.OK,
                    true, "vehicle image successfully saved",
                    accidentService.saveVehicleDocument(vehicule) );
        }catch(Exception e){
            e.printStackTrace();
            return ApiResponseHandler.generateResponse(HttpStatus.OK,false, "Error while declaring accident :"+e.getMessage(), null );

        }
    }

    @PostMapping("/delete-vehicle-image")
    public ResponseEntity<Object> deleteVehicleImage(
            @RequestBody DeleteImageReq deleteImageReq
    ){
        try{
            log.info("DELETE VEHICLE IMAGE REQUEST...");
            return ApiResponseHandler.generateResponse(HttpStatus.OK,
                    true, "vehicle image successfully deleted",
                    accidentService.deleteVehiculeImage(deleteImageReq.getName(), deleteImageReq.getId()) );
        }catch(Exception e){
            e.printStackTrace();
            return ApiResponseHandler.generateResponse(HttpStatus.OK,false, "Error while declaring accident :"+e.getMessage(), null );

        }
    }
    @PostMapping("/delete-vehicle-document-image")
    public ResponseEntity<Object> deleteVehicleDocumentImage(
            @RequestBody DeleteImageReq deleteImageReq
    ){
        try{
            log.info("DELETE VEHICLE DOCUMENT IMAGE REQUEST...");
            return ApiResponseHandler.generateResponse(HttpStatus.OK,
                    true, "vehicle image successfully deleted",
                    accidentService.deleteVehiculeImage(deleteImageReq.getName(), deleteImageReq.getId()) );
        }catch(Exception e){
            e.printStackTrace();
            return ApiResponseHandler.generateResponse(HttpStatus.OK,false, "Error while declaring accident :"+e.getMessage(), null );

        }
    }


    @PostMapping(value = "/save-drawing",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> saveDraw(
            @RequestPart(name = "image") MultipartFile vehicule,
            @RequestPart(name = "accident_id") String id
    ){
        try{
            log.info("SAVE DRAWING IMAGE REQUEST...");
            return ApiResponseHandler.generateResponse(HttpStatus.OK,
                    true, "vehicle image successfully saved",
                    accidentService.saveDrawing(vehicule, id) );
        }catch(Exception e){
            e.printStackTrace();
            return ApiResponseHandler.generateResponse(HttpStatus.OK,false, "Error while declaring accident :"+e.getMessage(), null );

        }
    }

    @PostMapping( "/delete-drawing")
    public ResponseEntity<Object> deleteDrawing(
            @RequestBody DeleteImageReq deleteImageReq
    ){
        try{
            log.info("DELETE DRAWING IMAGE REQUEST...");
            return ApiResponseHandler.generateResponse(HttpStatus.OK,
                    true, "vehicle image successfully deleted",
                    accidentService.deleteDrawing(deleteImageReq.getName(), deleteImageReq.getId()) );
        }catch(Exception e){
            e.printStackTrace();
            return ApiResponseHandler.generateResponse(HttpStatus.OK,false, "Error while declaring accident :"+e.getMessage(), null );

        }
    }

    @PostMapping(value = "/save-person-image",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> savePersonImage(
            @RequestPart(name = "image") MultipartFile person
    ){
        try{
            log.info("SAVE PERSON IMAGE REQUEST...");
            return ApiResponseHandler.generateResponse(HttpStatus.OK,
                    true, "person image successfully saved",
                    accidentService.savePersonImage(person) );
        }catch(Exception e){
            e.printStackTrace();
            return ApiResponseHandler.generateResponse(HttpStatus.OK,false, "Error while declaring accident :"+e.getMessage(), null );

        }
    }
    @PostMapping(value = "/save-person-document-image",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> savePersonDocumentImage(
            @RequestPart(name = "image") MultipartFile person
    ){
        try{
            log.info("SAVE PERSON IMAGE REQUEST...");
            return ApiResponseHandler.generateResponse(HttpStatus.OK,
                    true, "person document image successfully saved",
                    accidentService.savePersonDocument(person) );
        }catch(Exception e){
            e.printStackTrace();
            return ApiResponseHandler.generateResponse(HttpStatus.OK,false, "Error while saving file :"+e.getMessage(), null );

        }
    }

    @PostMapping( "/delete-person-document-image")
    public ResponseEntity<Object> deletePersonImage(
            @RequestBody DeleteImageReq deleteImageReq
    ){
        try{
            log.info("DELETE PERSON IMAGE REQUEST...");
            return ApiResponseHandler.generateResponse(HttpStatus.OK,
                    true, "person document image successfully deleted",
                    accidentService.deletePersonDocument(deleteImageReq.getName(), deleteImageReq.getId()) );
        }catch(Exception e){
            e.printStackTrace();
            return ApiResponseHandler.generateResponse(HttpStatus.OK,false, "Error while deleting file :"+e.getMessage(), null );

        }
    }

    @PostMapping( "/delete-person-image")
    public ResponseEntity<Object> deletePersonDocumentImage(
            @RequestBody DeleteImageReq deleteImageReq
    ){
        try{
            log.info("DELETE PERSON IMAGE REQUEST...");
            return ApiResponseHandler.generateResponse(HttpStatus.OK,
                    true, "vehicle image successfully deleted",
                    accidentService.deletePersonImage(deleteImageReq.getName(), deleteImageReq.getId()) );
        }catch(Exception e){
            e.printStackTrace();
            return ApiResponseHandler.generateResponse(HttpStatus.OK,false, "Error while declaring accident :"+e.getMessage(), null );

        }
    }

    @PostMapping(value = "/save-crash-image",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> saveCrashImage(
            @RequestPart(name = "image") MultipartFile crash
    ){
        try{
            log.info("SAVE CRASH IMAGE REQUEST...");
            return ApiResponseHandler.generateResponse(HttpStatus.OK,
                    true, "crash image successfully saved",
                    accidentService.saveCrashImage(crash) );
        }catch(Exception e){
            e.printStackTrace();
            return ApiResponseHandler.generateResponse(HttpStatus.OK,false, "Error while declaring accident :"+e.getMessage(), null );

        }
    }

    @PostMapping( "/delete-crash-image")
    public ResponseEntity<Object> deletCrashImage(
            @RequestBody DeleteImageReq deleteImageReq
    ){
        try{
            log.info("DELETE PERSON IMAGE REQUEST...");
            return ApiResponseHandler.generateResponse(HttpStatus.OK,
                    true, "crash image successfully deleted",
                    accidentService.deleteCrashImage(deleteImageReq.getName(), deleteImageReq.getId()) );
        }catch(Exception e){
            e.printStackTrace();
            return ApiResponseHandler.generateResponse(HttpStatus.OK,false, "Error while declaring accident :"+e.getMessage(), null );

        }
    }

    @PostMapping("/join-person-accident")
    public ResponseEntity<Object> joinPersonAccident(@RequestBody JoinPersonAccidentReq joinPersonAccidentReq){
        try{
            Optional<Care> optCare = careDao.findById(joinPersonAccidentReq.getCareId());
            Care care = optCare.orElseThrow(() -> new RuntimeException("Wrong id select a correct medical folder!"));
            if(care.getPersonAccident()!=null){
                return ApiResponseHandler.generateResponse(HttpStatus.ACCEPTED,
                        false,
                        "Error while joining person accident folder and patient folder ",
                        accidentMapper.fromAccident(care.getPersonAccident().getAccident())
                );
            }
            return ApiResponseHandler.generateResponse(HttpStatus.OK,
                    true,
                    "person accident folder and patient folder successfully joined!",
                    accidentService.joinPersonAccident(joinPersonAccidentReq)
            );
        }
        catch (Exception e){
            e.printStackTrace();
            return ApiResponseHandler.generateResponse(HttpStatus.OK,
                    false,
                    "Error while joining person accident folder and patient folder :"+e.getMessage(),
                    null
            );
        }
    }
    @PostMapping("/unjoin-person-accident")
    public ResponseEntity<Object> unjoinPersonAccident(@RequestBody JoinPersonAccidentReq joinPersonAccidentReq){
        try{

            return ApiResponseHandler.generateResponse(HttpStatus.OK,
                    true,
                    "person accident folder and patient folder successfully unjoined!",
                    accidentService.unjoinPersonAccident(joinPersonAccidentReq)
            );
        }catch (Exception e){
            e.printStackTrace();
            return ApiResponseHandler.generateResponse(HttpStatus.OK,
                    false,
                    "Error while unjoining person accident folder and patient folder :"+e.getMessage(),
                    null
            );
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getAccident(@PathVariable Long id){
        try{
            return ApiResponseHandler.generateResponse(HttpStatus.OK,
                    true, "accident successfully got",
                    accidentService.getOneAccidentNew(id) );
        }catch(Exception e){
            e.printStackTrace();
            return ApiResponseHandler.generateResponse(HttpStatus.OK,false, "Error while deplaying accident details :"+e.getMessage(), null );

        }
    }

    @PostMapping("/send-report")
    public ResponseEntity<Object> postAccident(@RequestBody AccidentReportReq accidentReportReq){
        try{
            return ApiResponseHandler.generateResponse(HttpStatus.OK,
                    true, "accident reported!",
                    accidentService.createReport(accidentReportReq) );
        }catch(Exception e){
            e.printStackTrace();
            return ApiResponseHandler.generateResponse(HttpStatus.OK,false, "Error while sending report :"+e.getMessage(), null );

        }
    }

    @GetMapping("/get-report/{accidentId}")
    public ResponseEntity<Object> getReport(@PathVariable Long accidentId){
        try{
            return ApiResponseHandler.generateResponse(HttpStatus.OK,
                    true, "report got!",accidentService.getReport(accidentId) );
        }catch(Exception e){
            e.printStackTrace();
            return ApiResponseHandler.generateResponse(HttpStatus.OK,false, "Error while getting report :"+e.getMessage(), null );

        }
    }

    @PostMapping("/print-report/{accidentId}")
    public ResponseEntity<Object> printReport(@PathVariable Long id){
        try{
            return ApiResponseHandler.generateResponse(HttpStatus.OK,
                    true, "report printed!",accidentService.printReport(id) );
        }catch(Exception e){
            e.printStackTrace();
            return ApiResponseHandler.generateResponse(HttpStatus.OK,false, "Error while getting report :"+e.getMessage(), null );

        }
    }

    @PostMapping("/finish")
    public ResponseEntity<Object> completeInvestigation(@RequestBody InvestigationStatusReq investigationStatusReq){
        try{
            return ApiResponseHandler.generateResponse(HttpStatus.OK,
                    true, "accident report successfully finished",
                    accidentService.completeInvesgation(investigationStatusReq, UserInfoIn.getUserId(request)) );
        }catch(Exception e){
            e.printStackTrace();
            return ApiResponseHandler.generateResponse(HttpStatus.OK,false, "Error while deplaying accident details :"+e.getMessage(), null );

        }
    }

    @PostMapping("/accept")
    public ResponseEntity<Object> acceptInvestigation(@RequestBody InvestigationStatusReq investigationStatusReq){
        try{
            return ApiResponseHandler.generateResponse(HttpStatus.OK,
                    true, "accident report successfully accident",
                    accidentService.acceptInvesgation(investigationStatusReq, UserInfoIn.getUserId(request)) );
        }catch(Exception e){
            e.printStackTrace();
            return ApiResponseHandler.generateResponse(HttpStatus.OK,false, "Error while deplaying accident details :"+e.getMessage(), null );

        }
    }

    @GetMapping("/reject/{id}")
    public ResponseEntity<Object> rejectInvestigation(@PathVariable Long id){
        try{
            return ApiResponseHandler.generateResponse(HttpStatus.OK,
                    true, "accident report successfully accident",
                    accidentService.rejectInvesgation(id, UserInfoIn.getUserId(request)) );
        }catch(Exception e){
            e.printStackTrace();
            return ApiResponseHandler.generateResponse(HttpStatus.OK,false, "Error while deplaying accident details :"+e.getMessage(), null );

        }
    }


    @GetMapping
    public ResponseEntity<Object> getAccidentPage(
                                                  @RequestParam(name = "page", defaultValue = "0") int page,
                                                  @RequestParam(name = "size", defaultValue = "10") int size,
                                                  @RequestParam(name = "search", defaultValue = "") String search
                                                  ){
        try{
            log.info("GET ACCIDENT LIST...");
            String userId = UserInfoIn.getUserId(request);
            return ApiResponseHandler.generateResponse(HttpStatus.OK,
                    true,
                    "Accident list",
                    accidentService.getAccidentList(userId,page,size, search)
            );
        }catch (Exception e){
            e.printStackTrace();
            return ApiResponseHandler.generateResponse(HttpStatus.OK,
                    false,
                    "Error while displaying accident list",
                    null
            );
        }
    }

    @GetMapping("/insurance")
    public ResponseEntity<Object> getAccidentPage(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "name", defaultValue = "") String name,
            @RequestParam(name = "size", defaultValue = "10") int size
    ){
        try{
            return ApiResponseHandler.generateResponse(HttpStatus.OK,
                    true,
                    "Accident list",
                    accidentService.getInsuranceAccidentList(name, page,size)
            );
        }catch (Exception e){
            return ApiResponseHandler.generateResponse(HttpStatus.OK,
                    false,
                    "Error while displaying accident list",
                    null
            );
        }
    }

    @GetMapping("/oms-data")
    public ResponseEntity<Object> getAccidentData(@RequestHeader("lang") String lang){
        try{
            if(lang==null) lang="fr";
            return ApiResponseHandler.generateResponse(
                    HttpStatus.OK,
                    true,
                    "OMS minimum data successfully pulled",
                    accidentService.getDataForAccident(lang.toLowerCase())
            );
        }catch(Exception e) {
            e.printStackTrace();
            return ApiResponseHandler.generateResponse(
                    HttpStatus.OK,
                    false,
                    "Error while pulling OMS minimum data :" + e.getMessage(),
                    null
            );
        }
    }
}