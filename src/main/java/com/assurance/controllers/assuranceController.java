package com.assurance.controllers;

import com.assurance.controllers.configuration.ApiResponseHandler;
import com.assurance.controllers.configuration.Message;
import com.assurance.dao.AssuranceDao;
import com.assurance.dao.OrganisationDao;
import com.assurance.entities.Assurance;
import com.assurance.services.AssuranceService;
import com.assurance.transfertObject.*;
import org.jboss.resteasy.spi.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("${url.version}"+"/assurances")
public class assuranceController {

    @Autowired
    private AssuranceDao assuranceDao;
    @Autowired
    private AssuranceService assuranceService;
    @Autowired
    private OrganisationDao organisationDao;


    @GetMapping(params = { "page", "size", "id" })
    public ResponseEntity<Object> findPaginated(@RequestParam("page") int page,
                                                @RequestParam("size") int size,
                                                @RequestParam(name ="search", defaultValue = "", required = false) String search,
                                                @RequestParam("id") UUID id, UriComponentsBuilder uriBuilder,
                                                HttpServletResponse response) throws Exception {

        Page<Assurance> resultPage = assuranceService.findAll( PageRequest.of(page, size), id);


        List<Pojo> pojos =resultPage.stream().map(assurance -> new Pojo(assurance.getId(), assurance.getVehicule(), String.valueOf(assurance.getPrimeAssurance())))
                .collect(Collectors.toList());
        Map<String, List<Pojo>> careGrouped = pojos.stream().collect(Collectors.groupingBy(Pojo::getLegend, Collectors.toList()));
        Map<String, Long> graphData = pojos.stream().collect(Collectors.groupingBy(Pojo::getLegend, Collectors.counting()));
        List<GraphDataResp> graphDataResps = new ArrayList<>();
        graphData.forEach((s, aLong) -> graphDataResps.add(new GraphDataResp(s, aLong)) );
        Map<String, Object> resp = new HashMap<>();
        List<KanbanCareSimpleResp> kanban = new ArrayList<>();
        careGrouped.forEach((s, strings) -> kanban.add(new KanbanCareSimpleResp(strings, s, strings.size())));
        resp.put("list",  new PageImpl<Assurance>(resultPage.toList(), PageRequest.of(page, size), resultPage.getTotalElements()));
        resp.put("kanban", kanban);
        resp.put("graph", graphDataResps);

        return ApiResponseHandler.generateResponse(HttpStatus.OK, false, "OK", resp);
    }



    @GetMapping("/{id}")
    public ResponseEntity<Object> getAssuranceById(@PathVariable UUID id){
        try {
            Assurance assurance = new Assurance();

            if(assuranceDao.findById(id).isPresent())
                assurance = assuranceDao.findById(id).get();

            return ApiResponseHandler.generateResponse(HttpStatus.OK,
                    true, Message.ListOK + "infos assurances", assurance);
        }catch (Exception e){
            return ApiResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                    false, Message.ListKO + "infos assurances", null);
        }
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Object> getAssuranceDetailById(@PathVariable UUID id){
        try {
            Assurance assurance = new Assurance();
            AssuranceDTO assuranceDTO = new AssuranceDTO();
            if(assuranceDao.findById(id).isPresent()){
                assurance = assuranceDao.findById(id).get();
                assuranceDTO =new AssuranceDTO(assurance);
            }


            return ApiResponseHandler.generateResponse(HttpStatus.OK,
                    true, Message.ListOK + "infos assurances", assuranceDTO);
        }catch (Exception e){
            return ApiResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                    false, Message.ListKO + "infos assurances", null);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAssurance(@PathVariable UUID id){

            assuranceDao.deleteById(id);
            return ApiResponseHandler.generateResponse(HttpStatus.OK,
                    true, Message.DeleteOK + "assurances", null);
            /*try {}
        catch (Exception e){
            return ApiResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                    false, Message.AddKO + "assurances", null);
        }*/
    }

    @PostMapping
    public ResponseEntity<Object> addAssurance(@RequestBody AssurancePOJO assurancePOJO) {

         try {
             Assurance savedAssurance = new Assurance(assurancePOJO);
            savedAssurance = assuranceService.addAssurance(savedAssurance);
            AssuranceDTO assuranceDTO = new AssuranceDTO(savedAssurance);
            assuranceDTO.setOrganisation(organisationDao
                    .findById(assurancePOJO.getOrganisation().getId()).get());
            return ApiResponseHandler.generateResponse(HttpStatus.OK,
                    true, Message.AddOK + "assurances", assuranceDTO);

        }
        catch (Exception e){
            return ApiResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                    false, "Assurances "+Message.AddKO , null);
        }
    }

    @GetMapping("/graphview")
    public ResponseEntity<Object> listforGraphView() {
        try {
            int[] histogramme = new int[12];
            for (int i = 1; i < histogramme.length; i++) {
                histogramme[i-1] = assuranceService.getAllByMonth(i).size();
            }
            List<AssuranceCircleViewDTO> graphViews = new ArrayList<>();
            graphViews.add(new AssuranceCircleViewDTO("valid", assuranceService.getValid().size()));
            graphViews.add(new AssuranceCircleViewDTO("expired", assuranceService.getExpiredThisYear().size()));

            return ApiResponseHandler.reponseForGraph(HttpStatus.OK, true, "Affichage graphe",graphViews ,histogramme);
        } catch (Exception e) {

            return ApiResponseHandler.generateResponse(HttpStatus.OK, false, "Erreur lors de l'affichage"
                    + " de la liste des visite en cours", null);
        }

    }

    @GetMapping("/kanban")
    public ResponseEntity<Object> listforKanbanView(){
        try {

            List<AssuranceKanbanViewDTO> kabanViews = new ArrayList<>();
            kabanViews.add(new AssuranceKanbanViewDTO("< 100000", assuranceService.getValidbyPrime(100000, 0), assuranceService.getValidbyPrime(100000, 0).size()));
            kabanViews.add(new AssuranceKanbanViewDTO("< 200000", assuranceService.getValidbyPrime(200000, 100000), assuranceService.getValidbyPrime(200000, 100000).size()));

            return ApiResponseHandler.generateResponse(HttpStatus.OK, true, "Affichage Kaban view assurance", kabanViews);

        } catch (Exception e) {

            return ApiResponseHandler.generateResponse(HttpStatus.OK, false, "Erreur lors de l'affichage"
                    + " de la liste des visite en cours", null);
        }

    }



    @GetMapping("/validity/{imma}")
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

}