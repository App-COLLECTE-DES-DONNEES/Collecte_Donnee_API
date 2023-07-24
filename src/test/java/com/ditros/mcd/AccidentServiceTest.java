package com.ditros.mcd;



import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.yaml.snakeyaml.DumperOptions.ScalarStyle;

import com.ditros.mcd.controller.AccidentController;
import com.ditros.mcd.dao.AccidentDao;
import com.ditros.mcd.dao.NotificationAccidentDao;
import com.ditros.mcd.enumeration.AccidentStatus;
import com.ditros.mcd.model.dto.AccidentReq;
import com.ditros.mcd.model.dto.AccidentResp;
import com.ditros.mcd.model.dto.AccidentstatResp1;
import com.ditros.mcd.model.dto.Accidentstatresp;
import com.ditros.mcd.model.dto.DirectCauseResp;
import com.ditros.mcd.model.dto.DocumentReq;
import com.ditros.mcd.model.dto.ImageVueFormat;
import com.ditros.mcd.model.dto.NotificationaccidentResp;
import com.ditros.mcd.model.dto.PersonDtoReq;
import com.ditros.mcd.model.dto.VehiculeReq;
import com.ditros.mcd.model.dto.NotificationaccidentResp.Statut;
import com.ditros.mcd.model.entity.Accident;
import com.ditros.mcd.model.entity.Notification;
import com.ditros.mcd.service.AccidentService;
import com.ditros.mcd.util.UserInfoIn;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Null;



@SpringBootTest
 
public class AccidentServiceTest {
 

 HttpServletRequest request;

@Autowired
     private AccidentService accidentservice;
     
   


    AccidentDao accidentDao;
    @Test
    void testSaveNotification() {

    NotificationaccidentResp motification=new NotificationaccidentResp();
    motification.setDate("27/05/2023");
    motification.setCity("Doaula");
    motification.setHours("15:05");
    motification.setStatut(Statut.ALLOWED);
    motification.setImageString("C://file");
    motification.setDescription("Nouvel Accident sur la rues des pavees, premiere avenu a 10 heure");
 
   

     
    //  assertNotNull(expectedvalue);
    //  assertEquals(expectedvalue.getDate(),motification.getDate());;
    //  assertEquals(expectedvalue.getHours(),motification.getHours());
    //  assertEquals(expectedvalue.getCity(),motification.getCity());
    //  assertEquals(expectedvalue.getStatus(),motification.getStatut());
    //  assertEquals(expectedvalue.getImageString(),motification.getImageString());
    //  assertEquals(expectedvalue.getDescription(),motification.getDescription());
 
    }  
    
   

    @Test
    void testAcceptInvesgation() {

    }
List<String>month=new ArrayList();
    @Test
    void testAccidentstatistics() {
      String year="2023";
        
        month.add("01");
        month.add("03");
        month.add("04");
        month.add("06");
        month.add("08");
        month.add("10");
        
        List<Accidentstatresp> expectedvalue=accidentservice.accidentstatistics("23194af1-6320-465f-b8fa-283abfc35a0c",year,month);
        for(Accidentstatresp element:expectedvalue){
           List<String>months=new ArrayList();
           months.add(element.getMonth());
          assertEquals(element,accidentservice.accidentstatistics("23194af1-6320-465f-b8fa-283abfc35a0c",year,months).get(0));
          System.out.println("------------------------------------- Passs ---------------------------------------------------------------");
        }
       

       







    }

    @Test
    void testAccidentstatistics1() {
      

    }

    @Test
    void testCompleteInvesgation() {

    }

    @Test
    void testCreateReport() {

    }

