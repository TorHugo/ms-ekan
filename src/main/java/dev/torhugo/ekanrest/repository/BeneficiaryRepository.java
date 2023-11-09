package dev.torhugo.ekanrest.repository;

import dev.torhugo.ekanrest.lib.data.domain.BeneficiaryModel;
import java.util.List;

/**
 * The interface Beneficiary Repository.
 */
public interface BeneficiaryRepository {

    /**
     * Retrieve by name beneficiary model.
     *
     * @param name the name
     * @return the beneficiary model
     */
    BeneficiaryModel retrieveByName(final String name);


    /**
     * Save beneficiary long.
     *
     * @param model the model
     * @return the benefiticiary id
     */
    Long saveBeneficiary(final BeneficiaryModel model);

    /**
     * Retrieve all beneficiaries list.
     *
     * @return the list
     */
    List<BeneficiaryModel> retrieveAllBeneficiaries();

    /**
     * Retrieve by id beneficiary model.
     *
     * @param beneficiaryId the beneficiary id
     * @return the beneficiary model
     */
    BeneficiaryModel retrieveById(final Long beneficiaryId);
}
