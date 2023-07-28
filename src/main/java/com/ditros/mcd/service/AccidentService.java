package com.ditros.mcd.service;

import com.ditros.mcd.dao.*;
import com.ditros.mcd.exception.AlreadyRelatedException;
import com.ditros.mcd.model.dto.*;
import com.ditros.mcd.model.entity.*;
import com.ditros.mcd.model.mappers.*;
import com.ditros.mcd.enumeration.AccidentStatus;
import com.ditros.mcd.util.DateUtil;
import com.ditros.mcd.util.ImageUtil;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Transactional
public class AccidentService {
    
    UserMonitoringDao userMoningtoring;
    AccidentActivityDao accidentActivityDao;
    HttpServletRequest request;
    private AccidentDao accidentDao;
    private DirectCauseDao directCauseDao;
    private PersonAccidentDao personAccidentDao;
    private AccidentTypeDao accidentTypeDao;
    private MunicipalityDao municipalityDao;
    private AccidentBrightnessConditionDao accidentBrightnessConditionDao;
    private AccidentClimaticConditionDao accidentClimaticConditionDao;
    private RoadTrafficControlDao roadTrafficControlDao;
    private AccidentSeverityDao accidentSeverityDao;
    private AccidentImpactTypeDao accidentImpactTypeDao;
    private VehicleSpecialFunctionDao vehicleSpecialFunctionDao;
    private VehicleTypeDao vehicleTypeDao;
    private VehicleBrandDao vehicleBrandDao;
    private VehicleModelDao vehicleModelDao;
    private VehicleDao vehicleDao;
    private VehicleActionDao vehicleActionDao;
    private RoadDao roadDao;
    private RoadStateDao roadStateDao;
    private RoadIntersectionDao roadIntersectionDao;
    private VirageDao virageDao;
    private RoadSlopSectionDao roadSlopSectionDao;
    private RoadTypeDao roadTypeDao;
    private RoadCategoryDao roadCategoryDao;
    private RoadBlockDao roadBlockDao;
    private PersonRoadTypeDao personRoadTypeDao;
    private AlcoholTestStatusDao alcoholTestStatusDao;
    private AlcoholTestResultDao alcoholTestResultDao;
    private AlcoholTestTypeDao alcoholTestTypeDao;
    private PersonAlcoholConsumptionDao personAlcoholConsumptionDao;
    private SeatingPlaceDao seatingPlaceDao;
    private SeatingRangeDao seatingRangeDao;
    private PersonActionDao personActionDao;
    private PersonTraumaSeverityDao traumaSeverityDao;
    private PersonDrugUseDao personDrugUseDao;
    private UserDao userDao;
    private CityDao cityDao;
    private PersonDao personDao;
    private PersonGenderDao personGenderDao;
    private CareDao careDao;
    private WearingHelmetDao wearingHelmetDao;
    private OccupantRestraintSystemDao occupantRestraintSystemDao;
    private VehicleAccidentDao vehicleAccidentDao;
    private DocumentTypeDao documentTypeDao;
    private ProfessionDao professionDao;
    
    private NotificationAccidentDao notificationAccidentDao;
    private ProfessionService professionService;
    private AccidentSeverityService severityService;
    private DirectCauseService directCauseService;
    private AccidentTypeService accidentTypeService;
    private ActionService actionService;
    private BrandService brandService;
    private BrightnessConditionService brightnessConditionService;
    private ClimaticConditionService climaticConditionService;
    private GenderService genderService;
    private ImpactTypeService impactTypeService;
    private MunicipalityService municipalityService;
    private OccupantRestraintSystemService restraintSystemService;
    private PersonRoadTypeService personRoadTypeService;
    private RoadBlockService blockService;
    private RoadCategoryService categoryService;
    private RoadIntersectionService intersectionService;
    private RoadService roadService;
    private RoadSlopSectionService slopSectionService;
    private RoadStateService stateService;
    private RoadTypeService roadTypeService;
    private SpecialFunctionService functionService;
    private TrafficControlService trafficControlService;
    private TraumaSeverityService traumaSeverityService;
    private VehicleModelService vehicleModelService;
    private VirageService virageService;
    private WearingHelmetService helmetService;
    private VehicleTypeService vehicleTypeService;
    private AlcoholTestTypeService alcoholTestTypeService;
    private AlcoholTestStatusService alcoholTestStatusService;
    private AlcoholConsumptionService alcoholConsumptionService;
    private SeatingRangeService seatingRangeService;
    private SeatingPlaceService seatingPlaceService;
    private PersonDrugUseService personDrugUseService;
    private AlcoholTestResultService alcoholTestResultService;
    private VehicleActionService vehicleActionService;
    private CityService cityService;
    private Environment env;
    private RestTemplate restTemplate;
    private DocumentTypeService documentTypeService;


    private RoadMapper roadMapper;
    private RoadTypeMapper roadTypeMapper;
    private RoadCategoryMapper roadCategoryMapper;
    private RoadBlockMapper roadBlockMapper;
    private RoadStateMapper roadStateMapper;
    private RoadIntersectionMapper roadIntersectionMapper;
    private TrafficControlMapper roadTrafficMapper;
    private VirageMapper virageMapper;
    private RoadSlopSectionMapper roadSlopSectionMapper;
    private AccidentTypeMapper accidentTypeMapper;
    private ImpactTypeMapper impactTypeMapper;
    private ClimaticConditionMapper climaticConditionMapper;
    private BrightnessConditionMapper brightnessConditionMapper;
    private AccidentSeverityMapper accidentSeverityMapper;
    private SpecialFunctionMapper specialFunctionMapper;
    private VehicleMapper vehicleMapper;
    private VehicleActionMapper actionMapper;
    private PersonRoadTypeMapper personRoadTypeMapper;
  private AccidentCommentDao accidentcommentDao;
    private AccidentMapper accidentMapper;
    private AlcoholConsumptionMapper alcoholConsumptionMapper;
    private AlcoholTestStatusMapper alcoholTestStatusMapper;
    private AlcoholTestTypeMapper alcoholTestTypeMapper;
    private AlcoholTestResultMapper alcoholTestResultMapper;
    private PersonDrugUseMapper personDrugUseMapper;
    private SeatingRangeMapper seatingRangeMapper;
    private SeatingPlaceMapper seatingPlaceMapper;
    private TraumaSeverityMapper traumaSeverityMapper;
    private ActionMapper actionMapper2;
    private WearingHelmetMapper wearingHelmetMapper;
    private OccupantRestraintSystemMapper occupantRestraintSystemMapper;
    private PersonMapper personMapper;
    private CityMapper cityMapper;
    private Environment environment;

