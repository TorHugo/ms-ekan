package dev.torhugo.ekanrest.mapper;

import dev.torhugo.ekanrest.lib.data.domain.BeneficiaryModel;
import dev.torhugo.ekanrest.lib.data.dto.*;

import java.util.List;

/**
 * The interface Document Mapper.
 */
public interface BeneficiaryMapper {

    /**
     * Mapping beneficiary model.
     *
     * @param beneficiary the beneficiary
     * @return the beneficiary model
     */
    BeneficiaryModel mapping(final BeneficiaryInclusionDTO beneficiary);

    /**
     * Mapping to response create beneficiary response dto.
     *
     * @param model         the model
     * @param beneficiaryId the beneficiary id
     * @return the beneficiary response dto
     */
    BeneficiaryResponseDTO mappingToResponseCreate(final BeneficiaryModel model,
                                                   final Long beneficiaryId);

    /**
     * Mapping beneficiaries list.
     *
     * @param beneficiary the beneficiary
     * @param documents the documents with beneficiary
     * @return the list
     */
    BeneficiariesDTO mappingBeneficiaries(final BeneficiaryModel beneficiary,
                                          final List<DocumentDTO> documents);

    /**
     * Mapping to update beneficiary model.
     *
     * @param beneficiaryModel the beneficiary model
     * @param beneficiary      the beneficiary
     * @return the beneficiary model
     */
    BeneficiaryModel mappingToUpdate(final BeneficiaryModel beneficiaryModel,
                                     final BeneficiaryBaseDTO beneficiary);

    /**
     * Mapping to response update beneficiary response dto.
     *
     * @param beneficiaryModel the beneficiary model
     * @param newBeneficiary   the new beneficiary
     * @return the beneficiary response dto
     */
    BeneficiaryFullResponseDTO mappingToResponseUpdate(final BeneficiaryModel beneficiaryModel,
                                                   final BeneficiaryBaseDTO newBeneficiary);

    /**
     * Mapping to response delete beneficiary response dto.
     *
     * @param beneficiaryModel the beneficiary model
     * @return the beneficiary response dto
     */
    BeneficiaryResponseDTO mappingToResponseDelete(final BeneficiaryModel beneficiaryModel);
}
