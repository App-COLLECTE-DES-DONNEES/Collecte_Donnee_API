package com.assurance.services;

import com.assurance.dao.AssuranceDao;
import com.assurance.entities.Assurance;
import com.assurance.transfertObject.AssuranceValidityDTO;
import com.assurance.transfertObject.CarteGrise;
import com.assurance.transfertObject.ChildKanbanDTO;
import com.assurance.transfertObject.ResponseEntityPOJO;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AssuranceService {

    @Autowired
    private AssuranceDao assuranceDao;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    Environment env;
    public Assurance addAssurance(Assurance assurance) {
        Assurance a = assuranceDao.save(assurance);

        /*try {
            a.setPdfPath(printAssurance(a));
            a = assuranceDao.save(a);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error when printing");

        }*/
        return a;
    }

    public Page<Assurance> findAll(Pageable pageable, UUID id){
        return assuranceDao.findByActiveStatusTrueAndOrganisation_IdOrderByCreatedDateDesc(pageable, id);
    }
    public Assurance getAssuranceById(UUID id){
        Optional<Assurance> a = assuranceDao.findById(id);
        if(a.isPresent())
            return a.get();
        return null;
    }
    public AssuranceValidityDTO IsInsuranceValid(String imma){
        List<Assurance> a = assuranceDao.getValidByImmatriculation(imma);

        Assurance assurance =a.stream().findFirst().orElseThrow(() -> new RuntimeException("Immatriculation non reconnue !"));
        AssuranceValidityDTO validityDTO = new AssuranceValidityDTO();
        validityDTO.setValid(true);
        validityDTO.setInsurranceName(assurance.getOrganisation().getInsuranceName());

        return validityDTO;
    }

    public List<Assurance> getAllByMonth(Integer i){
        return assuranceDao.getAllByMonth(i);
    }

    public List<Assurance> getExpiredThisYear(){
        return assuranceDao.getExpiredThisYear();
    }
    public List<Assurance> getValid(){
        return assuranceDao.getValid();
    }
    public List<Assurance> getValidAssuranceGroupbyPrime(double prime){
         return assuranceDao.getValidAssuranceGroupbyPrime().stream()
         .filter(assurance -> assurance.getPrimeAssurance() == prime)
         .collect(Collectors.toList());
    }
    public List<ChildKanbanDTO> getValidbyPrime(double prime, double min){
        List<Assurance> assurances = assuranceDao.findByActiveStatusTrueAndPrimeAssuranceLessThanEqualAndPrimeAssuranceGreaterThan(prime, min);
        List<ChildKanbanDTO> childKanbanDTOS = new ArrayList<>();
        ChildKanbanDTO childKanbanDTO;
        for(Assurance a : assurances){
            childKanbanDTO = new ChildKanbanDTO();
            childKanbanDTO.setId(a.getId());
            childKanbanDTO.setName(a.getVehicule());
            childKanbanDTOS.add(childKanbanDTO);
        }
        return childKanbanDTOS;
    }
    public String printAssurance(Assurance a) throws Exception {

        File f= new File(env.getProperty("assurance.path"));
        if(!f.exists())
            f.mkdirs();

        String outputFolder = env.getProperty("assurance.path") + a.getId().toString() + ".pdf";
        OutputStream outputStream = new FileOutputStream(outputFolder);
        restTemplate = new RestTemplate();
        ResponseEntityPOJO responseEntityPOJO
                = restTemplate.getForObject(env.getProperty("url.cartegrise")+a.getVehicule(), ResponseEntityPOJO.class);

        List<CarteGrise> carteGrises = responseEntityPOJO.getData();

        System.out.println("**************"+ToStringBuilder.reflectionToString(carteGrises.get(0)));
        CarteGrise carteGrise = carteGrises.get(0);
        if(carteGrise == null)
            throw new Exception("Cartegrise inexistante");


        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(
                parseThymeleafTemplate(a, carteGrise)
        );

        renderer.layout();
        renderer.createPDF(outputStream);

        outputStream.close();

        return env.getProperty("assurance.path")+a.getId()+".pdf";
        /*try {} catch (Exception e) {
            log.error("Erreur lors de l'impression du PV");
            return "<h1> Erreur lors l'impression du PV </h1>";
        }*/
    }
    public String parseThymeleafTemplate(Assurance a, CarteGrise carteGrise) throws Exception {



        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        Context context = new Context();


        context.setVariable("carteGrise", carteGrise);
        context.setVariable("nom", carteGrise.getProprietaireVehicule().getPartenaire().getNom());
        context.setVariable("assurance", a);
        context.setVariable("debut", convert(a.getDate_debut()));
        context.setVariable("fin", convert(a.getDate_fin()));



        return templateEngine.process("templates/vignette", context);
        }

    public Date convert(LocalDateTime dateToConvert) {
        return Date
                .from(
                        dateToConvert
                                .atZone(
                                        ZoneId
                                                .systemDefault())
                                .toInstant());
    }

    }