    public Long modifyAccident(AccidentReq accidentReq, String userId) throws IOException {
      

        Optional<User> user = userDao.findByKeycloakId(userId);

        if(!user.isPresent()) throw new RuntimeException("Error Register your police account in app");
         System.out.println(accidentReq);
        List<VehicleAccident> vehiculesAccidents = new ArrayList<VehicleAccident>();
        List<PersonAccident> personAccidents = new ArrayList<PersonAccident>();
        List<DirectCause> directCauses = new ArrayList<DirectCause>();
        List<AccidentComment> comments = new ArrayList<AccidentComment>();
            Accident a = accidentDao.getById(accidentReq.getId());
            if(a.getVehicleAccidents() !=null){
                a.getVehicleAccidents().forEach(
                        vehicleAccident -> vehicleAccidentDao.delete(vehicleAccident)
                );
            }
            if(a.getPersonAccidentList() !=null)
                a.getPersonAccidentList().forEach(personAccident -> personAccidentDao.delete(personAccident));

            a.setDirectCauses(new ArrayList());

        

            a.setCrashDate(accidentReq.getAccidentDate());
            a.setCrashTime(accidentReq.getAccidentTime());
            a.setPlace(accidentReq.getPlace());
            a.setLatitude(accidentReq.getLatitude());
            a.setLongitude(accidentReq.getLongitude());
            a.setAccidentType(accidentTypeDao.getById(accidentReq.getAccidentType()));
            a.setMunicipality(municipalityDao.getById(accidentReq.getMunicipality()));
            a.setBrightnessCondition(accidentBrightnessConditionDao.getById(accidentReq.getBrightnessCondition()));
            a.setClimaticCondition(accidentClimaticConditionDao.getById(accidentReq.getClimaticCondition()));
            a.setAccidentSeverity(accidentSeverityDao.getById(accidentReq.getAccidentSeverity()));
            a.setTrafficControl(roadTrafficControlDao.getById(accidentReq.getRoadTrafficControl()));
            a.setImpactType(accidentImpactTypeDao.getById(accidentReq.getImpactType()));
            a.setRoad(roadDao.findById(accidentReq.getRoad()).orElse(null));
            a.setRoadType(roadTypeDao.getById(accidentReq.getRoadType()));
            a.setRoadCategory(roadCategoryDao.getById(accidentReq.getRoadCategory()));
            a.setSpeedLimit(accidentReq.getSpeedLimit());
            a.setBlock(roadBlockDao.getById(accidentReq.getBlock()));
            a.setRoadState( roadStateDao.getById(accidentReq.getRoadState()));
            a.setRoadIntersection(roadIntersectionDao.getById(accidentReq.getRoadIntersection()));
            a.setRoadTrafficControl(roadTrafficControlDao.getById(accidentReq.getRoadTrafficControl()));
            a.setVirage(virageDao.getById(accidentReq.getVirage()));
            a.setRoadSlopSection(roadSlopSectionDao.getById(accidentReq.getRoadSlopSection()));
            a.setCity(cityDao.getById(accidentReq.getCity()));
            a.setStatus(accidentReq.getStatus());
            a.setAccidentDate(DateUtil.DateFromText("dd/MM/yyyy HH:mm", accidentReq.getAccidentDate() + " " + accidentReq.getAccidentTime() ));
            if(!accidentReq.getComments().isEmpty()){ 
                // le pb vient des enregistrement qui sont de types differents
            for(AccidentCommentReq comment: accidentReq.getComments()){
                if(accidentcommentDao.getById(comment.getIdcomment())==null){
                comments.add(new AccidentComment(userId,comment.getComment(),a)) ;
            }else{
               AccidentComment comment1= accidentcommentDao.getById(comment.getIdcomment());
               comment1.setContent(comment.getComment());
                 comments.add(comment1);
           }
        accidentcommentDao.deleteById(comment.getIdcomment());
           
        }
               
           
            a.setComment(comments);}


            
        // System.out.println("persons =====> " +accidentReq.getPersons());
        // accidentReq.getVehicules().forEach(vehiculeReq -> System.out.println("Involved vehicles " + vehiculeReq.getPlate()));
        
        // accidentReq.getPersons().forEach(person -> System.out.println("Involved people " + person.getFirstName()+" "+ person.getLastName()));
        if(!accidentReq.getVehicules().isEmpty()){
        for(VehiculeReq vehiculeReq : accidentReq.getVehicules()){

            ResponseEntityResp responseEntityPOJO= new ResponseEntityResp();
            restTemplate = new RestTemplate();
            try{
                responseEntityPOJO
                        = restTemplate.getForObject(env.getProperty("insurance.url")+vehiculeReq.getPlate(), ResponseEntityResp.class);

            }catch (Exception e){
                e.printStackTrace();
                responseEntityPOJO.setData(new InsuranceValidityResp("NETWORK ERROR", false));
            }

            Vehicle ve =new Vehicle(
                    vehiculeReq.getChassis(),
                    Long.valueOf(vehiculeReq.getCylinderCapacity()),
                    Long.valueOf(vehiculeReq.getFabricationYear()),
                    vehicleTypeDao.findById(vehiculeReq.getType()).orElse(null),
                    vehicleBrandDao.findById(vehiculeReq.getBrand()).orElse(null),
                    vehicleModelDao.findById(vehiculeReq.getModel()).orElse(null),
                    null,
                    null
            );
            List<DocumentVehicle> documentVehicles = new ArrayList<>();
            System.out.println("-------------------------------------------------  vehicle document pass     -------------------------------------");
            if (vehiculeReq.getVehicleDocuments()!=null){
                vehiculeReq.getVehicleDocuments().forEach(documentReq -> {
                    DocumentVehicle document = new DocumentVehicle();
                    document.setIdentification(documentReq.getIdentification());
                    document.setRecipientFirstname(documentReq.getRecipientFirstname());
                    document.setRecipientLastname(documentReq.getRecipientLastname());
                    document.setIssueDate(DateUtil.DateDayFromText("yyyy-MM-dd", documentReq.getIssueDate()));
                    document.setExpireAt(DateUtil.DateDayFromText("yyyy-MM-dd", documentReq.getExpireAt()));
                    document.setImage(documentReq.getImage());
                    document.setType(documentTypeDao.getById(documentReq.getType()));
                    document.setVehicle(ve);
                    documentVehicles.add(document);
                });
                ve.setDocuments(documentVehicles);
            }
            System.out.println("-------------------------------------------------  vehicle accident pass     -------------------------------------");
            VehicleAccident v =new VehicleAccident(
                    vehiculeReq.getVehicleAccidentNumber(),
                    vehiculeReq.getPlate(),
                    vehicleSpecialFunctionDao.getById(vehiculeReq.getSpecialFunction()),
                    vehicleDao.findById(vehiculeReq.getVehicleId()).orElse(
                        ve
                    ),
                    vehicleActionDao.getById(vehiculeReq.getVehicleAction()),
                    a,
                    responseEntityPOJO.getData().getInsurranceName(),
                    responseEntityPOJO.getData().isValid(),
                    null
            );
            List<VehicleImage> vehicleImageList = new ArrayList<>();
            System.out.println("-------------------------------------------------  vehicle document pass     -------------------------------------"); 
            if(vehiculeReq.getVehicleImages()!=null){
                vehiculeReq.getVehicleImages().forEach(s -> {
                    vehicleImageList.add(new VehicleImage(s.getName(), v));
                });
            }

            v.setId(vehiculeReq.getId());
            v.setVehicleImages(vehicleImageList);
            vehiculesAccidents.add(
                    v
            );
        }
        
        a.setVehicleAccidents(vehiculesAccidents);
       }else{
        System.out.println(" ------------------- No Vehicule present ------------");
       }

       if(!accidentReq.getPersons().isEmpty()){
        accidentReq.getPersons().forEach(personDtoReq -> {
            List<DocumentPerson> documentPersons = new ArrayList<>();
            System.out.println(" ------------------- personne present ------------");
            Person pe = new Person(
                    personDtoReq.getFirstName(),
                    personDtoReq.getLastName(),
                    personDtoReq.getCni(),
                    personDtoReq.getTelephone(),
                    personDtoReq.getBirthDate(),
                    DateUtil.DateDayFromText("dd/MM/yyyy", personDtoReq.getBirthDate()) != null?
                            DateUtil.DateDayFromText("dd/MM/yyyy", personDtoReq.getBirthDate()).atStartOfDay()
                            : null,
                    personDtoReq.getAddress(),
                    personDtoReq.getStreet(),
                    personDtoReq.getPostal(),
                    personDtoReq.getLang(),
                    personDtoReq.getTel1(),
                    personDtoReq.getTel2(),
                    personDtoReq.getLatitude(),
                    personDtoReq.getLongitude(),

                    personGenderDao.getById(personDtoReq.getGender()),
                    userDao.getOne(new Long(1)),
                    new ArrayList<>(),
                    new ArrayList<>()
            );
            System.out.println(" ------------------- document personne present ------------");
            if(personDtoReq.getPersonDocuments()!=null){
                personDtoReq.getPersonDocuments().forEach(documentReq -> {
                    DocumentPerson document = new DocumentPerson();
                    document.setIdentification(documentReq.getIdentification());
                    document.setRecipientFirstname(documentReq.getRecipientFirstname());
                    document.setRecipientLastname(documentReq.getRecipientLastname());
                    document.setIssueDate(DateUtil.DateDayFromText("yyyy-MM-dd", documentReq.getIssueDate()));
                    document.setExpireAt(DateUtil.DateDayFromText("yyyy-MM-dd", documentReq.getExpireAt()));
                    document.setImage(documentReq.getImage());
                    document.setType(documentTypeDao.getById(documentReq.getType()));
                    document.setPerson(personDao.findById(personDtoReq.getPersonId()).orElse(pe));
                    documentPersons.add(document);
                });
                pe.setDocuments(documentPersons);
            }

            System.out.println(" ------------------- accident personne present ------------");
            System.out.println("------------------------------------------>"+accidentReq);
            PersonAccident p = new PersonAccident(
                    personDtoReq.getPersonAccidentNumber(),
                    personDtoReq.getVehicleAccidentNumber(),
                    personDtoReq.getVehicleLinkedPedestrian(),
                    personDtoReq.getDrivingLicenceYear(),
                    DateUtil.findAge(personDtoReq.getBirthDate()),
                    a,
                    personRoadTypeDao.getById(personDtoReq.getRoadType()),
                    personAlcoholConsumptionDao.getById(personDtoReq.getAlcoholConsumption()),
                    alcoholTestStatusDao.getById(personDtoReq.getTestStatus()),
                    personDtoReq.getTestValue(),
                    alcoholTestTypeDao.getById(personDtoReq.getTestResult()),
                    alcoholTestResultDao.getById(personDtoReq.getTestResult()),
                    personDrugUseDao.getById(personDtoReq.getDrugUse()),
                    seatingRangeDao.getById(personDtoReq.getRange()),
                    seatingPlaceDao.getById(personDtoReq.getPlace()),
                    traumaSeverityDao.getById(personDtoReq.getTraumaSeverity()),
                    personActionDao.getById(personDtoReq.getPersonAction()),
                    wearingHelmetDao.getById(personDtoReq.getWearingHelmet()),
                    occupantRestraintSystemDao.getById(personDtoReq.getOccupantRestraintSystem()),
                    personDao.save(pe),
                    careDao.findById(personDtoReq.getCare()).orElse(null),
                    null,
                    personDtoReq.getProfession()==null?
                            professionDao.getById(1L):professionDao.getById(personDtoReq.getProfession()),
                    personDtoReq.getDrivingLicence(),
                    personDtoReq.getDrivingLicenceType()


            );
            System.out.println(" -------------------  endpersonne present ------------");
            List<PersonImage> personImageList = new ArrayList<>();
            if(personDtoReq.getImages()!=null){
                personDtoReq.getImages().forEach(s -> {
                    personImageList.add(new PersonImage(s.getName(), p));
                });
                p.setPersonImages(personImageList);
            }
            System.out.println(" -------------------  image personne present ------------");
            
            personAccidents.add(
                    p
            );
            personAccidentDao.save(p);
            System.out.println(" ------------------- personne present add ------------");
        });
        a.setPersonAccidentList(personAccidents);
        accidentDao.save(a);
    }else{
        System.out.println(" ------------------- No person present ------------");
       }
        
        
        List<AccidentImage> accidentImageList = new ArrayList<>();
        accidentReq.getCrashImages().forEach(s -> {
            accidentImageList.add(new AccidentImage(s.getName(), a));
        });
        accidentReq.getCauses().forEach(aLong -> directCauses.add(directCauseDao.getById(aLong.getId())));
        
        a.setAccidentImages(accidentImageList);
        a.setCode(new AccidentCode(null, a));
        a.setDirectCauses(
            directCauses
        );

        a.setOrganization(user.get().getOrganization());
        a.setAccidentImages(accidentImageList);
        
        UserMonitoring monitoring=new UserMonitoring();
        monitoring.setAccidentId(accidentReq.getId());
        monitoring.setActivityId(accidentActivityDao.getById(new Long(7)).getId());
        monitoring.setUserId(user.get().getId());

        return a.getId();
    }


