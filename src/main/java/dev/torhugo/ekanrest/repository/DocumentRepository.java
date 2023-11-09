package dev.torhugo.ekanrest.repository;

import dev.torhugo.ekanrest.lib.data.domain.DocumentModel;

import java.util.List;

/**
 * The interface Document Repository.
 */
public interface DocumentRepository {

    /**
     * Save document.
     *
     * @param documentModel
     */
    void saveDocuments(final DocumentModel documentModel);

    /**
     * Retrieve by beneficiary id list.
     *
     * @param beneficiaryId the beneficiary id
     * @return the list
     */
    List<DocumentModel> retrieveByBeneficiaryId(final Long beneficiaryId);
}
