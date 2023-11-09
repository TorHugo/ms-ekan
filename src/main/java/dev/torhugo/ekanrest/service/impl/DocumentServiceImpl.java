package dev.torhugo.ekanrest.service.impl;

import dev.torhugo.ekanrest.lib.data.domain.DocumentModel;
import dev.torhugo.ekanrest.lib.data.dto.DocumentDTO;
import dev.torhugo.ekanrest.lib.data.dto.DocumentInclusionDTO;
import dev.torhugo.ekanrest.mapper.DocumentMapper;
import dev.torhugo.ekanrest.repository.DocumentRepository;
import dev.torhugo.ekanrest.service.DocumentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {


    private final DocumentMapper documentMapping;
    private final DocumentRepository documentRepository;

    @Override
    public void saveDocument(final List<DocumentInclusionDTO> documents, final Long beneficiaryId) {
        log.info("[1] Mapping to Documents.");
        final List<DocumentModel> lsDocuments = mappingDocuments(beneficiaryId, documents);
        log.info("[2] Saving to documents.");
        savingDocuments(lsDocuments);
    }

    @Override
    public List<DocumentDTO> retrieveDocumentByBeneficiaryId(final Long beneficiaryId) {
        log.info("[1] Find all documents.");
        final List<DocumentModel> lsDocuments = retrieveDocuments(beneficiaryId);
        log.info("[2] Mapping to documents.");
        return mappingDocuments(lsDocuments);
    }

    private List<DocumentDTO> mappingDocuments(final List<DocumentModel> lsDocuments) {
        return documentMapping.mappingToDocuments(lsDocuments);
    }

    private List<DocumentModel> retrieveDocuments(final Long beneficiaryId) {
        return documentRepository.retrieveByBeneficiaryId(beneficiaryId);
    }

    private void savingDocuments(final List<DocumentModel> lsDocuments) {
        lsDocuments.forEach(documentRepository::saveDocuments);
    }

    private List<DocumentModel> mappingDocuments(final Long beneficiaryId,
                                                 final List<DocumentInclusionDTO> documents) {
        return documentMapping.mapping(beneficiaryId, documents);
    }
}
