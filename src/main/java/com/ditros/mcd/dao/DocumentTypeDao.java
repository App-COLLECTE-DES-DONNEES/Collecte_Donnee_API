package com.ditros.mcd.dao;

import com.ditros.mcd.model.entity.AlcoholTestResult;
import com.ditros.mcd.model.entity.Document;
import com.ditros.mcd.model.entity.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentTypeDao extends JpaRepository<DocumentType, Long> {
    List<DocumentType> findByLang(String lang);

}
