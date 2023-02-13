package com.assurance.services;

import com.assurance.dao.OrganisationDao;
import com.assurance.entities.Organisation;
import com.assurance.transfertObject.ChildKanbanDTO;
import com.assurance.transfertObject.ModelForSelectDTO;
import com.assurance.transfertObject.OrganisationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrganisationService {
    @Autowired
    private OrganisationDao organisationDao;

    public Organisation findOrganisationById(UUID id){
        Optional<Organisation> o = organisationDao.findById(id);
        if(o.isPresent()){
            return o.get();
        }
        return null;
    }
    public Page<OrganisationDTO> findAll(Pageable pageable){
        List<OrganisationDTO> organisationDTOS = new ArrayList<>();
        OrganisationDTO oDTO;
        ModelForSelectDTO parentOrganisation;
        for(Organisation o : organisationDao.findByActiveStatusTrueAndParentTrue(pageable)){
            oDTO = new OrganisationDTO();
            parentOrganisation = new ModelForSelectDTO();
            oDTO.setId(o.getId());
            oDTO.setAdresse(o.getAdresse());
            oDTO.setNom(o.getNom());
            oDTO.setTel1(o.getTel1());
            oDTO.setTel2(o.getTel2());
            oDTO.setParent(o.isParent());
            parentOrganisation.setId(o.getParentOrganisation()==null?null:o.getParentOrganisation().getId());
            parentOrganisation.setName(o.getParentOrganisation()==null?null:o.getParentOrganisation().getNom() );
            oDTO.setParentOrganisation(parentOrganisation);
            organisationDTOS.add(oDTO);
        }
        Page<OrganisationDTO> organisationDTOPage = new PageImpl<>(organisationDTOS,pageable,Long.valueOf(organisationDTOS.size()));
        return organisationDTOPage;
    }
    public Page<OrganisationDTO> findAllChildren(UUID idParent, Pageable pageable){
        List<OrganisationDTO> organisationDTOS = new ArrayList<>();
        OrganisationDTO oDTO;
        ModelForSelectDTO parentOrganisation;
        for(Organisation o : organisationDao.findByActiveStatusTrueAndParentOrganisation_ActiveStatusTrueAndParentOrganisation_Id(idParent,pageable)){
            oDTO = new OrganisationDTO();
            parentOrganisation = new ModelForSelectDTO();
            oDTO.setId(o.getId());
            oDTO.setAdresse(o.getAdresse());
            oDTO.setNom(o.getNom());
            oDTO.setTel1(o.getTel1());
            oDTO.setTel2(o.getTel2());
            oDTO.setParent(o.isParent());
            parentOrganisation.setId(o.getParentOrganisation()==null?null:o.getParentOrganisation().getId());
            parentOrganisation.setName(o.getParentOrganisation()==null?null:o.getParentOrganisation().getNom() );
            oDTO.setParentOrganisation(parentOrganisation);
            organisationDTOS.add(oDTO);
        }
        Page<OrganisationDTO> organisationDTOPage = new PageImpl<>(organisationDTOS,pageable,Long.valueOf(organisationDTOS.size()));
        return organisationDTOPage;
    }
    public List<Organisation> findAll(){
        List<Organisation> orgs = new ArrayList<>();
        organisationDao.findAll().forEach(orgs::add);
        return orgs;
    }
    public List<ChildKanbanDTO> findChildren(String nom){
        List<Organisation> orgs = organisationDao.findByActiveStatusTrueAndParentOrganisation_ActiveStatusTrueAndParentOrganisation_Nom(nom);
        List<ChildKanbanDTO> childKanbanDTOS = new ArrayList<>();
        ChildKanbanDTO childKanbanDTO;
        for(Organisation o : orgs){
            childKanbanDTO = new ChildKanbanDTO();
            childKanbanDTO.setId(o.getId());
            childKanbanDTO.setName(o.getNom());
            childKanbanDTOS.add(childKanbanDTO);
        }
        return childKanbanDTOS;
    }
    public List<ChildKanbanDTO> findChildren(UUID id){
        List<Organisation> orgs = organisationDao.findByActiveStatusTrueAndParentOrganisation_ActiveStatusTrueAndParentOrganisation_Id(id);
        List<ChildKanbanDTO> childKanbanDTOS = new ArrayList<>();
        ChildKanbanDTO childKanbanDTO;
        for(Organisation o : orgs){
            childKanbanDTO = new ChildKanbanDTO();
            childKanbanDTO.setId(o.getId());
            childKanbanDTO.setName(o.getNom());
            childKanbanDTOS.add(childKanbanDTO);
        }
        return childKanbanDTOS;
    }
    public List<Organisation> findAllForSelect(){
        List<Organisation> orgs = new ArrayList<>();
        organisationDao.findByActiveStatusTrueAndParentTrue().forEach(orgs::add);
        return orgs;
    }
    public void deleteById(UUID id){
        organisationDao.deleteById(id);
    }

    public Organisation enregistrer(Organisation organisation){
       return organisationDao.save(organisation);
    }
}