    public Long declareAccident(AccidentReq accidentReq, String userId) {
        List<VehicleAccident> vehiculesAccidents = new ArrayList<VehicleAccident>();
        List<PersonAccident> personAccidents = new ArrayList<PersonAccident>();
        List<DirectCause> directCauses = new ArrayList<DirectCause>();

        Optional<User> user = userDao.findByKeycloakId(userId);
          System.out.println(accidentReq);
        if(!user.isPresent()) throw new RuntimeException("Error Register your police account in app");
        List<AccidentComment> comment =new ArrayList<AccidentComment>();
       
        final Accident accident = new Accident(
                null,
                accidentReq.getAccidentDate(),
                accidentReq.getAccidentTime(),
                accidentReq.getPlace(),
                accidentReq.getLatitude(),
                accidentReq.getLongitude(),
                accidentTypeDao.getById(accidentReq.getAccidentType()),
                municipalityDao.getById(accidentReq.getMunicipality()),
                accidentBrightnessConditionDao.getById(accidentReq.getBrightnessCondition()),
                accidentClimaticConditionDao.getById(accidentReq.getClimaticCondition()),
                roadTrafficControlDao.getById(accidentReq.getRoadTrafficControl()),
                accidentSeverityDao.getById(accidentReq.getAccidentSeverity()),
                accidentImpactTypeDao.getById(accidentReq.getImpactType()),
                new ArrayList<VehicleAccident>(){{
                }},
                roadDao.findById(accidentReq.getRoad()).orElse(null),
                roadTypeDao.getById(accidentReq.getRoadType()),
                roadCategoryDao.getById(accidentReq.getRoadCategory()),
                accidentReq.getSpeedLimit(),
                roadBlockDao.getById(accidentReq.getBlock()),
                roadStateDao.getById(accidentReq.getRoadState()),
                roadIntersectionDao.getById(accidentReq.getRoadIntersection()),
                roadTrafficControlDao.getById(accidentReq.getRoadTrafficControl()),
                virageDao.getById(accidentReq.getVirage()),
                roadSlopSectionDao.getById(accidentReq.getRoadSlopSection()),
                user.orElse(null),
                new ArrayList<PersonAccident>(){{
                }},
                cityDao.getById(accidentReq.getCity()),
                AccidentStatus.OPENED,
                DateUtil.DateFromText("dd/MM/yyyy HH:mm", accidentReq.getAccidentDate() + " " + accidentReq.getAccidentTime() ),
                null,
                null,
                null,
                null,
                null,
                null,
                null,null,comment
        );
         if(!accidentReq.getComments().isEmpty()){
         List<AccidentComment> commente =new ArrayList<AccidentComment>();
        for(AccidentCommentReq comments:accidentReq.getComments()){
           commente.add(new AccidentComment(userId,comments.getComment(),accident));

        }
    accident.setComment(commente);
    
    }
        System.out.println("persons =====> " +accidentReq.getPersons());
        accidentReq.getVehicules().forEach(vehiculeReq -> System.out.println("Involved vehicles " + vehiculeReq.getPlate()));
        //accidentReq.getPersons().forEach(person -> System.out.println("Involved people " + person.getFirstName()+" "+ person.getLastName()));

        for(VehiculeReq vehiculeReq : accidentReq.getVehicules()){

            ResponseEntityResp responseEntityPOJO= new ResponseEntityResp();
            restTemplate = new RestTemplate();
             try{
                 responseEntityPOJO
                         = restTemplate.getForObject(env.getProperty("insurance.url")+vehiculeReq.getPlate(), ResponseEntityResp.class);

             }catch (Exception e){
                 e.printStackTrace();
                 responseEntityPOJO.setData(new InsuranceValidityResp("NETWORK ERROR", false));
             }
            System.out.println("vehicule =====> " + accidentReq.getVehicules());
            Vehicle ve =new Vehicle(
                    vehiculeReq.getChassis(),
                    Long.valueOf(vehiculeReq.getCylinderCapacity()),
                    Long.valueOf(vehiculeReq.getFabricationYear()),
                    vehicleTypeDao.findById(vehiculeReq.getType()).orElse(null),
                    vehicleBrandDao.findById(vehiculeReq.getBrand()).orElse(null),
                    vehicleModelDao.findById(vehiculeReq.getModel()).orElse(null),
                    null,
                    null
            );
            
            List<DocumentVehicle> documentVehicles = new ArrayList<>();

            if (vehiculeReq.getVehicleDocuments()!=null){
                vehiculeReq.getVehicleDocuments().forEach(documentReq -> {
                    DocumentVehicle document = new DocumentVehicle();
                    document.setIdentification(documentReq.getIdentification());
                    document.setRecipientFirstname(documentReq.getRecipientFirstname());
                    document.setRecipientLastname(documentReq.getRecipientLastname());
                    document.setIssueDate(DateUtil.DateDayFromText("yyyy-MM-dd", documentReq.getIssueDate()));
                    document.setExpireAt(DateUtil.DateDayFromText("yyyy-MM-dd", documentReq.getExpireAt()));
                    document.setImage(documentReq.getImage());
                    document.setType(documentTypeDao.getById(documentReq.getType()));
                    document.setVehicle(ve);
                    documentVehicles.add(document);
                });
                ve.setDocuments(documentVehicles);
            }
            System.out.println("vehicule1 kkk =====> " + responseEntityPOJO.getData());
            VehicleAccident v =new VehicleAccident(
                    vehiculeReq.getVehicleAccidentNumber(),
                    vehiculeReq.getPlate(),
                    vehicleSpecialFunctionDao.getById(vehiculeReq.getSpecialFunction()),
                    vehicleDao.findById(vehiculeReq.getVehicleId()).orElse(ve),
                    vehicleActionDao.getById(vehiculeReq.getVehicleAction()),
                    accident,
                   "",//responseEntityPOJO.getData().getInsurranceName(),
                    true,//responseEntityPOJO.getData().isValid(),
                    null
            );
            System.out.println("vehicule1 =====> " + accidentReq.getVehicules());
            List<VehicleImage> vehicleImageList = new ArrayList<>();
            
            if(vehiculeReq.getVehicleImages()!=null){
                vehiculeReq.getVehicleImages().forEach(s -> {
                    vehicleImageList.add(new VehicleImage(s.getName(), v));
                });
            }
            System.out.println("vehicule1 =====> idddddd ");
            //v.setId(vehiculeReq.getId());
            v.setVehicleImages(vehicleImageList);
            vehiculesAccidents.add(
                    v
            );
        }
        if(accidentReq.getPersons()!=null || !accidentReq.getPersons().isEmpty() ){
        accidentReq.getPersons().forEach(personDtoReq -> {
            List<DocumentPerson> documentPersons = new ArrayList<>();
            Person pe = new Person(
                    personDtoReq.getFirstName(),
                    personDtoReq.getLastName(),
                    personDtoReq.getCni(),
                    personDtoReq.getTelephone(),
                    personDtoReq.getBirthDate(),
                    DateUtil.DateDayFromText("dd/MM/yyyy", personDtoReq.getBirthDate()) != null?
                            DateUtil.DateDayFromText("dd/MM/yyyy", personDtoReq.getBirthDate()).atStartOfDay()
                            : null,
                    personDtoReq.getAddress(),
                    personDtoReq.getStreet(),
                    personDtoReq.getPostal(),
                    personDtoReq.getLang(),
                    personDtoReq.getTel1(),
                    personDtoReq.getTel2(),
                    personDtoReq.getLatitude(),
                    personDtoReq.getLongitude(),
                    personGenderDao.getById(personDtoReq.getGender()),
                    userDao.getById(new Long(2)),
                    new ArrayList<>(),
                    new ArrayList<>()
            );System.out.println("vehicule1 =====> idddddd1");
            
            if(personDtoReq.getPersonDocuments()!=null || !personDtoReq.getPersonDocuments().isEmpty()){
                personDtoReq.getPersonDocuments().forEach(documentReq -> {
                    DocumentPerson document = new DocumentPerson();
                    document.setIdentification(documentReq.getIdentification());
                    document.setRecipientFirstname(documentReq.getRecipientFirstname());
                    document.setRecipientLastname(documentReq.getRecipientLastname());
                    document.setIssueDate(DateUtil.DateDayFromText("yyyy-MM-dd", documentReq.getIssueDate()));
                    document.setExpireAt(DateUtil.DateDayFromText("yyyy-MM-dd", documentReq.getExpireAt()));
                    document.setImage(documentReq.getImage());
                    document.setType(documentTypeDao.getById(documentReq.getType()));
                    document.setPerson(personDao.findById(personDtoReq.getPersonId()).orElse(pe));
                    documentPersons.add(document);
                });
                pe.setDocuments(documentPersons);System.out.println("vehicule1 =====> idddddd2 ");
            }

            PersonAccident p = new PersonAccident(
                    personDtoReq.getPersonAccidentNumber(),
                    personDtoReq.getVehicleAccidentNumber(),
                    personDtoReq.getVehicleLinkedPedestrian(),
                    personDtoReq.getDrivingLicenceYear(),
                    DateUtil.findAge(personDtoReq.getBirthDate()),
                    accident,
                    personRoadTypeDao.getById(personDtoReq.getRoadType()),
                    personAlcoholConsumptionDao.getById(personDtoReq.getAlcoholConsumption()),
                    alcoholTestStatusDao.getById(personDtoReq.getTestStatus()),
                    personDtoReq.getTestValue(),
                    
                    alcoholTestTypeDao.getById(personDtoReq.getTestType()),
                    alcoholTestResultDao.getById(personDtoReq.getTestResult()),
                    personDrugUseDao.getById(personDtoReq.getDrugUse()),
                    seatingRangeDao.getById(personDtoReq.getRange()),
                    seatingPlaceDao.getById(personDtoReq.getPlace()),
                    traumaSeverityDao.getById(personDtoReq.getTraumaSeverity()),
                    personActionDao.getById(personDtoReq.getPersonAction()),
                    wearingHelmetDao.getById(personDtoReq.getWearingHelmet()),
                    occupantRestraintSystemDao.getById(personDtoReq.getOccupantRestraintSystem()),
                    pe,
                    careDao.findById(personDtoReq.getCare()).orElse(null),
                    null,
                    personDtoReq.getProfession()==null?
                            professionDao.getById(new Long(1)):professionDao.getById(personDtoReq.getProfession()),
                    personDtoReq.getDrivingLicence(),
                    personDtoReq.getDrivingLicenceType()


            );
            System.out.println("vehicule1 =====> idddddd3 ");
            p.setId(personDtoReq.getId());
            System.out.println("vehicule1 =====> idddddd4 ");
            List<PersonImage> personImageList = new ArrayList<>();
            if(personDtoReq.getImages()!=null){
                personDtoReq.getImages().forEach(s -> {
                    personImageList.add(new PersonImage(s.getName(), p));
                });
            }

            p.setPersonImages(personImageList);
            personAccidents.add(
                    p
            );
        });}
        List<AccidentImage> accidentImageList = new ArrayList<>();
        accidentReq.getCrashImages().forEach(s -> {
            accidentImageList.add(new AccidentImage(s.getName(), accident));
        });
        accidentReq.getCauses().forEach(aLong -> directCauses.add(directCauseDao.getById(aLong.getId())));
        accident.setVehicleAccidents(vehiculesAccidents);
        accident.setPersonAccidentList(personAccidents);
        accident.setAccidentImages(accidentImageList);
        accident.setCode(new AccidentCode(null, accident));
        accident.setId(accidentReq.getId());
        accident.setDirectCauses(
            directCauses
        );

        accident.setOrganization(user.get().getOrganization());
        accident.setAccidentImages(accidentImageList);
        accidentDao.save(accident);


        return accident.getId();
    }

