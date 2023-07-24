package com.ditros.mcd.util;

import com.ditros.mcd.model.dto.AccParamReq;
import com.ditros.mcd.model.dto.CareParamReq;
import com.ditros.mcd.model.dto.CareReq;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class CsvHelper {
    public static String TYPE = "text/csv";

    static String[] HEADERs = { "cni", "nom", "prenom", "telephone", "dateNaiss",
            "passport", "permis", "genre", "crashDate", "description",
            "persontrauma", "consumalcohol", "consumdrugs", "poids", "temperature",
            "pouls", "tension"
    };

    public static boolean hasCSVFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public static List<CareReq> csvToCare(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));

             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.newFormat(';').withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<CareReq> cares = new ArrayList<CareReq>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                CareReq care = new CareReq(
                        null,
                        csvRecord.get("cni"),
                        csvRecord.get("nom"),
                        csvRecord.get("prenom"),
                        csvRecord.get("telephone"),
                        csvRecord.get("dateNaiss"),
                        csvRecord.get("passport"),
                        csvRecord.get("permis"),
                        csvRecord.get("genre"),
                        new AccParamReq(csvRecord.get("persontrauma"), csvRecord.get("consumalcohol"), csvRecord.get("consumdrugs")),
                        new CareParamReq(csvRecord.get("poids"), csvRecord.get("temperature"), csvRecord.get("pouls"), csvRecord.get("tension"), null),
                        csvRecord.get("crashDate"),
                        new ArrayList<>(),
                        csvRecord.get("Description"),
                        csvRecord.get("plate")
                );

                cares.add(care);
            }

            return cares;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }
}
