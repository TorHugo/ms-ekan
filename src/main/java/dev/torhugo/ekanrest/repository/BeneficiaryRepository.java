package dev.torhugo.ekanrest.repository;

import dev.torhugo.ekanrest.lib.data.domain.BeneficiaryModel;

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
}