    public AccidentRespOne getOneAccident(Long id){
        Optional<Accident> optAccident = accidentDao.findById(id);
        Accident accident = optAccident.orElseThrow(() -> new RuntimeException("Please verify accident id"));
        List<AccidentCommentReq> comments=new ArrayList<AccidentCommentReq>();
        for(AccidentComment comment: accident.getComment()){
            AccidentCommentReq c=new AccidentCommentReq();
            c.setIdcomment(comment.getId());
            c.setComment(comment.getContent());
                comments.add(c);
        }
        return new AccidentRespOne(
                accident.getId(),
                accident.getCrashDate(),
                accident.getCrashTime(),
                municipalityDao.findById(accident.getMunicipality().getId()).orElse(null).getName(),
                accident.getLatitude(),
                accident.getLongitude(),
                accident.getPlace(),
                roadMapper.fromRoad(accident.getRoad()),
                roadTypeMapper.fromRoadType(accident.getRoadType()),
                roadCategoryMapper.fromRoadCategory(accident.getRoadCategory()),
                accident.getSpeedLimit(),
                roadBlockMapper.blockResp(accident.getBlock()),
                roadStateMapper.fromRoadState(accident.getRoadState()),
                roadIntersectionMapper.fromRoadIntersection(accident.getRoadIntersection()),
                roadTrafficMapper.fromTrafficControlResp(accident.getTrafficControl()),
                virageMapper.fromVirage(accident.getVirage()),
                roadSlopSectionMapper.fromRoadSlopSection(accident.getRoadSlopSection()),
                accidentTypeMapper.fromAccidentType(accident.getAccidentType()),
                impactTypeMapper.fromImpactType(accident.getImpactType()),
                climaticConditionMapper.fromClimaticCondition(accident.getClimaticCondition()),
                brightnessConditionMapper.fromBrightnessCondition(accident.getBrightnessCondition()),
                accidentSeverityMapper.fromSeverity(accident.getAccidentSeverity()),
                accident.getVehicleAccidents().stream()
                        .map(vehicleAccident ->
                        {
                            try {
                                return new VehicleAccidentResp(vehicleAccident.getId(),
                                        vehicleAccident.getVehicleAccidentNumber(),
                                        vehicleAccident.getPlateNumber(),
                                        specialFunctionMapper.fromSpecialFunction(vehicleAccident.getSpecialFunction()),
                                                vehicleMapper.fromVehicle(vehicleAccident.getVehicle()),
                                                actionMapper.fromVehicleAction(vehicleAccident.getAction()),
                                                vehicleAccident.getInsuranceName(),
                                                vehicleAccident.isInsuranceValid(),
                                        ImageUtil.getImageFromDisk(environment.getProperty("image.accident.vehicle.path") + File.separator + "VEHICLE"+ accident.getCode()));
                            } catch (IOException e) {
                                System.err.println("Error while getting vehicule image");
                                e.printStackTrace();
                                return new VehicleAccidentResp(vehicleAccident.getId(),
                                        vehicleAccident.getVehicleAccidentNumber(),
                                        vehicleAccident.getPlateNumber(),
                                        specialFunctionMapper.fromSpecialFunction(vehicleAccident.getSpecialFunction()),
                                        vehicleMapper.fromVehicle(vehicleAccident.getVehicle()),
                                        actionMapper.fromVehicleAction(vehicleAccident.getAction()),
                                        vehicleAccident.getInsuranceName(),
                                        vehicleAccident.isInsuranceValid(),
                                        null);

                            }
                        })
                        .collect(Collectors.toList()),
                accident.getPersonAccidentList().stream()
                        .map(personAccident -> {
                            try {
                                return new PersonAccidentResp(
                                        personAccident.getId(),
                                        personAccident.getPersonNumber(),
                                        personAccident.getVehicleNumber(),
                                        personAccident.getVehicleLinkedPedestrian(),
                                        personAccident.getDrivingLicenceYear(),
                                        personAccident.getAge(),
                                        personRoadTypeMapper.fromPersonRoadType(personAccident.getRoadType()),
                                        alcoholConsumptionMapper.fromAlcoholConsumption(personAccident.getAlcoholConsumption()),
                                        alcoholTestStatusMapper.fromAlcoholTestStatus(personAccident.getTestStatus()),
                                        alcoholTestTypeMapper.fromAlcoholTestType(personAccident.getTestType()),
                                        alcoholTestResultMapper.fromAlcoholResult(personAccident.getTestResult()),
                                        personDrugUseMapper.fromPersonDrugUse(personAccident.getDrugUse()),
                                        seatingRangeMapper.fromRange(personAccident.getRange()),
                                        seatingPlaceMapper.fromPlace(personAccident.getPlace()),
                                        traumaSeverityMapper.fromTraumaSeverity(personAccident.getTraumaSeverity()),
                                        actionMapper2.fromAction(personAccident.getAction()),
                                        wearingHelmetMapper.fromWearingHelmet(personAccident.getWearingHelmet()),
                                        occupantRestraintSystemMapper.fromOccupantRestraintSystem(personAccident.getOccupantRestraintSystem()),
                                        personMapper.fromPerson(personAccident.getPerson()),
                                        ImageUtil.getImageFromDisk(environment.getProperty("image.accident.vehicle.path") + File.separator + "VEHICLE"+ accident.getCode())

                                );
                            } catch (IOException e) {
                                System.err.println("Error while getting person image");
                                e.printStackTrace();
                                return new PersonAccidentResp(
                                        personAccident.getId(),
                                        personAccident.getPersonNumber(),
                                        personAccident.getVehicleNumber(),
                                        personAccident.getVehicleLinkedPedestrian(),
                                        personAccident.getDrivingLicenceYear(),
                                        personAccident.getAge(),
                                        personRoadTypeMapper.fromPersonRoadType(personAccident.getRoadType()),
                                        alcoholConsumptionMapper.fromAlcoholConsumption(personAccident.getAlcoholConsumption()),
                                        alcoholTestStatusMapper.fromAlcoholTestStatus(personAccident.getTestStatus()),
                                        alcoholTestTypeMapper.fromAlcoholTestType(personAccident.getTestType()),
                                        alcoholTestResultMapper.fromAlcoholResult(personAccident.getTestResult()),
                                        personDrugUseMapper.fromPersonDrugUse(personAccident.getDrugUse()),
                                        seatingRangeMapper.fromRange(personAccident.getRange()),
                                        seatingPlaceMapper.fromPlace(personAccident.getPlace()),
                                        traumaSeverityMapper.fromTraumaSeverity(personAccident.getTraumaSeverity()),
                                        actionMapper2.fromAction(personAccident.getAction()),
                                        wearingHelmetMapper.fromWearingHelmet(personAccident.getWearingHelmet()),
                                        occupantRestraintSystemMapper.fromOccupantRestraintSystem(personAccident.getOccupantRestraintSystem()),
                                        personMapper.fromPerson(personAccident.getPerson()),
                                        null
                                );
                            }
                        })
                        .collect(Collectors.toList()),
                cityMapper.fromCity(accident.getCity()),
                comments
                
        );
    }

    public AccidentListResp completeInvesgation(InvestigationStatusReq investigationStatusReq, String userId){
        Optional<Accident> optAccident = accidentDao.findById(investigationStatusReq.getId());
        Accident accident = optAccident.orElseThrow(() -> new RuntimeException("Please verify accident id"));
        Optional<User> optUser = userDao.findByKeycloakId(userId);

        if(!optUser.isPresent())
            new RuntimeException("You are not authorized to do this action, please register in database");


        User user = optUser.get();
        accident.setStatus(AccidentStatus.READY);
        accident.setAgentSignature(investigationStatusReq.getSignature().getBytes(StandardCharsets.UTF_8));
        accidentDao.save(accident);
        List<Map<String, String>> crashImageList = new ArrayList<>();
          accident.getAccidentImages().stream().forEach(crashImage ->
                            {
                                Map<String, String> map = new HashMap<>();
                                map.put("path",environment.getProperty("data.collect.address")
                                        +":"+environment.getProperty("data.collect.port")+"/public/images/accident/crash/"
                                        +  crashImage.getName());
                                map.put("name", crashImage.getName());
                                crashImageList.add(map);
                            });
                            AccidentReport accidentReport = accident.getAccidentReport();
                                              
        return new AccidentListResp(
            accident.getId(),
            accident.getPlace(),
            accident.getAccidentSeverity().getValue(),
            accident.getCrashDate(),
            accident.getPersonAccidentList().size(),
            0,
            accident.getCity().getName(),
            (user.getPerson().getLastName()==null?"":user.getPerson().getLastName())
                    + " " +(user.getPerson().getFirstName()==null?"":user.getPerson().getFirstName()),
            accident.getStatus(),
            "",
            "",
            accident.getCode()==null? "":accident.getCode().getCode(),
            0L,
            0,
            0,
                accident.getCrashTime(), 0l,
                accident.getDrawing(),
                accident.getOrganization().getType()
            // crashImageList,
            // new AccidentReportReq(
            //                         accidentReport.getId(),
            //                         accident.getId(),
            //                         accidentReport.getAuthors(),
            //                         accidentReport.getAssist(),
            //                         accidentReport.getSaw(),
            //                         accidentReport.getCirculation(),
            //                         accidentReport.getPatrol()
            //                 )      
                  
        );

    }

    public Map<String, String> saveVehiculeImage(MultipartFile vehicleImage) throws IOException {
            String name= "VEHICLE"+ DateUtil.textFromDate("yyyyMMddHHmmssSSS", LocalDateTime.now());
            String path =environment.getProperty("image.accident.vehicle.path") + File.separator + name +".png";

            File f3= new File(environment.getProperty("image.accident.vehicle.path"));
            if(!f3.exists()) f3.mkdirs();
            Files.copy(vehicleImage.getInputStream(), Paths.get(path), StandardCopyOption.REPLACE_EXISTING);
            Map<String, String> map = new HashMap<>();
            map.put("path",environment.getProperty("data.collect.address")
                    +":"+environment.getProperty("data.collect.port")+"/public/images/accident/vehicles/"
                    +  name+".png");
            map.put("name", name+".png");
        return map;
    }

    public String deleteVehiculeImage(String name, Long vehicleId) throws IOException {
        VehicleAccident vehicleAccident = vehicleAccidentDao.getById(vehicleId);
        vehicleAccident.getVehicleImages().stream().forEach(accidentImage -> {
            if(accidentImage.getName().equals(name)){
                accidentImage.setDeleted(true);
            }
        });
        vehicleAccidentDao.save(vehicleAccident);
        File f3= new File(environment.getProperty("image.accident.vehicle.path")+ File.separator + name);
        if(f3.delete()) return "success"; else throw new RuntimeException("Error while deleting");

    }

    public Map<String, String> saveVehicleDocument(MultipartFile vehicleDoc) throws IOException {
        String name= "DOCUMENT"+ DateUtil.textFromDate("yyyyMMddHHmmssSSS", LocalDateTime.now());
        String path =environment.getProperty("image.accident.vehicle.document.path") + File.separator + name+".png";

        File f3= new File(environment.getProperty("image.accident.vehicle.document.path"));
        if(!f3.exists()) f3.mkdirs();
        Files.copy(vehicleDoc.getInputStream(), Paths.get(path), StandardCopyOption.REPLACE_EXISTING);

        Map<String, String> map = new HashMap<>();
        map.put("path",environment.getProperty("data.collect.address")
                +":"+environment.getProperty("data.collect.port")+"/public/images/accident/vehicles/documents/"
                +  name+".png");
        map.put("name", name+".png");
        return map;
    }
    public String deleteVehicleDocument(String name, Long vehicleId) throws IOException {
        VehicleAccident vehicleAccident = vehicleAccidentDao.getById(vehicleId);
        vehicleAccident.getVehicle().getDocuments().stream().forEach(doc -> {
            if(doc.getImage().equals(name)){
                doc.setDeleted(true);
            }
        });
        vehicleAccidentDao.save(vehicleAccident);
        File f3= new File(environment.getProperty("image.accident.vehicle.document.path")+ File.separator + name);
        if(f3.delete()) return "success"; else throw new RuntimeException("Error while deleting vehicule document");
    }

