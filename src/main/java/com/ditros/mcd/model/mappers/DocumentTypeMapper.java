package com.ditros.mcd.model.mappers;

import com.ditros.mcd.model.dto.DocumentTypeResp;
import com.ditros.mcd.model.entity.DocumentType;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class DocumentTypeMapper {
    public DocumentTypeResp fromDocumentType(DocumentType documentType) {
        if(documentType==null) return null;
        DocumentTypeResp documentTypeResp = new DocumentTypeResp();
        BeanUtils.copyProperties(documentType, documentTypeResp);

        return documentTypeResp;
    }
}
