package dev.torhugo.ekanrest.service;

import dev.torhugo.ekanrest.lib.data.dto.*;

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

    /**
     * Gets beneficiary by id.
     *
     * @param beneficiaryId the beneficiary id
     * @return the beneficiary by id
     */
    BeneficiariesDTO getBeneficiaryById(final Long beneficiaryId);

    /**
     * Add document beneficiaries dto.
     *
     * @param beneficiaryId the beneficiary id
     * @param documents     the documents with beneficiary
     * @return the beneficiaries dto
     */
    BeneficiaryResponseDTO addDocument(final Long beneficiaryId, final List<DocumentInclusionDTO> documents);

    /**
     * Update to beneficiary.
     *
     * @param beneficiaryId the beneficiary id
     * @param newBeneficiary   the beneficiary
     * @return the beneficiary response dto
     */
    BeneficiaryFullResponseDTO updateBeneficiary(final Long beneficiaryId, final BeneficiaryBaseDTO newBeneficiary);

    /**
     * Delete beneficiary by beneficiaryId.
     *
     * @param beneficiaryId the beneficiary id
     * @return the beneficiary response dto
     */
    BeneficiaryResponseDTO deleteBeneficiary(final Long beneficiaryId);

    /**
     * Reactivate beneficiary with name.
     *
     * @param name the name
     * @return the beneficiary response dto
     */
    BeneficiaryResponseDTO reactivateBeneficiary(final String name);
}