    public Map<String, String> saveDrawing(MultipartFile vehicleImage, String id) throws IOException {

        Accident accident = accidentDao.getById(Long.valueOf(id));
        String name= "DRAWING"+ DateUtil.textFromDate("yyyyMMddHHmmssSSS", LocalDateTime.now());
        accident.setDrawing(name);
        String path =environment.getProperty("image.accident.draw.path") + File.separator + name+".png";
        File f3= new File(environment.getProperty("image.accident.draw.path"));
        if(!f3.exists()) f3.mkdirs();
        Files.copy(vehicleImage.getInputStream(), Paths.get(path), StandardCopyOption.REPLACE_EXISTING);
        accidentDao.save(accident);
        Map<String, String> map = new HashMap<>();
        map.put("path",environment.getProperty("data.collect.address")
                +":"+environment.getProperty("data.collect.port")+"/public/images/accident/drawing/"
                +  name+".png");
        map.put("name", name+".png");
        return map;
    }

    public Map<String, String> importDrawing(MultipartFile vehicleImage, String id) throws IOException {

        Accident accident = accidentDao.getById(Long.valueOf(id));
        String name= "DRAWING"+ DateUtil.textFromDate("yyyyMMddHHmmssSSS", LocalDateTime.now());
        accident.setDrawing(name);
        String path =environment.getProperty("image.accident.draw.path") + File.separator + name+".png";
        File f3= new File(environment.getProperty("image.accident.draw.path"));
        if(!f3.exists()) f3.mkdirs();
        Files.copy(vehicleImage.getInputStream(), Paths.get(path), StandardCopyOption.REPLACE_EXISTING);
        accidentDao.save(accident);
        Map<String, String> map = new HashMap<>();
        map.put("path",environment.getProperty("data.collect.address")
                +":"+environment.getProperty("data.collect.port")+"/public/images/accident/drawing/"
                +  name+".png");
        map.put("name", name+".png");
        return map;
    }

    public String deleteDrawing(String name, Long accidentId) throws IOException {
        Accident accident = accidentDao.getById(accidentId);
        if(!accident.getDrawing().equals(name)) throw new RuntimeException("Verify file name!");
        accident.setDrawing(null);
        accidentDao.save(accident);
        File f3= new File(environment.getProperty("image.accident.draw.path")+ File.separator + name);
        if(f3.delete()) return "success"; else throw new RuntimeException("Error while deleting drawing");

    }

    public Map<String, String> savePersonImage(MultipartFile personImage) throws IOException {
        String name= "PERSON"+ DateUtil.textFromDate("yyyyMMddHHmmssSSS", LocalDateTime.now());
        String path =environment.getProperty("image.accident.person.path") + File.separator + name+".png";

        File f3= new File(environment.getProperty("image.accident.person.path"));
        if(!f3.exists()) f3.mkdirs();
        Files.copy(personImage.getInputStream(), Paths.get(path), StandardCopyOption.REPLACE_EXISTING);

        Map<String, String> map = new HashMap<>();
        map.put("path",environment.getProperty("data.collect.address")
                +":"+environment.getProperty("data.collect.port")+"/public/images/accident/persons/"
                +  name+".png");
        map.put("name", name+".png");
        return map;
    }

    public Map<String, String> savePersonDocument(MultipartFile personImage) throws IOException {
        String name= "DOCUMENT"+ DateUtil.textFromDate("yyyyMMddHHmmssSSS", LocalDateTime.now());
        String path =environment.getProperty("image.accident.person.document.path") + File.separator + name+".png";

        File f3= new File(environment.getProperty("image.accident.person.document.path"));
        if(!f3.exists()) f3.mkdirs();
        Files.copy(personImage.getInputStream(), Paths.get(path), StandardCopyOption.REPLACE_EXISTING);

        Map<String, String> map = new HashMap<>();
        map.put("path",environment.getProperty("data.collect.address")
                +":"+environment.getProperty("data.collect.port")+"/public/images/accident/persons/documents/"
                +  name+".png");
        map.put("name", name+".png");
        return map;
    }
    public String deletePersonDocument(String name, Long personId) throws IOException {
        PersonAccident personAccident = personAccidentDao.getById(personId);
        personAccident.getPerson().getDocuments().stream().forEach(doc -> {
            if(doc.getImage().equals(name)){
                doc.setDeleted(true);
            }
        });
        personAccidentDao.save(personAccident);

        File f3= new File(environment.getProperty("image.accident.person.document.path")+ File.separator + name);
        if(f3.delete()) return "success"; else throw new RuntimeException("Error while deleting person document");
    }
    public String deletePersonImage(String name, Long personId) throws IOException {
        PersonAccident personAccident = personAccidentDao.getById(personId);
        personAccident.getPersonImages().stream().forEach(accidentImage -> {
            if(accidentImage.getName().equals(name)){
                accidentImage.setDeleted(true);
            }
        });
        personAccidentDao.save(personAccident);

        File f3= new File(environment.getProperty("image.accident.person.path")+ File.separator + name);
        if(f3.delete()) return "success"; else throw new RuntimeException("Error while deleting");
    }

    public Map<String, String> saveCrashImage(MultipartFile crashImage) throws IOException {
        String name= "CRASH"+ DateUtil.textFromDate("yyyyMMddHHmmssSSS", LocalDateTime.now());
        String path =environment.getProperty("image.accident.crash.path") + File.separator + name+".png";

        File f3= new File(environment.getProperty("image.accident.crash.path"));
        if(!f3.exists()) f3.mkdirs();
        Files.copy(crashImage.getInputStream(), Paths.get(path), StandardCopyOption.REPLACE_EXISTING);
        Map<String, String> map = new HashMap<>();
        map.put("path",environment.getProperty("data.collect.address")
                +":"+environment.getProperty("data.collect.port")+"/public/images/accident/crash/"
                +  name+".png");
        map.put("name", name+".png");

        return map;
    }

    public String deleteCrashImage(String name, Long accidentId) throws IOException {
        Accident accident = accidentDao.getById(accidentId);
        accident.getAccidentImages().stream().forEach(accidentImage -> {
            if(accidentImage.getName().equals(name)){
                accidentImage.setDeleted(true);
            }
        });
        accidentDao.save(accident);
        File f3= new File(environment.getProperty("image.accident.crash.path")+ File.separator + name );
        if(f3.delete()) return "success"; else throw new RuntimeException("Error while deleting");
    }

    public AccidentListResp acceptInvesgation(InvestigationStatusReq investigationStatusReq, String userId){
        Optional<Accident> optAccident = accidentDao.findById(investigationStatusReq.getId());
        Accident accident = optAccident.orElseThrow(() -> new RuntimeException("Please verify accident id"));
        Optional<User> optUser = userDao.findByKeycloakId(userId);

        if(!optUser.isPresent())
            new RuntimeException("You are not authorized to do this action, please register in database");


        User user = optUser.get();

        accident.setStatus(AccidentStatus.ACCEPTED);
        accident.setManagerSignature(investigationStatusReq.getSignature().getBytes(StandardCharsets.UTF_8));
        accidentDao.save(accident);
 List<Map<String, String>> crashImageList = new ArrayList<>();
          accident.getAccidentImages().stream().forEach(crashImage ->
                            {
                                Map<String, String> map = new HashMap<>();
                                map.put("path",environment.getProperty("data.collect.address")
                                        +":"+environment.getProperty("data.collect.port")+"/public/images/accident/crash/"
                                        +  crashImage.getName());
                                map.put("name", crashImage.getName());
                                crashImageList.add(map);
                            });

        
        return new AccidentListResp(accident.getId(),
                accident.getPlace(),
                accident.getAccidentSeverity().getValue(),
                accident.getCrashDate(),
                accident.getPersonAccidentList().size(),
                0,
                accident.getCity().getName(),
                (user.getPerson().getLastName()==null?"":user.getPerson().getLastName())
                        + " " +(user.getPerson().getFirstName()==null?"":user.getPerson().getFirstName()),
                accident.getStatus(),
                "",
                "",
                accident.getCode()==null? "":accident.getCode().getCode(),
                0L,
                0,
                0,
                accident.getCrashTime(), 0l,
                accident.getDrawing(),
                accident.getOrganization().getType()
        //         crashImageList,
        //          new AccidentReportReq(
        //         accidentReport.getId(),
        //         accident.getId(),
        //         accidentReport.getAuthors(),
        //         accidentReport.getAssist(),
        //         accidentReport.getSaw(),
        //         accidentReport.getCirculation(),
        //         accidentReport.getPatrol()
        // )
        );

    }

    public AccidentReq joinPersonAccident(JoinPersonAccidentReq joinPersonAccidentReq) throws AlreadyRelatedException {
        Optional<Care> optCare = careDao.findById(joinPersonAccidentReq.getCareId());
        Care care = optCare.orElseThrow(() -> new RuntimeException("Wrong id select a correct medical folder!"));
        Optional<PersonAccident> optionalPersonAccident = personAccidentDao.findById(joinPersonAccidentReq.getPersonAccidentId());
        PersonAccident personAccident = optionalPersonAccident.orElseThrow(() -> new RuntimeException("Wrong id select a correct accident person!"));
        personAccident.setCare(care);
        personAccident = personAccidentDao.save(personAccident);
        return accidentMapper.fromAccident(personAccident.getAccident());
    }

    public boolean unjoinPersonAccident(JoinPersonAccidentReq joinPersonAccidentReq){
        Optional<PersonAccident> optionalPersonAccident = personAccidentDao.findById(joinPersonAccidentReq.getPersonAccidentId());
        PersonAccident personAccident = optionalPersonAccident.orElseThrow(() -> new RuntimeException("Wrong id select a correct accident person!"));
        personAccident.setCare(null);
        personAccidentDao.save(personAccident);

        return true;

    }

