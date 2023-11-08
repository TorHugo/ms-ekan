package dev.torhugo.ekanrest.mapper.impl;

import dev.torhugo.ekanrest.lib.data.domain.DocumentModel;
import dev.torhugo.ekanrest.lib.data.dto.DocumentInclusionDTO;
import dev.torhugo.ekanrest.mapper.DocumentMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DocumentMapperImpl implements DocumentMapper {
    @Override
    public List<DocumentModel> mapping(final Long beneficiaryId,
                                       final List<DocumentInclusionDTO> documents) {
        return documents
                .stream()
                .map(document ->
                        DocumentModel.builder()
                                .beneficiaryId(beneficiaryId)
                                .typeDocument(document.typeDocument())
                                .description(document.description())
                                .inActive(Boolean.TRUE)
                                .build())
                .toList();
    }
}
