package com.assurance.controllers;

import com.assurance.controllers.configuration.ApiResponseHandler;
import com.assurance.controllers.configuration.Message;
import com.assurance.services.KeycloakService;
import com.assurance.services.OrganisationService;
import com.assurance.transfertObject.ChildKanbanDTO;
import com.assurance.transfertObject.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("${url.version}"+"/users")
public class UserController {

    @Autowired
    private KeycloakService keycloakService;
    @Autowired
    private OrganisationService os;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUsersOfOrganisation(@PathVariable UUID id){
        List<UserDTO> userDTOList = keycloakService.getUserList();
        List<ChildKanbanDTO> children = os.findChildren(id);
        children.add(new ChildKanbanDTO(id,"",0));
        userDTOList = userDTOList.stream()
                .filter(userDTO -> children.stream()
                .anyMatch(childKanbanDTO -> childKanbanDTO.getId().equals(userDTO.getOrganisationId())))
                .collect(Collectors.toList());

        return ApiResponseHandler.generateResponse(HttpStatus.OK,
                true, Message.ListOK + " Users", userDTOList);
    }
}