    public AccidentListResp rejectInvesgation(Long accidentId, String userId){
        Optional<Accident> optAccident = accidentDao.findById(accidentId);
        Accident accident = optAccident.orElseThrow(() -> new RuntimeException("Please verify accident id"));
        Optional<User> optUser = userDao.findByKeycloakId(userId);
        if(!optUser.isPresent())
            new RuntimeException("You are not authorized to do this action, please register in database");
        User user = optUser.get();
        accident.setStatus(AccidentStatus.REJECTED);
        accidentDao.save(accident);
 List<Map<String, String>> crashImageList = new ArrayList<>();
          accident.getAccidentImages().stream().forEach(crashImage ->
                            {
                                Map<String, String> map = new HashMap<>();
                                map.put("path",environment.getProperty("data.collect.address")
                                        +":"+environment.getProperty("data.collect.port")+"/public/images/accident/crash/"
                                        +  crashImage.getName());
                                map.put("name", crashImage.getName());
                                crashImageList.add(map);
                            });
        
        return new AccidentListResp(accident.getId(),
                accident.getPlace(),
                accident.getAccidentSeverity().getValue(),
                accident.getCrashDate(),
                accident.getPersonAccidentList().size(),
                0,
                accident.getCity().getName(),
                (user.getPerson().getLastName()==null?"":user.getPerson().getLastName())
                        + " " +(user.getPerson().getFirstName()==null?"":user.getPerson().getFirstName()),
                accident.getStatus(),
                "",
                "",
                accident.getCode()==null? "":accident.getCode().getCode(),
                0L,
                0,
                0,
                accident.getCrashTime(), 0l,
                accident.getDrawing(),
                accident.getOrganization().getType()
        //         crashImageList,
        //          new AccidentReportReq(
        //         accidentReport.getId(),
        //         accident.getId(),
        //         accidentReport.getAuthors(),
        //         accidentReport.getAssist(),
        //         accidentReport.getSaw(),
        //         accidentReport.getCirculation(),
        //         accidentReport.getPatrol()
        // )               
               
        );
    }

    public Long createReport(AccidentReportReq accidentReportReq) throws IOException {
        Accident accident = accidentDao.getById(accidentReportReq.getIdaccident());
        if(accident.getAccidentReport() !=null) {
            accident.getAccidentReport().setAccident(null);
            accidentDao.save(accident);
        }

        AccidentReport accidentReport = new AccidentReport(
                accidentReportReq.getNous(),
                accidentReportReq.getAssiste(),
                accidentReportReq.getConstate(),
                accidentReportReq.getCirculation(),
                accidentReportReq.getPatrouille(),
                false,
                accident
        );
        accidentReport.setId(accidentReport.getId());
        accident.setAccidentReport(accidentReport);
        accident = accidentDao.save(accident);
        return accident.getAccidentReport().getId();
    }

    public AccidentReportReq getReport(Long id) throws IOException {
        Accident accident = accidentDao.getById(id);
        AccidentReport accidentReport = accident.getAccidentReport();
        return new AccidentReportReq(
                accidentReport.getId(),
                accident.getId(),
                accidentReport.getAuthors(),
                accidentReport.getAssist(),
                accidentReport.getSaw(),
                accidentReport.getCirculation(),
                accidentReport.getPatrol()
        );
    }

    public AccidentReportReq printReport(Long id) throws IOException {
        Accident accident = accidentDao.getById(id);
        accident.getAccidentReport().setPrinted(true);
        accidentDao.save(accident);
        AccidentReport accidentReport = accident.getAccidentReport();
        return new AccidentReportReq(
                accidentReport.getId(),
                accident.getId(),
                accidentReport.getAuthors(),
                accidentReport.getAssist(),
                accidentReport.getSaw(),
                accidentReport.getCirculation(),
                accidentReport.getPatrol()
        );
    }


