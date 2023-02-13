package com.ditros.mcd.service;

import com.ditros.mcd.dao.DirectCauseDao;
import com.ditros.mcd.dao.DocumentTypeDao;
import com.ditros.mcd.model.dto.DirectCauseResp;
import com.ditros.mcd.model.dto.DocumentTypeResp;
import com.ditros.mcd.model.entity.DirectCause;
import com.ditros.mcd.model.entity.DocumentType;
import com.ditros.mcd.model.mappers.DirectCauseMapper;
import com.ditros.mcd.model.mappers.DocumentTypeMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DocumentTypeService {
    private DocumentTypeMapper documentTypeMapper;
    private DocumentTypeDao documentTypeDao;

    public List<DocumentTypeResp> getAll(String lang) {
        if(lang==null) lang="fr";
        List<DocumentType> documentTypes = documentTypeDao.findByLang(lang);

        return documentTypes.stream()
                .map(d -> documentTypeMapper.fromDocumentType(d))
                .collect(Collectors.toList());
    }
}
