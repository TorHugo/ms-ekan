package dev.torhugo.ekanrest.mapper;

import dev.torhugo.ekanrest.lib.data.domain.DocumentModel;
import dev.torhugo.ekanrest.lib.data.dto.DocumentDTO;
import dev.torhugo.ekanrest.lib.data.dto.DocumentInclusionDTO;

import java.util.List;

/**
 * The interface Document Mapper.
 */
public interface DocumentMapper {

    /**
     * Mapping documents for save.
     *
     * @param beneficiaryId the beneficiary id
     * @param documents     the documents
     * @return the list
     */
    List<DocumentModel> mapping(final Long beneficiaryId,
                                final List<DocumentInclusionDTO> documents);

    /**
     * Mapping to documents list.
     *
     * @param lsDocuments the ls documents
     * @return the list
     */
    List<DocumentDTO> mappingToDocuments(final List<DocumentModel> lsDocuments);
}
