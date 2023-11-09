package dev.torhugo.ekanrest.service.impl;

import dev.torhugo.ekanrest.lib.data.domain.DocumentModel;
import dev.torhugo.ekanrest.lib.data.dto.DocumentDTO;
import dev.torhugo.ekanrest.lib.data.dto.DocumentInclusionDTO;

import java.time.LocalDateTime;

public class DocumentServiceImplTestObject {
    public DocumentModel documentModel(){
        return DocumentModel.builder()
                .documentId(1L)
                .beneficiaryId(1L)
                .typeDocument("typeDocument")
                .description("description")
                .inActive(true)
                .build();
    }

    public DocumentInclusionDTO documentInclusionDTO(){
        return DocumentInclusionDTO.builder()
               .typeDocument("typeDocument")
               .description("description")
               .build();
    }

    public DocumentDTO documentDTO(){
        return DocumentDTO.builder()
                .typeDocument("typeDocument")
                .description("description")
                .inActive(true)
                .createdAt(LocalDateTime.of(2023, 11, 9, 1, 1))
                .updatedAt(null)
              .build();
    }
}
