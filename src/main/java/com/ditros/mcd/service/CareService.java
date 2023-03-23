package com.ditros.mcd.service;

import com.ditros.mcd.dao.*;
import com.ditros.mcd.model.dto.*;
import com.ditros.mcd.model.entity.*;
import com.ditros.mcd.model.mappers.CareMapper;
import com.ditros.mcd.model.mappers.ExaminationMapper;
import com.ditros.mcd.model.mappers.InjuryMapper;
import com.ditros.mcd.model.mappers.TreatmentMapper;
import com.ditros.mcd.enumeration.CareStatus;
import com.ditros.mcd.util.CsvHelper;
import com.ditros.mcd.util.DateUtil;
import com.ditros.mcd.enumeration.InsuranceVisa;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class CareService {
    private CareDao careDao;
    private AccidentDao accidentDao;
    private InjuryDao injuryDao;
    private CareInjuryDao careInjuryDao;
    private ExaminationDao examinationDao;
    private CareExaminationDao careExaminationDao;
    private CareTreatmentDao careTreatmentDao;
    private UserDao userDao;
    private TreatmentDao treatmentDao;
    private PersonTraumaSeverityDao personTraumaSeverityDao;
    private PersonGenderDao personGenderDao;
    private CareMapper careMapper;
    private TraumaSeverityService traumaSeverityService;
    private GenderService genderService;
    private ExaminationMapper examinationMapper;
    private TreatmentMapper treatmentMapper;
    private InjuryMapper injuryMapper;



    public CareResp declareCare(CareReq careReq, String subject) throws Throwable{
        Optional<User> hospitalEmployee = userDao.findByKeycloakId(subject);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateNaiss = LocalDate.parse(careReq.getDateNaiss(), formatter);

        Care entity = new Care(
            DateUtil.DateFromText("yyyy-MM-dd HH:mm",careReq.getCrashDate()),
            careReq.getPrenom(),
            careReq.getNom(),
            null,
            dateNaiss.atStartOfDay(),
            hospitalEmployee.orElseThrow(() -> {
                throw new RuntimeException("Please verify that your account is registered in database");
            }),
            null,
            personTraumaSeverityDao.getById(Long.valueOf(careReq.getAccparams().getPersontrauma())),
            personGenderDao.getById(Long.valueOf(careReq.getGender())),
            CareStatus.OPENED,
                null,
                null,
                null,
            careReq.getCni(),
            careReq.getPermis(),
            careReq.getPassport(),
            careReq.getTelephone(),
            careReq.getAccparams().getConsumalcohol().equals("oui")? true : false,
            careReq.getAccparams().getConsumdrugs().equals("oui")? true : false,
            careReq.getParametre().getPoids(),
            careReq.getParametre().getTemperature(),
            careReq.getParametre().getPouls(),
            careReq.getParametre().getTension(),
            String.join(",",
                    careReq.getParametre().getParams()==null?
                            new ArrayList<>():careReq.getParametre().getParams()),
            careReq.getDescription(),
            null,
            null,
            careReq.getPlate()
        );
        entity.setId(careReq.getId());

        entity.setCareContact(
                careReq.getContacts().stream().map(contactReq -> {
                    CareContact c = new CareContact(entity,
                            new Person(contactReq.getFirstName(),
                                    contactReq.getLastName(),
                                    contactReq.getCni(),
                                    contactReq.getTelephone(),
                                    null,
                                    null,
                                    null,
                                    null,
                                    null,
                                    null,
                                    null,
                                    null,
                                    0.0,
                                    0.0,
                                    personGenderDao.getById(contactReq.getGender()),
                                null,
                        null,
                        null),
                            contactReq.getFiliation());
                    c.setId(contactReq.getId());
                    return c;
                }).collect(Collectors.toList())
        );
        entity.setCode(new CareCode(null, entity));
        entity.setOrganization(hospitalEmployee.get().getOrganization());
        Care care = careDao.save(entity);
        checkInsuranceVisa(care);
        return careMapper.fromCare(care);
    }

    @Transactional
    public boolean importCareFile(MultipartFile file, String subject){
        try {
            List<CareReq> careReqs = CsvHelper.csvToCare(file.getInputStream());
            if(careReqs!=null){
                careReqs.forEach(careReq -> {
                    try {
                        log.info("saving "+careReq.getNom()+"...");
                        declareCare(careReq, subject);

                    } catch (Throwable e) {
                        e.printStackTrace();
                        throw new RuntimeException("fail to store csv data: " + e.getMessage());
                    }

                });
            }
            else
                throw new RuntimeException("fail to store csv data: empty file");

        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());

        }
        log.info("file imported successfully");
        return true;
    }
    public void disableCare(Long id){
        careDao.getById(id).disable();
    }

    public Page<CareResp> getCareList(String userId, String search, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Optional<User> hospitalEmployee = userDao.findByKeycloakId(userId);
        if(hospitalEmployee.isPresent()){
            List<Long> ids = hospitalEmployee.orElseThrow(() -> new RuntimeException("Please register in database!"))
                    .getOrganization().getMyIdAndAllChildrenId(hospitalEmployee.get().getOrganization());
            Page<Care> cares =careDao
                .getCareByOrganization(
                        ids,
                        search,
                    pageRequest);
            List<CareResp> models = new ArrayList<>();
            cares.forEach(care ->
                    models.add(
                            careMapper.fromCare(care)
                    )
            );
            return new PageImpl<CareResp>(models, pageRequest, cares.getTotalElements());
        }
        else
            throw new RuntimeException("Please register in database");
    }

    public CareResp getOne(Long id){
        Optional<Care> care = careDao.findById(id);
        return careMapper.fromCare(
                care.orElseThrow(() -> new RuntimeException("Please verify care id!"))
        );

    }

    public List<KanbanCareSimpleResp> getkanbanForOpenCare(String id){
        List<KanbanCareSimpleResp> list = new ArrayList<>();
        Optional<User> optUser = userDao.findByKeycloakId(id);
        User user = optUser.orElseThrow(() -> new RuntimeException("Register in database!"));
        /*personTraumaSeverityDao.findAll()
                .stream().forEach(personTraumaSeverity ->{
                    list.add(new KanbanCareSimpleResp(careDao.findOpenedByTrauma(personTraumaSeverity.getId(), user.getHospitalEmployee().getHospital().getId()).stream()
                            .map(
                             care -> new String(care.getFirstname()+" " +care.getLastname())
                            ).collect(Collectors.toList()),
                                    personTraumaSeverity.getValue(),
                                    careDao.findOpenedByTrauma(personTraumaSeverity.getId(), user.getHospitalEmployee().getHospital().getId()).size()));
                });
        return list;*/
        return null;
    }

    public Page<CareResp> getAllCareList(int page, int size){
        PageRequest pageRequest = PageRequest.of(page, size);
        List<Care> cares =careDao
                .findByActiveStatusTrueOrderByCreatedDateDesc(pageRequest);
        List<CareResp> models = new ArrayList<>();
        cares.forEach(care ->
                models.add(
                        careMapper.fromCare(care)
                )
        );
        return new PageImpl<CareResp>(models, pageRequest, models.size());
    }

    public Page<CareResp> getOpenedByInsurance(String userId, int page, int size, String insuranceName){
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<CareResp> careResps = getCareList(userId, "", 0, Integer.MAX_VALUE);
        List<CareResp> filteredCareResps = careResps.stream()
                .filter(careResp -> careResp.getInsurance().equals(insuranceName))
                .collect(Collectors.toList());

        return new PageImpl<CareResp>(filteredCareResps, pageRequest, filteredCareResps.size());
    }

    public List<CareResp> findOpenedCare(String keyword){
        List<CareResp> models = new ArrayList<>();
        if(keyword.equals(""))
            return models;
        List<Care> cares =careDao
                .findOpenedCareByName(keyword);

        cares.forEach(care ->
                models.add(
                        careMapper.fromCare(care)
                )
        );
        return models;
    }

    public Map<String, Object> findCareAccident(Long id, int vehiculeNumber, int page, int size){
        List<CareResp> models = new ArrayList<>();
        PageRequest pageRequest = PageRequest.of(page, size);
        Optional<Accident> optAccident = accidentDao.getAccidentWithValid(id, vehiculeNumber);
        Accident accident = optAccident.orElseThrow(() -> new RuntimeException("No Patient in hospital for this accident!"));

        accident.getPersonAccidentList().stream()
                .filter(personAccident -> personAccident.getCare() != null)
                .forEach(personAccident ->
                models.add(
                        careMapper.fromCare(personAccident.getCare())
                )
        );
        if(models.size() == 0) new RuntimeException("No Patient in hospital for this accident!");

        List<Pojo> pojos =models.stream().map(careResp -> new Pojo(careResp.getId(), careResp.getNom(), careResp.getTrauma()))
                .collect(Collectors.toList());
        Map<String, List<Pojo>> careGrouped = pojos.stream().collect(Collectors.groupingBy(Pojo::getLegend, Collectors.toList()));
        Map<String, Long> graphData = pojos.stream().collect(Collectors.groupingBy(Pojo::getLegend, Collectors.counting()));

        List<GraphDataResp> graphDataResps = new ArrayList<>();
                graphData.forEach((s, aLong) -> graphDataResps.add(new GraphDataResp(s, aLong)) );
        Map<String, Object> resp = new HashMap<>();
        List<KanbanCareSimpleResp> kanban = new ArrayList<>();
         careGrouped.forEach((s, strings) -> kanban.add(new KanbanCareSimpleResp(strings, s, strings.size())));
        resp.put("list", new PageImpl<CareResp>(models, pageRequest, models.size()));
        resp.put("kanban", kanban);
        resp.put("graph", graphDataResps);

        return resp;
    }

    public SaveCareFormResp getSaveFormData(String lang){
        return new SaveCareFormResp(
                traumaSeverityService.getAll(lang),
                genderService.getAll(lang)
        );
    }

    public CareFolderResp getFolder(Long id){
        Optional<Care> optCare = careDao.findById(id);
        Care care = optCare.orElseThrow(
                                () ->  new RuntimeException("Please verify patient id!"));
        List<ExaminationResp> examinationRespList = new ArrayList<>();
        List<TreatmentResp> treatmentRespList = new ArrayList<>();
        List<InjuryResp> injuryRespList = new ArrayList<>();
        if(care.getExaminations() != null)
            care.getExaminations().forEach(
                   e-> examinationRespList.add(examinationMapper.fromCareExamination(e))
            );
        if(care.getInjuries() != null)
            care.getInjuries().forEach(
                    i-> injuryRespList.add(injuryMapper.fromInjury(i))
            );
        if(care.getTreatments() != null)
            care.getTreatments().forEach(
                    t-> treatmentRespList.add(treatmentMapper.fromTreatment(t))
            );
        CareFolderResp careFolderResp = careMapper.fromCareFolder(care);
        careFolderResp.setExams(examinationRespList);
        careFolderResp.setTreatments(treatmentRespList);
        careFolderResp.setInjuries(injuryRespList);
        return careFolderResp;
    }

    public CareFolderResp closeFolder(Long id){
        Optional<Care> optCare = careDao.findById(id);
        Care care = optCare.orElseThrow(
                () ->  new RuntimeException("Please verify patient id!"));
        care.setStatus(CareStatus.CLOSED);
        care = careDao.save(care);
        List<ExaminationResp> examinationRespList = new ArrayList<>();
        List<TreatmentResp> treatmentRespList = new ArrayList<>();
        List<InjuryResp> injuryRespList = new ArrayList<>();
        if(care.getExaminations() != null)
            care.getExaminations().forEach(
                    e-> examinationRespList.add(examinationMapper.fromCareExamination(e))
            );
        if(care.getInjuries() != null)
            care.getInjuries().forEach(
                    i-> injuryRespList.add(injuryMapper.fromInjury(i))
            );
        if(care.getTreatments() != null)
            care.getTreatments().forEach(
                    t-> treatmentRespList.add(treatmentMapper.fromTreatment(t))
            );
        CareFolderResp careFolderResp = careMapper.fromCareFolder(care);
        careFolderResp.setExams(examinationRespList);
        careFolderResp.setTreatments(treatmentRespList);
        careFolderResp.setInjuries(injuryRespList);
        return careFolderResp;
    }

    public CareFolderResp addInjury(CareFolderItemReq careFolderItemReq){
        Optional<Care> optCare = careDao.findById(careFolderItemReq.getCare());
        Care care = optCare.orElseThrow(() -> new RuntimeException("Wrong id select a correct folder!"));
        Optional<Injury> optInjury = injuryDao.findById(careFolderItemReq.getItem());
        Injury injury = optInjury.orElseThrow(() -> new RuntimeException("Wrong id select a correct injury!"));

        if(care.getInjuries().stream().anyMatch(injury1 -> injury1.getId() == injury.getId()))
            throw new RuntimeException("Injury already recorded!");

        CareInjury careInjury = new CareInjury();
        careInjury.setCare(care);
        careInjury.setDate(DateUtil.DateFromText("dd/MM/yyyy HH:mm", careFolderItemReq.getDate()));
        careInjury.setInjury(injury);
        careInjury.setInsuranceVisa(InsuranceVisa.PENDING);
        care.getInjuries().add(careInjury);
        checkInsuranceVisa(care);
        care = careDao.save(care);

        List<ExaminationResp> examinationRespList = new ArrayList<>();
        List<TreatmentResp> treatmentRespList = new ArrayList<>();
        List<InjuryResp> injuryRespList = new ArrayList<>();
        if(care.getExaminations() != null)
            care.getExaminations().forEach(
                    e-> examinationRespList.add(examinationMapper.fromCareExamination(e))
            );
        if(care.getInjuries() != null)
            care.getInjuries().forEach(
                    i-> injuryRespList.add(injuryMapper.fromInjury(i))
            );
        if(care.getTreatments() != null)
            care.getTreatments().forEach(
                    t-> treatmentRespList.add(treatmentMapper.fromTreatment(t))
            );
        CareFolderResp careFolderResp = careMapper.fromCareFolder(care);
        careFolderResp.setExams(examinationRespList);
        careFolderResp.setTreatments(treatmentRespList);
        careFolderResp.setInjuries(injuryRespList);
        return careFolderResp;
    }

    public CareFolderResp removeInjury(CareFolderItemReq careFolderItemReq) {
        Optional<Care> optCare = careDao.findById(careFolderItemReq.getCare());
        Care care = optCare.orElseThrow(() -> new RuntimeException("Wrong id select a correct folder!"));
        Optional<CareInjury> optInjury = careInjuryDao.findById(careFolderItemReq.getItem());
        CareInjury injury = optInjury.orElseThrow(() -> new RuntimeException("Wrong id select a correct injury!"));

        careInjuryDao.delete(injury);

        List<ExaminationResp> examinationRespList = new ArrayList<>();
        List<TreatmentResp> treatmentRespList = new ArrayList<>();
        List<InjuryResp> injuryRespList = new ArrayList<>();
        if(care.getExaminations() != null)
            care.getExaminations().forEach(
                    e-> examinationRespList.add(examinationMapper.fromCareExamination(e))
            );
        if(care.getInjuries() != null)
            care.getInjuries().forEach(
                    i-> injuryRespList.add(injuryMapper.fromInjury(i))
            );
        if(care.getTreatments() != null)
            care.getTreatments().forEach(
                    t-> treatmentRespList.add(treatmentMapper.fromTreatment(t))
            );
        CareFolderResp careFolderResp = careMapper.fromCareFolder(care);
        careFolderResp.setExams(examinationRespList);
        careFolderResp.setTreatments(treatmentRespList);
        careFolderResp.setInjuries(injuryRespList);
        return careFolderResp;
    }

    public CareFolderResp addExam(CareFolderItemReq careFolderItemReq) {
        Optional<Care> optCare = careDao.findById(careFolderItemReq.getCare());
        Care care = optCare.orElseThrow(() -> new RuntimeException("Wrong id select a correct folder!"));
        Optional<Examination> optExamination = examinationDao.findById(careFolderItemReq.getItem());
        Examination examination = optExamination.orElseThrow(() -> new RuntimeException("Wrong id select a correct exam!"));

        CareExamination careExamination = new CareExamination();
        careExamination.setCare(care);
        careExamination.setExamination(examination);
        careExamination.setDate(DateUtil.DateFromText("dd/MM/yyyy HH:mm", careFolderItemReq.getDate()));
        careExamination.setInsuranceVisa(InsuranceVisa.PENDING);
        care.getExaminations().add(careExamination);
        care = careDao.save(care);

        List<ExaminationResp> examinationRespList = new ArrayList<>();
        List<TreatmentResp> treatmentRespList = new ArrayList<>();
        List<InjuryResp> injuryRespList = new ArrayList<>();
        if(care.getExaminations() != null)
            care.getExaminations().forEach(
                    e-> examinationRespList.add(examinationMapper.fromCareExamination(e))
            );
        if(care.getInjuries() != null)
            care.getInjuries().forEach(
                    i-> injuryRespList.add(injuryMapper.fromInjury(i))
            );
        if(care.getTreatments() != null)
            care.getTreatments().forEach(
                    t-> treatmentRespList.add(treatmentMapper.fromTreatment(t))
            );
        CareFolderResp careFolderResp = careMapper.fromCareFolder(care);
        careFolderResp.setExams(examinationRespList);
        careFolderResp.setTreatments(treatmentRespList);
        careFolderResp.setInjuries(injuryRespList);
        return careFolderResp;
    }

    public Care checkInsuranceVisa(Care care){
        if(care.getTreatments()==null && care.getExaminations() == null)
            return care;
        long treatments =care.getTreatments()==null? 0 : care.getTreatments().stream().filter(careTreatment -> careTreatment.getInsuranceVisa() == InsuranceVisa.PENDING)
                .count();
        long exams = care.getExaminations() == null? 0 : care.getExaminations().stream().filter(careExamination -> careExamination.getInsuranceVisa() == InsuranceVisa.PENDING)
                .count();
        if(treatments !=0 || exams !=0){
            care.setStatus(CareStatus.OPENED);
        }
        else
            care.setStatus(CareStatus.READY);

        return care;
    }

    public CareFolderResp removeExam(CareFolderItemReq careFolderItemReq) {
        Optional<Care> optCare = careDao.findById(careFolderItemReq.getCare());
        Care care = optCare.orElseThrow(() -> new RuntimeException("Wrong id select a correct folder!"));
        Optional<CareExamination> optExamination = careExaminationDao.findById(careFolderItemReq.getItem());
        CareExamination examination = optExamination.orElseThrow(() -> new RuntimeException("Wrong id select a correct exam!"));

        careExaminationDao.delete(examination);
        careDao.save(checkInsuranceVisa(care));

        List<ExaminationResp> examinationRespList = new ArrayList<>();
        List<TreatmentResp> treatmentRespList = new ArrayList<>();
        List<InjuryResp> injuryRespList = new ArrayList<>();
        if(care.getExaminations() != null)
            care.getExaminations().forEach(
                    e-> examinationRespList.add(examinationMapper.fromCareExamination(e))
            );
        if(care.getInjuries() != null)
            care.getInjuries().forEach(
                    i-> injuryRespList.add(injuryMapper.fromInjury(i))
            );
        if(care.getTreatments() != null)
            care.getTreatments().forEach(
                    t-> treatmentRespList.add(treatmentMapper.fromTreatment(t))
            );
        CareFolderResp careFolderResp = careMapper.fromCareFolder(care);
        careFolderResp.setExams(examinationRespList);
        careFolderResp.setTreatments(treatmentRespList);
        careFolderResp.setInjuries(injuryRespList);
        return careFolderResp;
    }

    public CareFolderResp addTreatment(CareFolderItemReq careFolderItemReq) {
        Optional<Care> optCare = careDao.findById(careFolderItemReq.getCare());
        Care care = optCare.orElseThrow(() -> new RuntimeException("Wrong id select a correct folder!"));
        Optional<Treatment> optTreatment = treatmentDao.findById(careFolderItemReq.getItem());
        Treatment treatment = optTreatment.orElseThrow(() -> new RuntimeException("Wrong id select a correct treatment!"));

        CareTreatment careTreatment = new CareTreatment();
        careTreatment.setCare(care);
        careTreatment.setTreatment(treatment);
        careTreatment.setDate(DateUtil.DateFromText("dd/MM/yyyy HH:mm", careFolderItemReq.getDate()));
        careTreatment.setInsuranceVisa(InsuranceVisa.PENDING);
        care.getTreatments().add(careTreatment);
        care = careDao.save(care);

        List<ExaminationResp> examinationRespList = new ArrayList<>();
        List<TreatmentResp> treatmentRespList = new ArrayList<>();
        List<InjuryResp> injuryRespList = new ArrayList<>();
        if(care.getExaminations() != null)
            care.getExaminations().forEach(
                    e-> examinationRespList.add(examinationMapper.fromCareExamination(e))
            );
        if(care.getInjuries() != null)
            care.getInjuries().forEach(
                    i-> injuryRespList.add(injuryMapper.fromInjury(i))
            );
        if(care.getTreatments() != null)
            care.getTreatments().forEach(
                    t-> treatmentRespList.add(treatmentMapper.fromTreatment(t))
            );
        CareFolderResp careFolderResp = careMapper.fromCareFolder(care);
        careFolderResp.setExams(examinationRespList);
        careFolderResp.setTreatments(treatmentRespList);
        careFolderResp.setInjuries(injuryRespList);
        return careFolderResp;
    }

    public CareFolderResp removeTreatment(CareFolderItemReq careFolderItemReq) {
        Optional<Care> optCare = careDao.findById(careFolderItemReq.getCare());
        Care care = optCare.orElseThrow(() -> new RuntimeException("Wrong id select a correct folder!"));
        Optional<CareTreatment> optExamination = careTreatmentDao.findById(careFolderItemReq.getItem());
        CareTreatment careTreatment = optExamination.orElseThrow(() -> new RuntimeException("Wrong id select a correct treatment!"));

        careTreatmentDao.delete(careTreatment);
        careDao.save(checkInsuranceVisa(care));
        List<ExaminationResp> examinationRespList = new ArrayList<>();
        List<TreatmentResp> treatmentRespList = new ArrayList<>();
        List<InjuryResp> injuryRespList = new ArrayList<>();
        if(care.getExaminations() != null)
            care.getExaminations().forEach(
                    e-> examinationRespList.add(examinationMapper.fromCareExamination(e))
            );
        if(care.getInjuries() != null)
            care.getInjuries().forEach(
                    i-> injuryRespList.add(injuryMapper.fromInjury(i))
            );
        if(care.getTreatments() != null)
            care.getTreatments().forEach(
                    t-> treatmentRespList.add(treatmentMapper.fromTreatment(t))
            );
        CareFolderResp careFolderResp = careMapper.fromCareFolder(care);
        careFolderResp.setExams(examinationRespList);
        careFolderResp.setTreatments(treatmentRespList);
        careFolderResp.setInjuries(injuryRespList);
        return careFolderResp;
    }
}
