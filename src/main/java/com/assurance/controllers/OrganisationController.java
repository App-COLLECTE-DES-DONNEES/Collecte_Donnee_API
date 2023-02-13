package com.assurance.controllers;

import com.assurance.controllers.configuration.ApiResponseHandler;
import com.assurance.controllers.configuration.Message;
import com.assurance.entities.Organisation;
import com.assurance.services.OrganisationService;
import com.assurance.transfertObject.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@CrossOrigin
@RequestMapping("${url.version}"+"/organisations")
public class OrganisationController {
    @Autowired
    private OrganisationService os;
    @Autowired
    private PagedResourcesAssembler<OrganisationDTO> pagedResourcesAssembler;
    @GetMapping("/{id}")
    public ResponseEntity<Object> getOrganisation(@PathVariable UUID id){

        Organisation organisation = os.findOrganisationById(id);
        return ApiResponseHandler.generateResponse(HttpStatus.OK,
                true, Message.ListOK + "Organisation", organisation);
            /*try {}
        catch (Exception e){
            return ApiResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                    false, Message.AddKO + "assurances", null);
        }*/
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteOrganisation(@PathVariable UUID id){

         os.deleteById(id);
        return ApiResponseHandler.generateResponse(HttpStatus.OK,
                true, Message.DeleteOK + "Organisation", null);
            /*try {}
        catch (Exception e){
            return ApiResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                    false, Message.AddKO + "assurances", null);
        }*/
    }
    @GetMapping(params = { "page", "size" })
    public ResponseEntity<Object> findPaginated(@RequestParam("page") int page,
                                                @RequestParam("size") int size) throws Exception {

        Page<OrganisationDTO> resultPage = os.findAll(PageRequest.of(page, size));

        if (page > resultPage.getTotalPages()) {
            throw new Exception();
        }

        PagedModel<EntityModel<OrganisationDTO>> collModel = pagedResourcesAssembler
                .toModel(resultPage);

        return ApiResponseHandler.generateResponse(HttpStatus.OK, false, "OK", collModel);
    }
    @GetMapping(value = "/children/{id}" ,params = { "page", "size" })
    public ResponseEntity<Object> findChildPaginated(@PathVariable UUID id, @RequestParam("page") int page,
                                                     @RequestParam("size") int size) throws Exception {

        Page<OrganisationDTO> resultPage = os.findAllChildren(id,PageRequest.of(page, size));

        if (page > resultPage.getTotalPages()) {
            throw new Exception();
        }

        PagedModel<EntityModel<OrganisationDTO>> collModel = pagedResourcesAssembler
                .toModel(resultPage);

        return ApiResponseHandler.generateResponse(HttpStatus.OK, false, "OK", collModel);
    }
    @GetMapping("/kanban")
    public ResponseEntity<Object> listforKanbanView(){
        try {

            List<OrganisationKanbanViewDTO> kabanViews = new ArrayList<>();
            List<Organisation> parentList = os.findAllForSelect();
            for(Organisation o : parentList ){
                kabanViews.add(new OrganisationKanbanViewDTO(o.getNom(), os.findChildren(o.getNom()), os.findChildren(o.getNom()).size()));
            }

            return ApiResponseHandler.generateResponse(HttpStatus.OK, true, "Affichage Kaban view assurance", kabanViews);

        } catch (Exception e) {

            return ApiResponseHandler.generateResponse(HttpStatus.OK, false, "Erreur lors de l'affichage"
                    + " de la liste des visite en cours", null);
        }

    }
    @GetMapping("/graphview")
    public ResponseEntity<Object> listforGraphView() {
        try {
            List<Organisation> parents = os.findAllForSelect();
            int[] histogramme = new int[parents.size()];
            List<AssuranceCircleViewDTO> graphViews = new ArrayList<>();
            int i = 0;
            int entitySize = 0;
            for(Organisation o : parents){
                entitySize = os.findChildren(o.getNom()).size();
                histogramme[i] = entitySize;
                graphViews.add(new AssuranceCircleViewDTO(o.getNom(), entitySize));
            }

            return ApiResponseHandler.reponseForGraph(HttpStatus.OK, true, "Affichage graphe",graphViews ,histogramme);
        } catch (Exception e) {

            return ApiResponseHandler.generateResponse(HttpStatus.OK, false, "Erreur lors de l'affichage"
                    + " de la liste des visite en cours", null);
        }

    }
    @GetMapping
    public ResponseEntity<Object> findAllForSelect(){
        try {
            List<Organisation> results = os.findAllForSelect();
            List<Map<String, String>> mapList = new ArrayList<>();
            Map<String, String> map = new HashMap<>();
            for(Organisation organisation : results){
                map.put("id",String.valueOf(organisation.getId()));
                map.put("name", organisation.getNom());
                mapList.add(map);
                map = new HashMap<>();
            }
            return ApiResponseHandler.generateResponse(HttpStatus.OK, false, "OK", mapList);
        }
        catch(Exception e){
            e.printStackTrace();
            return ApiResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, false, "OK", null);
        }
    }
    @GetMapping("/parents")
    public ResponseEntity<Object> findParent(){
        try {
            List<Organisation> results = os.findAllForSelect();
            List<String> names = new ArrayList<>();
            results.forEach(organisation -> {
                names.add(organisation.getNom());
            });
            return ApiResponseHandler.generateResponse(HttpStatus.OK, false, "OK", names);
        }
        catch(Exception e){
            e.printStackTrace();
            return ApiResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, false, "OK", null);
        }
    }
    @PostMapping
    public ResponseEntity<Object> enregistrer(@RequestBody OrganisationPOJO organisationPOJO){
        try {
            Organisation organisation = new Organisation();
            Organisation parentOrganisation = organisationPOJO.getParentOrganisation() == null?
                    null : os.findOrganisationById(organisationPOJO.getParentOrganisation());
            organisation.setAdresse(organisationPOJO.getAdresse());
            organisation.setId(organisationPOJO.getId() == null ? null : organisationPOJO.getId() );
            organisation.setParent(organisationPOJO.isParent());
            organisation.setTel1(organisationPOJO.getTel1());
            organisation.setTel2(organisationPOJO.getTel2());
            organisation.setNom(organisationPOJO.getNom());
            organisation.setParentOrganisation(parentOrganisation);
            organisation = os.enregistrer(organisation);

            return ApiResponseHandler.generateResponse(HttpStatus.OK, false, "OK", organisation);
        }
        catch(Exception e){
            e.printStackTrace();
            return ApiResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, false, "OK", null);
        }
    }

}
