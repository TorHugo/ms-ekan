package dev.torhugo.ekanrest.repository;

import dev.torhugo.ekanrest.lib.data.domain.DocumentModel;

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
}
