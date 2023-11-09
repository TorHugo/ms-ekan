package dev.torhugo.ekanrest.service;

import dev.torhugo.ekanrest.lib.data.dto.DocumentDTO;
import dev.torhugo.ekanrest.lib.data.dto.DocumentInclusionDTO;

import javax.swing.text.Document;
import java.util.List;

/**
 * The interface Document service.
 */
public interface DocumentService {
    /**
     * Save document.
     *
     * @param documentInclusionDTOS the document inclusion dtos
     * @param beneficiaryId         the beneficiary id
     */
    void saveDocument(final List<DocumentInclusionDTO> documentInclusionDTOS,
                      final Long beneficiaryId);

    /**
     * Retrieve document by beneficiary id list.
     *
     * @param beneficiaryId the beneficiary id
     * @return the list
     */
    List<DocumentDTO> retrieveDocumentByBeneficiaryId(final Long beneficiaryId);
}
