package dev.torhugo.ekanrest.mapper;

import dev.torhugo.ekanrest.lib.data.domain.BeneficiaryModel;
import dev.torhugo.ekanrest.lib.data.dto.BeneficiaryInclusionDTO;
import dev.torhugo.ekanrest.lib.data.dto.BeneficiaryResponseDTO;

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
}