    public AccidentReqResp getOneAccidentNew(Long id) throws IOException {
        Optional<Accident> optAccident = accidentDao.findById(id);
        Accident accident = optAccident.orElseThrow(() -> new RuntimeException("Please verify accident id"));
        List<Map<String, String>> crashImageList = new ArrayList<>();
        accident.getAccidentImages().stream().forEach(crashImage ->
                {
                    Map<String, String> map = new HashMap<>();
                    map.put("path",environment.getProperty("data.collect.address")
                            +":"+environment.getProperty("data.collect.port")+"/public/images/accident/crash/"
                            +  crashImage.getName());
                    map.put("name", crashImage.getName());
                    crashImageList.add(map);
                }
        );

        return new AccidentReqResp(
                accident.getId(),
                accident.getCrashDate(),
                accident.getCrashTime(),
                accident.getMunicipality().getId(),
                accident.getLatitude(),
                accident.getLongitude(),
                accident.getPlace(),
                accident.getRoad() == null? null : accident.getRoad() .getId(),
                accident.getRoadType().getId(),
                accident.getRoadCategory().getId(),
                accident.getSpeedLimit(),
                accident.getBlock().getId(),
                accident.getRoadState().getId(),
                accident.getRoadIntersection().getId(),
                accident.getTrafficControl().getId(),
                accident.getVirage().getId(),
                accident.getRoadSlopSection().getId(),
                accident.getAccidentType().getId(),
                accident.getImpactType().getId(),
                accident.getClimaticCondition().getId(),
                accident.getBrightnessCondition().getId(),
                accident.getAccidentSeverity().getId(),
                accident.getVehicleAccidents().stream()
                        .map(vehicleAccident ->
                        {

                                List<Map<String, String>> vehicleImageList = new ArrayList<>();
                                vehicleAccident.getVehicleImages().stream().forEach(vehicleImage ->{
                                    Map<String, String> map = new HashMap<>();
                                    map.put("path",environment.getProperty("data.collect.address")
                                            +":"+environment.getProperty("data.collect.port")+"/public/images/accident/vehicles/"
                                            + vehicleImage.getName());
                                    map.put("name", vehicleImage.getName());
                                    vehicleImageList.add(map);
                                });
                                return new VehicleReqResp(
                                        vehicleAccident.getId(),
                                        vehicleAccident.getVehicleAccidentNumber(),
                                        vehicleAccident.getPlateNumber(),
                                        vehicleAccident.getVehicle() ==null? null : vehicleAccident.getVehicle().getChassis(),
                                        vehicleAccident.getVehicle() ==null? null : vehicleAccident.getVehicle().getId(),
                                        vehicleAccident.getSpecialFunction().getId(),
                                        vehicleAccident.getVehicle() ==null? null : vehicleAccident.getVehicle().getBrand().getId(),
                                        vehicleAccident.getVehicle() ==null? null : vehicleAccident.getVehicle().getModel().getId(),
                                        vehicleAccident.getVehicle() ==null? null : vehicleAccident.getVehicle().getFabricationYear().intValue(),
                                        vehicleAccident.getVehicle() ==null? null : vehicleAccident.getVehicle().getCylinderCapacity().intValue(),
                                        vehicleAccident.getSpecialFunction().getId(),
                                        vehicleAccident.getAction().getId(),
                                        vehicleImageList
                                        );
                        }).collect(Collectors.toList()),
                accident.getPersonAccidentList().stream()
                        .map(personAccident -> {
                                List<Map<String, String>> personImageList = new ArrayList<>();
                                personAccident.getPersonImages().stream().forEach(personImage ->
                                        {
                                            Map<String, String> map = new HashMap<>();
                                            map.put("path",environment.getProperty("data.collect.address")
                                                    +":"+environment.getProperty("data.collect.port")+"/public/images/accident/persons/"
                                                    + personImage.getName());
                                            map.put("name", personImage.getName());
                                            personImageList.add(map);
                                        }
                                );
                                return new PersonReqResp(
                                        personAccident.getId(),
                                        personAccident.getPerson().getFirstName(),
                                        personAccident.getPerson().getLastName(),
                                        personAccident.getPerson().getIdentityNumber(),
                                        personAccident.getPerson().getPhone(),
                                        personAccident.getPersonNumber(),
                                        personAccident.getVehicleNumber(),
                                        personAccident.getVehicleLinkedPedestrian(),
                                        DateUtil.textFromDate("dd/MM/yyyy", personAccident.getPerson().getBirthDate()),
                                        personAccident.getPerson().getGender()==null? null : personAccident.getPerson().getGender().getId(),
                                        personAccident.getRoadType().getId(),
                                        personAccident.getRange().getId(),
                                        personAccident.getPlace().getId(),
                                        personAccident.getTraumaSeverity().getId(),
                                        personAccident.getWearingHelmet().getId(),
                                        personAccident.getOccupantRestraintSystem().getId(),
                                        personAccident.getAction().getId(),
                                        personAccident.getAlcoholConsumption().getId(),
                                        personAccident.getTestStatus().getId(),
                                        personAccident.getTestType().getId(),
                                        personAccident.getTestResult().getId(),
                                        personAccident.getDrugUse().getId(),
                                        personAccident.getDrivingLicenceYear(),
                                        personAccident.getCare()==null? 0 : personAccident.getCare().getId(),
                                        personAccident.getPerson().getId(),
                                        personImageList,
                                        personAccident.getProfession().getId()
                                );
                        })
                        .collect(Collectors.toList()),
                accident.getCity().getId(),
                accident.getStatus(),
                accident.getDirectCauses().stream().map(directCause -> new DirectCauseResp(directCause.getId(), directCause.getName())).collect(Collectors.toList()),
                crashImageList,
                new ImageVueFormat(accident.getDrawing(), environment.getProperty("data.collect.address")
                        +":"+environment.getProperty("data.collect.port")+"/public/images/accident/crash/"
                        +  accident.getDrawing() )
        );
    }
    public AccidentResp getDataForAccident(String lang) {
        AccidentResp accidentResp = new AccidentResp();
        accidentResp.setAccidentTypeResp(accidentTypeService.getAll(lang));
        accidentResp.setAccidentSeverityResp(severityService.getAll(lang));
        accidentResp.setActionResp(actionService.getAll(lang));
        accidentResp.setBlockResp(blockService.getAll(lang));
        accidentResp.setBrightnessConditionResp(brightnessConditionService.getAll(lang));
        accidentResp.setBrandResp(brandService.getAll(lang));
        accidentResp.setControlResp(trafficControlService.getAll(lang));
        accidentResp.setClimaticConditionResp(climaticConditionService.getAll(lang));
        accidentResp.setGenderResp(genderService.getAll(lang));
        accidentResp.setImpactTypeResp(impactTypeService.getAll(lang));
        accidentResp.setMunicipalityResp(municipalityService.getAll());
        accidentResp.setOccupantRestraintSystemResp(restraintSystemService.getAll(lang));
        accidentResp.setPersonRoadTypeResp(personRoadTypeService.getAll(lang));
        accidentResp.setRoadResp(roadService.getAll(lang));
        accidentResp.setRoadCategoryResp(categoryService.getAll(lang));
        accidentResp.setRoadIntersectionResp(intersectionService.getAll(lang));
        accidentResp.setRoadStateResp(stateService.getAll(lang));
        accidentResp.setRoadTypeResp(roadTypeService.getAll(lang));
        accidentResp.setRoadSlopSectionResp(slopSectionService.getAll(lang));
        accidentResp.setSpecialFunctionResp(functionService.getAll(lang));
        accidentResp.setTraumaSeverityResp(traumaSeverityService.getAll(lang));
        accidentResp.setVehicleModelResp(vehicleModelService.getAll(lang));
        accidentResp.setWearingHelmetResp(helmetService.getAll(lang));
        accidentResp.setVirageResp(virageService.getAll(lang));
        accidentResp.setVehicleTypeResp(vehicleTypeService.getAll(lang));
        accidentResp.setAlcoholTestTypeResp(alcoholTestTypeService.getAll(lang));
        accidentResp.setAlcoholTestStatusResp(alcoholTestStatusService.getAll(lang));
        accidentResp.setAlcoholConsumptionResp(alcoholConsumptionService.getAll(lang));
        accidentResp.setSeatingRangeResp(seatingRangeService.getAll(lang));
        accidentResp.setSeatingPlaceResp(seatingPlaceService.getAll(lang));
        accidentResp.setPersonDrugUseResp(personDrugUseService.getAll(lang));
        accidentResp.setAlcoholTestResultResp(alcoholTestResultService.getAll(lang));
        accidentResp.setVehicleActionResp(vehicleActionService.getAll(lang));
        accidentResp.setCityResp(cityService.getAll());
        accidentResp.setDirectCauseResp(directCauseService.getAll(lang));
        accidentResp.setProfessionResp(professionService.getAll(lang));
        accidentResp.setDocumentTypeResp(documentTypeService.getAll(lang));

        return accidentResp;
    }
public List<Accidentstatresp> accidentstatistics(String userId, String year,List<String> month){
    List<Accidentstatresp> stat=new ArrayList();
    Optional<User> user = userDao.findByKeycloakId(userId);
    if(user.isPresent()){
        
       
        for (String months:month){
           Integer g= accidentDao.getAccidentBymonthYear(months+"/"+year)==null?0 : accidentDao.getAccidentBymonthYear(months+"/"+year).size();
           stat.add(new Accidentstatresp(months,g));
        }
        
    }
    return stat;

}
public AccidentstatResp1 accidentstatistics1(String userId, String year, String month){
    Optional<User> user = userDao.findByKeycloakId(userId);
    AccidentstatResp1 statresp1=new AccidentstatResp1();
    if(user.isPresent()){
        
        statresp1.setTotalnumberaccident(accidentDao.getAccidentBymonthYear(month+"/"+year).size());
        List<AccidentSeverity> severities=accidentSeverityDao.findAll();
        statresp1.setStats(new HashMap());
        Map<String, Integer> stat=statresp1.getStats();  
        for (AccidentSeverity severity:severities){

           Integer g= accidentDao.getAccidentBymonthYearwithseverity(month+"/"+year,severity.getId().intValue())==null?0 : accidentDao.getAccidentBymonthYearwithseverity(month+"/"+year,severity.getId().intValue()).size();
           stat.put(severity.getValue(),g);
        }
        
    }
    return statresp1;

}
    public Page<AccidentResp1> getAccidentList(String userId, int page, int size, String search){
        Pageable pageable = PageRequest.of(page, size);
        Optional<User> user = userDao.findByKeycloakId(userId);
        List<AccidentResp1> accidentListResp = new ArrayList<>();
        
        if(user.isPresent()){
            List<Long> ids = user.get().getOrganization().getMyIdAndAllChildrenId(user.get().getOrganization());
                Page<Accident> accidents = accidentDao
                        .getAccidentByOrganization(
                                ids,
                                search,
                                pageable
                        );
                accidents.forEach(accident ->{
                    
                    List<Map<String, String>> crashImageList = new ArrayList<>();
                    accident.getAccidentImages().stream().forEach(crashImage ->
                            {
                                Map<String, String> map = new HashMap<>();
                                map.put("path",environment.getProperty("data.collect.address")
                                        +":"+environment.getProperty("data.collect.port")+"/public/images/accident/crash/"
                                        +  crashImage.getName());
                                map.put("name", crashImage.getName());
                                crashImageList.add(map);
                            }
                    );
                    List<AccidentCommentReq> comments=new ArrayList<AccidentCommentReq>();
                      for(AccidentComment comment: accident.getComment()){
                            AccidentCommentReq c=new AccidentCommentReq();
                             c.setIdcomment(comment.getId());
                                 c.setComment(comment.getContent());
                                     comments.add(c);
                             }
                    accidentListResp.add(
                       new AccidentResp1(
                            accident.getId(),
                            accident.getCrashDate(),
                            accident.getCrashTime(),
                            accident.getMunicipality().getName(),
                            accident.getLatitude(),
                            accident.getLongitude(),
                            accident.getPlace(),
                            accident.getRoad() == null? null : accident.getRoad().getName(),
                            accident.getRoadType().getValue(),
                            accident.getRoadCategory().getName(),
                            accident.getSpeedLimit(),
                            accident.getBlock().getId(),
                            accident.getRoadState().getId(),
                            accident.getRoadIntersection().getId(),
                            accident.getTrafficControl().getId(),
                            accident.getVirage().getId(),
                            accident.getRoadSlopSection().getId(),
                            accident.getAccidentType().getId(),
                            accident.getImpactType().getId(),
                            accident.getClimaticCondition().getId(),
                            accident.getBrightnessCondition().getId(),
                            accident.getAccidentSeverity().getValue(),
                            accident.getVehicleAccidents().stream()
                                    .map(vehicleAccident ->
                                    {
            
                                            List<Map<String, String>> vehicleImageList = new ArrayList<>();
                                            vehicleAccident.getVehicleImages().stream().forEach(vehicleImage ->{
                                                Map<String, String> map = new HashMap<>();
                                                map.put("path",environment.getProperty("data.collect.address")
                                                        +":"+environment.getProperty("data.collect.port")+"/public/images/accident/vehicles/"
                                                        + vehicleImage.getName());
                                                map.put("name", vehicleImage.getName());
                                                vehicleImageList.add(map);
                                            });
                                            return new VehicleReqResp(
                                                    vehicleAccident.getId(),
                                                    vehicleAccident.getVehicleAccidentNumber(),
                                                    vehicleAccident.getPlateNumber(),
                                                    vehicleAccident.getVehicle() ==null? null : vehicleAccident.getVehicle().getChassis(),
                                                    vehicleAccident.getVehicle() ==null? null : vehicleAccident.getVehicle().getId(),
                                                    vehicleAccident.getSpecialFunction().getId(),
                                                    vehicleAccident.getVehicle() ==null? null : vehicleAccident.getVehicle().getBrand().getId(),
                                                    vehicleAccident.getVehicle() ==null? null : vehicleAccident.getVehicle().getModel().getId(),
                                                    vehicleAccident.getVehicle() ==null? null : vehicleAccident.getVehicle().getFabricationYear().intValue(),
                                                    vehicleAccident.getVehicle() ==null? null : vehicleAccident.getVehicle().getCylinderCapacity().intValue(),
                                                    vehicleAccident.getSpecialFunction().getId(),
                                                    vehicleAccident.getAction().getId(),
                                                    vehicleImageList
                                                    );
                                    }).collect(Collectors.toList()),
                            accident.getPersonAccidentList().stream()
                                    .map(personAccident -> {
                                            List<Map<String, String>> personImageList = new ArrayList<>();
                                            personAccident.getPersonImages().stream().forEach(personImage ->
                                                    {
                                                        Map<String, String> map = new HashMap<>();
                                                        map.put("path",environment.getProperty("data.collect.address")
                                                                +":"+environment.getProperty("data.collect.port")+"/public/images/accident/persons/"
                                                                + personImage.getName());
                                                        map.put("name", personImage.getName());
                                                        personImageList.add(map);
                                                    }
                                            );
                                            return new PersonReqResp(
                                                    personAccident.getId(),
                                                    personAccident.getPerson().getFirstName(),
                                                    personAccident.getPerson().getLastName(),
                                                    personAccident.getPerson().getIdentityNumber(),
                                                    personAccident.getPerson().getPhone(),
                                                    personAccident.getPersonNumber(),
                                                    personAccident.getVehicleNumber(),
                                                    personAccident.getVehicleLinkedPedestrian(),
                                                    DateUtil.textFromDate("dd/MM/yyyy", personAccident.getPerson().getBirthDate()),
                                                    personAccident.getPerson().getGender()==null? null : personAccident.getPerson().getGender().getId(),
                                                    personAccident.getRoadType().getId(),
                                                    personAccident.getRange().getId(),
                                                    personAccident.getPlace().getId(),
                                                    personAccident.getTraumaSeverity().getId(),
                                                    personAccident.getWearingHelmet().getId(),
                                                    personAccident.getOccupantRestraintSystem().getId(),
                                                    personAccident.getAction().getId(),
                                                    personAccident.getAlcoholConsumption().getId(),
                                                    personAccident.getTestStatus().getId(),
                                                    personAccident.getTestType().getId(),
                                                    personAccident.getTestResult().getId(),
                                                    personAccident.getDrugUse().getId(),
                                                    personAccident.getDrivingLicenceYear(),
                                                    personAccident.getCare()==null? 0 : personAccident.getCare().getId(),
                                                    personAccident.getPerson().getId(),
                                                    personImageList,
                                                    personAccident.getProfession().getId()
                                            );
                                    })
                                    .collect(Collectors.toList()),
                            accident.getCity().getName(),
                            accident.getStatus(),
                            accident.getDirectCauses().stream().map(directCause -> new DirectCauseResp(directCause.getId(), directCause.getName())).collect(Collectors.toList()),
                            crashImageList,
                            new ImageVueFormat(accident.getDrawing(), environment.getProperty("data.collect.address")
                                    +":"+environment.getProperty("data.collect.port")+"/public/images/accident/drawing/"
                                    +  accident.getDrawing() ),
                            accident.getAccidentReport()==null?null: new AccidentReportResp().fillaccidentReportResp(accident),
                            comments
                    )
                    );
                });
                return new PageImpl<AccidentResp1>(accidentListResp, pageable, accidents.getTotalElements());
            }
        else
            throw new RuntimeException("Please register in database");
    }

    // public Map<String, Object> getInsuranceAccidentList(String name, int page, int size){
    //     Pageable pageable = PageRequest.of(page, size);
    //     List<AccidentListResp> accidentListResp = new ArrayList<>();
    //             Page<Accident> accidents = accidentDao
    //                     .getInsurranceAccident(name, pageable);
    //             for(Accident accident : accidents){

    //                 AccidentReport accidentReport = accident.getAccidentReport();
                          


