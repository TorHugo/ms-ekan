package dev.torhugo.ekanrest.service;

import dev.torhugo.ekanrest.lib.data.dto.BeneficiariesDTO;
import dev.torhugo.ekanrest.lib.data.dto.BeneficiaryInclusionDTO;
import dev.torhugo.ekanrest.lib.data.dto.BeneficiaryResponseDTO;

import java.util.List;

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

    /**
     * Gets all beneficiaries.
     *
     * @return the all beneficiaries
     */
    List<BeneficiariesDTO> getAllBeneficiaries();
}