    @Test
    void testDeclareAccident() throws IOException {


        ImageVueFormat imageformat=ImageVueFormat.builder()
      .name("image1")
      .path("C://picture")
      .build();
      ImageVueFormat imageformat1=ImageVueFormat.builder()
      .name("image1")
      .path("C://picture")
      .build();
      List<ImageVueFormat> crashImage=new ArrayList();
      crashImage.add(imageformat);
      crashImage.add(imageformat1);

      DirectCauseResp directcausesresp=DirectCauseResp.builder()
      .name("route")
      .build();
        

      List<DirectCauseResp> cause=new ArrayList();
     
    //    DocumentReq document=DocumentReq.builder()
    //   .identification("identification1")
    //   .recipientFirstname("JR")
    //   .recipientLastname("identification2")
    //   .issueDate("15/06/2023")
    //   .expireAt("12/06/2023")
    //   .image("file:///doc/img.png")
    //   .type(new Long(2))
    //   .person(new Long(22))
    //   .vehicle(new Long(2))
    //   .build();
      List<DocumentReq> listdocument=new ArrayList();
      

     PersonDtoReq person=PersonDtoReq.builder()
     .id(new Long(244))
     .firstName("fabien")
     .lastName("catis")
     .cni("107456897")
     .telephone("630254587")
     .personAccidentNumber(3)
     .vehicleAccidentNumber(2)
     .vehicleLinkedPedestrian(0)
     .birthDate("18/05/2006")
     .gender(new Long(1))
     .roadType(new Long(22))
     .range(new Long(2))
     .place(new Long(1))
     .testType(new Long(1))
     .testResult(new Long(2))
     .traumaSeverity(new Long(1))
     .wearingHelmet(new Long(2))
     .occupantRestraintSystem(new Long(1))
     .personAction(new Long(2))
     .alcoholConsumption(new Long(1))
     .testStatus(new Long(2))
     .drugUse(new Long(1))
     .drivingLicenceYear("2023")
     .care(new Long(22))
     .personId(new Long(22))
     .images(crashImage)
     .address("Douala")
.street("")
.postal("4104")
.lang("Fr")
.tel1("678412536")
.tel2("684567123")
.latitude(5.2588778989)
.longitude(5.32155888)
.personDocuments(listdocument)
.profession(new Long(2))
.drivingLicence("15/11/2023")
.drivingLicenceType("")
.build();

List<PersonDtoReq> personlist=new ArrayList();
personlist.add(person);

     VehiculeReq vehiculeReq=VehiculeReq.builder()
     .id(new Long(212))
     .vehicleAccidentNumber(new Long(2))
     .plate("LT85621")
     .chassis("1856478914")
     .vehicleId(new Long(206))
     .type(new Long(2))
     .brand(new Long(1))
     .model(new Long(1))
     .fabricationYear(2023)
.cylinderCapacity(25)
.specialFunction(new Long(1))
.vehicleAction(new Long(2))
.vehicleImages(crashImage)
.vehicleDocuments(listdocument)
.build();

List<VehiculeReq> listvehicule=new ArrayList();
listvehicule.add(vehiculeReq);
     AccidentReq requestaccident= new AccidentReq();
      requestaccident.setId(new Long(121));
      requestaccident.setAccidentDate("15/05/2023");
      requestaccident.setAccidentTime("15:03");
      requestaccident.setMunicipality(new Long(22));
      requestaccident.setLatitude(5.006882888142739);
      requestaccident.setLatitude(5.006882888142739);
      requestaccident.setPlace("Douala");
      requestaccident.setRoad(new  Long(3));
       requestaccident.setRoadType(new Long(3));
       requestaccident.setRoadCategory(new Long(2));
       requestaccident.setSpeedLimit(0);
       requestaccident.setBlock(new Long(3));
       requestaccident.setRoad(new Long(22));
       requestaccident.setRoadIntersection(new Long(2));
       requestaccident.setRoadTrafficControl(new Long(1));
        requestaccident.setRoadState(new Long(1));
requestaccident.setVirage(new Long(2));
requestaccident.setRoadSlopSection(new Long(2));
requestaccident.setRoadIntersection(new Long(2));
requestaccident.setAccidentType(new Long(1));
requestaccident.setImpactType(new Long(2));
requestaccident.setClimaticCondition(new Long(2));
requestaccident.setBrightnessCondition(new Long(1));
requestaccident.setAccidentSeverity(new Long(2));
requestaccident.setVehicules(listvehicule);
requestaccident.setPersons(personlist);
requestaccident.setCity(new Long(2));
requestaccident.setStatus(AccidentStatus.ACCEPTED);
requestaccident.setCauses(cause);
requestaccident.setCrashImages(crashImage);
requestaccident.setDrawing(imageformat);


System.out.println("------------------------------------ "+requestaccident);
Long ids=accidentservice.declareAccident(requestaccident, "23194af1-6320-465f-b8fa-283abfc35a0c");

Accident expectedvalue=accidentDao.getById(ids);



    }


    @Test
    void testDeleteCrashImage() {

    }

    @Test
    void testDeleteDrawing() {

    }

    @Test
    void testDeletePersonDocument() {

    }

    @Test
    void testDeletePersonImage() {

    }

    @Test
    void testDeleteVehicleDocument() {

    }

    @Test
    void testDeleteVehiculeImage() {

    }

    @Test
    void testGetAccidentList() {

    }

    @Test
    void testGetAllnotifications() {

    }

    @Test
    void testGetDataForAccident() {

    }

    @Test
    void testGetInsuranceAccidentList() {

    }

    @Test
    void testGetInsuranceName() {

    }

    @Test
    void testGetNotification() {

    }

    @Test
    void testGetOneAccident() {

    }

    @Test
    void testGetOneAccidentNew() {

    }

    @Test
    void testGetReport() {

    }

    @Test
    void testImportDrawing() {

    }

    @Test
    void testJoinPersonAccident() {

    }

    @Test
    void testModifyAccident() {

    }

    @Test
    void testPrintReport() {

    }

    @Test
    void testRejectInvesgation() {

    }

    @Test
    void testSaveCrashImage() {

    }

    @Test
    void testSaveDrawing() {

    }

    @Test
    void testSavePersonDocument() {

    }

    @Test
    void testSavePersonImage() {

    }

    @Test
    void testSaveVehicleDocument() {

    }

    @Test
    void testSaveVehiculeImage() {

    }

    @Test
    void testUnjoinPersonAccident() {

    }
}