    //                  List<Map<String, String>> crashImageList = new ArrayList<>();
    //                    accident.getAccidentImages().stream().forEach(crashImage ->
    //                         {
    //                             Map<String, String> map = new HashMap<>();
    //                             map.put("path",environment.getProperty("data.collect.address")
    //                                     +":"+environment.getProperty("data.collect.port")+"/public/images/accident/crash/"
    //                                     +  crashImage.getName());
    //                             map.put("name", crashImage.getName());
    //                             crashImageList.add(map);
    //                         });
    //                 accidentListResp.addAll(accident.getVehicleAccidents()
    //                         .stream().filter(vehicleAccident -> vehicleAccident.getInsuranceName().equals(name) && vehicleAccident.isInsuranceValid())
    //                         .map(vehicleAccident -> new AccidentListResp( vehicleAccident.getId(),
    //                                 vehicleAccident.getAccident().toString(),
    //                                 vehicleAccident.getAccident().getAccidentSeverity().getValue(),
    //                                 vehicleAccident.getAccident().getCrashDate() +" "+vehicleAccident.getAccident().getCrashTime(),
    //                                 vehicleAccident.getAccident().getPersonAccidentList().size(),
    //                                 vehicleAccident.getAccident().getPersonAccidentList().stream().filter(personAccident -> personAccident.getCare() !=null).collect(Collectors.toList()).size(),
    //                                 vehicleAccident.getAccident().getCity().getName(),
    //                                 "",
    //                                 vehicleAccident.getAccident().getStatus(),
    //                                 vehicleAccident.getPlateNumber(),
    //                                 "",
    //                                 accident.getCode()==null? "":accident.getCode().getCode(),
    //                                 vehicleAccident.getAccident().getId(),
    //                                 vehicleAccident.getAccident().getPersonAccidentList().stream()
    //                                         .filter(personAccident -> personAccident.getCare() !=null)
    //                                         .mapToDouble(p -> p.getCare().getTreatmentsCost()).sum() +
    //                                         vehicleAccident.getAccident().getPersonAccidentList().stream()
    //                                                 .filter(personAccident -> personAccident.getCare() !=null)
    //                                                 .mapToDouble(p -> p.getCare().getExaminationsCost()).sum(),
    //                                 vehicleAccident.getAccident().getPersonAccidentList().stream()
    //                                         .filter(personAccident -> personAccident.getCare() !=null)
    //                                         .mapToDouble(p -> p.getCare().getAmountAccepted()).sum(),
    //                                 accident.getCrashTime(),
    //                                 vehicleAccident.getVehicleAccidentNumber(),
    //                                 accident.getDrawing(),
    //                                 accident.getOrganization().getType(),
    //                                 crashImageList,
    //                                   new AccidentReportReq(
    //                         accidentReport.getId(),
    //                         accident.getId(),
    //                         accidentReport.getAuthors(),
    //                         accidentReport.getAssist(),
    //                         accidentReport.getSaw(),
    //                         accidentReport.getCirculation(),
    //                         accidentReport.getPatrol()
    //                 )      

    //                                 )).collect(Collectors.toList())) ;
    //             }
    //     List<Pojo> pojos =accidentListResp.stream().map(accident -> new Pojo(accident.getId(), accident.getCode(), accident.getSeverity()))
    //             .collect(Collectors.toList());
    //     Map<String, List<Pojo>> careGrouped = pojos.stream().collect(Collectors.groupingBy(Pojo::getLegend, Collectors.toList()));
    //     Map<String, Long> graphData = pojos.stream().collect(Collectors.groupingBy(Pojo::getLegend, Collectors.counting()));
    //     List<GraphDataResp> graphDataResps = new ArrayList<>();
    //     graphData.forEach((s, aLong) -> graphDataResps.add(new GraphDataResp(s, aLong)) );
    //     Map<String, Object> resp = new HashMap<>();
    //     List<KanbanCareSimpleResp> kanban = new ArrayList<>();
    //     careGrouped.forEach((s, strings) -> kanban.add(new KanbanCareSimpleResp(strings, s, strings.size())));
    //     resp.put("list",  new PageImpl<AccidentListResp>(accidentListResp, pageable, accidents.getTotalElements()));
    //     resp.put("kanban", kanban);
    //     resp.put("graph", graphDataResps);

    //     return resp;
    // }

    public Map<String, Object> getInsuranceAccidentList(String name, int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        List<AccidentListResp1> accidentListResp = new ArrayList<>();
                Page<Accident> accidents = accidentDao
                        .getInsurranceAccident(name, pageable);
                for(Accident accident : accidents){
                      
                   
  List<Map<String, String>> crashImageList = new ArrayList<>();
                       accident.getAccidentImages().stream().forEach(crashImage ->
                            {
                                Map<String, String> map = new HashMap<>();
                                map.put("path",environment.getProperty("data.collect.address")
                                        +":"+environment.getProperty("data.collect.port")+"/public/images/accident/crash/"
                                        +  crashImage.getName());
                                map.put("name", crashImage.getName());
                                crashImageList.add(map);
                            });
                     accidentListResp.add(
                            new AccidentListResp1(accident.getId(),
                                    accident.toString(),
                                    accident.getAccidentSeverity().getValue(),
                                    accident.getCrashDate(),
                                    accident.getPersonAccidentList().size(),
                                    0,
                                    accident.getCity().getName(),
                                    (accident.getPoliceAgent().getPerson().getLastName()==null?"":accident.getPoliceAgent().getPerson().getLastName())
                                            + " " +(accident.getPoliceAgent().getPerson().getFirstName()==null?"":accident.getPoliceAgent().getPerson().getFirstName()),
                                    accident.getStatus(),
                                    "",
                                    "",
                                    accident.getCode()==null? "":accident.getCode().getCode(),
                                    0L,
                                    0,
                                    0,
                                    accident.getCrashTime(), 0l,
                                    accident.getDrawing(),
                                    accident.getOrganization().getType(),
                                    crashImageList,
                                    accident.getAccidentReport()==null?null:new AccidentReportResp().fillaccidentReportResp(accident)

                            )
                    );
                  
                    accidentListResp.addAll(accident.getVehicleAccidents()
                            .stream().filter(vehicleAccident -> vehicleAccident.getInsuranceName().equals(name) && vehicleAccident.isInsuranceValid())
                            .map(vehicleAccident -> new AccidentListResp1( vehicleAccident.getId(),
                                    vehicleAccident.getAccident().toString(),
                                    vehicleAccident.getAccident().getAccidentSeverity().getValue(),
                                    vehicleAccident.getAccident().getCrashDate() +" "+vehicleAccident.getAccident().getCrashTime(),
                                    vehicleAccident.getAccident().getPersonAccidentList().size(),
                                    vehicleAccident.getAccident().getPersonAccidentList().stream().filter(personAccident -> personAccident.getCare() !=null).collect(Collectors.toList()).size(),
                                    vehicleAccident.getAccident().getCity().getName(),
                                    "",
                                    vehicleAccident.getAccident().getStatus(),
                                    vehicleAccident.getPlateNumber(),
                                    "",
                                    accident.getCode()==null? "":accident.getCode().getCode(),
                                    vehicleAccident.getAccident().getId(),
                                    vehicleAccident.getAccident().getPersonAccidentList().stream()
                                            .filter(personAccident -> personAccident.getCare() !=null)
                                            .mapToDouble(p -> p.getCare().getTreatmentsCost()).sum() +
                                            vehicleAccident.getAccident().getPersonAccidentList().stream()
                                                    .filter(personAccident -> personAccident.getCare() !=null)
                                                    .mapToDouble(p -> p.getCare().getExaminationsCost()).sum(),
                                    vehicleAccident.getAccident().getPersonAccidentList().stream()
                                            .filter(personAccident -> personAccident.getCare() !=null)
                                            .mapToDouble(p -> p.getCare().getAmountAccepted()).sum(),
                                    accident.getCrashTime(),
                                    vehicleAccident.getVehicleAccidentNumber(),
                                    accident.getDrawing(),
                                    accident.getOrganization().getType(),
                                    crashImageList,
                                    accident.getAccidentReport()==null?null:new AccidentReportResp().fillaccidentReportResp(accident)
                                //     

                                    )).collect(Collectors.toList())) ;
                }
        List<Pojo> pojos =accidentListResp.stream().map(accident -> new Pojo(accident.getId(), accident.getCode(), accident.getSeverity()))
                .collect(Collectors.toList());
        Map<String, List<Pojo>> careGrouped = pojos.stream().collect(Collectors.groupingBy(Pojo::getLegend, Collectors.toList()));
        Map<String, Long> graphData = pojos.stream().collect(Collectors.groupingBy(Pojo::getLegend, Collectors.counting()));
        List<GraphDataResp> graphDataResps = new ArrayList<>();
        graphData.forEach((s, aLong) -> graphDataResps.add(new GraphDataResp(s, aLong)) );
        Map<String, Object> resp = new HashMap<>();
        List<KanbanCareSimpleResp> kanban = new ArrayList<>();
        careGrouped.forEach((s, strings) -> kanban.add(new KanbanCareSimpleResp(strings, s, strings.size())));
        resp.put("list",  new PageImpl<AccidentListResp1>(accidentListResp, pageable, accidents.getTotalElements()));
        resp.put("kanban", kanban);
        resp.put("graph", graphDataResps);

        return resp;
    }


    public String getInsuranceName(PersonAccident personAccident){
        if(personAccident == null)
            return "UNLINKED";

        Optional<VehicleAccident> opt = personAccident.getAccident()
                .getVehicleAccidents()
                .stream()
                .filter(vehicleAccident -> vehicleAccident.getVehicleAccidentNumber().intValue() == personAccident.getVehicleNumber())
                .findFirst();

        if(opt.isPresent())
            return opt.get().getInsuranceName();

        return "";
    }


     public Page<Notification> getAllnotifications(String userId,int page,int size,String search){
        Pageable pageable = PageRequest.of(page, size);
        Optional<User> user = userDao.findByKeycloakId(userId);
       List<Notification> notificationResp = notificationAccidentDao.findAll();
       Page<Notification> notificationresp;
       if(user.isPresent()){
          notificationresp=notificationAccidentDao.getNotificationByOrganization(search,pageable);
          
       }
         return new PageImpl<Notification>(notificationResp, pageable, 10);
    }

     public Optional<Notification> getNotification(String userId, Long Id){
          Optional<User> user = userDao.findByKeycloakId(userId);
        //   Long Id=new Long(Ids);
       Optional<Notification> result=null;
          if(user.isPresent()){
              result=notificationAccidentDao.findById(Id);  
             }
                 return result;
          }
      public Long SaveNotification(NotificationaccidentResp notificatio){
        Notification notification=new Notification();
        notification.setDate(notificatio.getDate());
        notification.setHours(notificatio.getHours());
        notification.setCity(notificatio.getCity());
        notification.setImageString(notificatio.getImageString());
        notification.setDescription(notificatio.getDescription());
       
        
        Long Id=notificationAccidentDao.save(notification).getId();
        return Id;
      }


}
