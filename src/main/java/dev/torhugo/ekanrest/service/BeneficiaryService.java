package dev.torhugo.ekanrest.service;

import dev.torhugo.ekanrest.lib.data.dto.BeneficiaryInclusionDTO;
import dev.torhugo.ekanrest.lib.data.dto.BeneficiaryResponseDTO;

/**
 * The interface Beneficiary service.
 */
public interface BeneficiaryService {

    /**
     * Create beneficiary object.
     *
     * @param beneficiary the beneficiary
     * @return the object
     */
    BeneficiaryResponseDTO createBeneficiary(final BeneficiaryInclusionDTO beneficiary);
}
