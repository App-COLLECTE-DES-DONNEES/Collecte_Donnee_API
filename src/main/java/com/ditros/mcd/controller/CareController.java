package com.ditros.mcd.controller;

import com.ditros.mcd.model.dto.*;
import com.ditros.mcd.service.CareService;
import com.ditros.mcd.util.ApiResponseHandler;
import com.ditros.mcd.util.CsvHelper;
import com.ditros.mcd.util.UserInfoIn;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/cares")
public class CareController {
    private CareService careService;
    private HttpServletRequest request;

    @PostMapping(value="/upload-csv", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file) {
        if (CsvHelper.hasCSVFormat(file)) {
            try {
                careService.importCareFile(file, UserInfoIn.getUserId(request) );
                return ApiResponseHandler.generateResponse(HttpStatus.OK, true, "Uploaded the file successfully!", null);

            } catch (Exception e) {
                e.printStackTrace();
                return ApiResponseHandler.generateResponse(HttpStatus.OK, false, "Could not upload the file", null);
            }
        }
        return ApiResponseHandler.generateResponse(HttpStatus.OK, false, "Please upload a csv file!", null);
    }

    @PostMapping
    public ResponseEntity<Object> declareOrEditCare(@RequestBody CareReq careReq){
        try{
            CareResp careResp = careService.declareCare(
                careReq, UserInfoIn.getUserId(request));
            return ApiResponseHandler.generateResponse(HttpStatus.OK, true, "Care successfully declared", careResp);
        }catch (Exception e){
            e.printStackTrace();
            return ApiResponseHandler.generateResponse(HttpStatus.OK, false, "Error while declaring care"+ e.getMessage(), null);
        } catch (Throwable e) {
            e.printStackTrace();
            return ApiResponseHandler.generateResponse(HttpStatus.OK, false, "Error while declaring care"+ e.getMessage(), null);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCare(@PathVariable Long id){
        try{
            careService.disableCare(id);
            return ApiResponseHandler.generateResponse(HttpStatus.OK, true, "Care successfully disabled", null);
        }catch (Exception e){
            e.printStackTrace();
            return ApiResponseHandler.generateResponse(HttpStatus.OK, false, "Error while disabling care"+ e.getMessage(), null);
        } catch (Throwable e) {
            e.printStackTrace();
            return ApiResponseHandler.generateResponse(HttpStatus.OK, false, "Error while declaring care"+ e.getMessage(), null);

        }
    }

    @GetMapping("/search")
    public ResponseEntity<Object> getPatientFolder(@RequestParam(name = "name", required = true) String name){
        try{
            List<CareResp> careResps = careService.findOpenedCare(name);

            return ApiResponseHandler.generateResponse(HttpStatus.OK, true, "Opened folder in Hospitals", careResps);
        }catch (Exception e){
            e.printStackTrace();
            return ApiResponseHandler.generateResponse(HttpStatus.OK, false, "Error while finding folder in hospital"+ e.getMessage(), null);
        }
    }

    @GetMapping("/accident")
    public ResponseEntity<Object> getPatientAccident(@RequestParam(name = "page", defaultValue = "0") int page,
                                                     @RequestParam(name = "size", defaultValue = "10") int size,
                                                     @RequestParam(name = "vehicleNumber") int vehiculeNumber,
                                                     @RequestParam(name = "accidentId") Long id
                                                     ){
        try{

            return ApiResponseHandler.generateResponse(HttpStatus.OK, true, "Opened folder in Hospitals", careService.findCareAccident(id, vehiculeNumber, page, size));
        }catch (Exception e){
            e.printStackTrace();
            return ApiResponseHandler.generateResponse(HttpStatus.OK, false, "Error while finding folder in hospital: "+ e.getMessage(), null);
        }
    }


    @GetMapping("/declare-form-data")
    public ResponseEntity<Object> getSaveFormData(@RequestHeader("lang") String lang){
        try{
            if(lang==null) lang="fr";
            SaveCareFormResp data = careService.getSaveFormData(lang.toLowerCase());

            return ApiResponseHandler.generateResponse(HttpStatus.OK, true, "Declare form data well received", data);
        }catch (Exception e){
            e.printStackTrace();
            return ApiResponseHandler.generateResponse(HttpStatus.OK, false, "Error while receiving save data form: "+ e.getMessage(), null);
        }
    }

    @GetMapping
    public ResponseEntity<Object> getCareList(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "search", defaultValue = "") String search
    ){
        try{
            log.info("GETTING CARE LIST...");
            String userId = UserInfoIn.getUserId(request);
            return ApiResponseHandler.generateResponse(HttpStatus.OK,
                    true,
                    "Care list",
                    careService.getCareList(userId,search,page,size)
            );
        }catch (Exception e){
            e.printStackTrace();
            return ApiResponseHandler.generateResponse(HttpStatus.OK,
                    false,
                    "Error while displaying accident list :"+e.getMessage(),
                    null
            );
        } catch (Throwable e) {
            e.printStackTrace();
            return ApiResponseHandler.generateResponse(HttpStatus.OK,
                    false,
                    "Error while displaying accident list",
                    null
            );
        }
    }

    @GetMapping("/kanban")
    public ResponseEntity<Object> getKanbanViewData(){
        try{
            String userId = UserInfoIn.getUserId(request);
            return ApiResponseHandler.generateResponse(HttpStatus.OK,
                    true,
                    "Care kanban data",
                    careService.getkanbanForOpenCare(userId)
            );
        }catch (Exception e){
            e.printStackTrace();
            return ApiResponseHandler.generateResponse(HttpStatus.OK,
                    false,
                    "Error while returning kanban data :"+e.getMessage(),
                    null
            );
        }
    }


    @GetMapping("/all")
    public ResponseEntity<Object> getAllCareList(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ){
        try{
            return ApiResponseHandler.generateResponse(HttpStatus.OK,
                    true,
                    "Care list",
                    careService.getAllCareList(page,size)
            );
        }catch (Exception e){
            e.printStackTrace();
            return ApiResponseHandler.generateResponse(HttpStatus.OK,
                    false,
                    "Error while displaying accident list :"+e.getMessage(),
                    null
            );
        }
    }

    @GetMapping("/insurance")
    public ResponseEntity<Object> getAllCareList(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "insuranceName", defaultValue = "") String insuranceName
    ){
        try{
            String userId = UserInfoIn.getUserId(request);
            return ApiResponseHandler.generateResponse(HttpStatus.OK,
                    true,
                    "Care list",
                    careService.getOpenedByInsurance(userId, page,size, insuranceName)
            );
        }catch (Exception e){
            e.printStackTrace();
            return ApiResponseHandler.generateResponse(HttpStatus.OK,
                    false,
                    "Error while displaying car list by insurance Name:"+e.getMessage(),
                    null
            );
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneCare(@PathVariable Long id){
        try{
            return ApiResponseHandler.generateResponse(HttpStatus.OK,
                    true,
                    "Care folder successfully got",
                    careService.getOne(id)
            );
        }catch (Exception e){
            e.printStackTrace();
            return ApiResponseHandler.generateResponse(HttpStatus.OK,
                    false,
                    "Error while getting care folder :"+e.getMessage(),
                    null
            );
        }
    }

    @GetMapping("/folder/{id}")
    public ResponseEntity<Object> getOneCareFolder(@PathVariable Long id){
        try{
            return ApiResponseHandler.generateResponse(HttpStatus.OK,
                    true,
                    "Care folder successfully got",
                    careService.getFolder(id)
            );
        }catch (Exception e){
            e.printStackTrace();
            return ApiResponseHandler.generateResponse(HttpStatus.OK,
                    false,
                    "Error while getting care folder :"+e.getMessage(),
                    null
            );
        }
    }

    @GetMapping("/close/folder/{id}")
    public ResponseEntity<Object> closeCareFolder(@PathVariable Long id){
        try{
            return ApiResponseHandler.generateResponse(HttpStatus.OK,
                    true,
                    "Care folder successfully closed",
                    careService.closeFolder(id)
            );
        }catch (Exception e){
            e.printStackTrace();
            return ApiResponseHandler.generateResponse(HttpStatus.OK,
                    false,
                    "Error while closing care folder :"+e.getMessage(),
                    null
            );
        }
    }


    @PostMapping("/add-injury")
    public ResponseEntity<Object> addInjury(@RequestBody CareFolderItemReq careFolderItemReq){
        try{
            return ApiResponseHandler.generateResponse(HttpStatus.OK,
                    true,
                    "Injury successfully added!",
                    careService.addInjury(careFolderItemReq)
            );
        }catch (Exception e){
            e.printStackTrace();
            return ApiResponseHandler.generateResponse(HttpStatus.OK,
                    false,
                    "Error while adding injury :"+e.getMessage(),
                    null
            );
        }
    }

    @PostMapping("/remove-injury")
    public ResponseEntity<Object> removeInjury(@RequestBody CareFolderItemReq careFolderItemReq){
        try{
            return ApiResponseHandler.generateResponse(HttpStatus.OK,
                    true,
                    "Injury successfully removed!",
                    careService.removeInjury(careFolderItemReq)
            );
        }catch (Exception e){
            e.printStackTrace();
            return ApiResponseHandler.generateResponse(HttpStatus.OK,
                    false,
                    "Error while removing injury :"+e.getMessage(),
                    null
            );
        }
    }
    @PostMapping("/add-exam")
    public ResponseEntity<Object> addExam(@RequestBody CareFolderItemReq careFolderItemReq){
        try{
            return ApiResponseHandler.generateResponse(HttpStatus.OK,
                    true,
                    "Injury successfully removed!",
                    careService.addExam(careFolderItemReq)
            );
        }catch (Exception e){
            e.printStackTrace();
            return ApiResponseHandler.generateResponse(HttpStatus.OK,
                    false,
                    "Error while removing injury :"+e.getMessage(),
                    null
            );
        }
    }

    @PostMapping("/remove-exam")
    public ResponseEntity<Object> removeExam(@RequestBody CareFolderItemReq careFolderItemReq){
        try{
            return ApiResponseHandler.generateResponse(HttpStatus.OK,
                    true,
                    "Exam successfully removed!",
                    careService.removeExam(careFolderItemReq)
            );
        }catch (Exception e){
            e.printStackTrace();
            return ApiResponseHandler.generateResponse(HttpStatus.OK,
                    false,
                    "Error while removing exam :"+e.getMessage(),
                    null
            );
        }
    }

    @PostMapping("/add-treatment")
    public ResponseEntity<Object> addTreatment(@RequestBody CareFolderItemReq careFolderItemReq){
        try{
            return ApiResponseHandler.generateResponse(HttpStatus.OK,
                    true,
                    "Treatment successfully added!",
                    careService.addTreatment(careFolderItemReq)
            );
        }catch (Exception e){
            e.printStackTrace();
            return ApiResponseHandler.generateResponse(HttpStatus.OK,
                    false,
                    "Error while adding treatment :"+e.getMessage(),
                    null
            );
        }
    }
    @PostMapping("/remove-treatment")
    public ResponseEntity<Object> removeTreatment(@RequestBody CareFolderItemReq careFolderItemReq){
        try{
            return ApiResponseHandler.generateResponse(HttpStatus.OK,
                    true,
                    "Treatment successfully removed!",
                    careService.removeTreatment(careFolderItemReq)
            );
        }catch (Exception e){
            e.printStackTrace();
            return ApiResponseHandler.generateResponse(HttpStatus.OK,
                    false,
                    "Error while removing treatment :"+e.getMessage(),
                    null
            );
        }
    }



}
