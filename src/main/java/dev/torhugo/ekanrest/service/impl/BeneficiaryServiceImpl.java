package dev.torhugo.ekanrest.service.impl;

import dev.torhugo.ekanrest.lib.data.domain.BeneficiaryModel;
import dev.torhugo.ekanrest.lib.data.dto.*;
import dev.torhugo.ekanrest.lib.exception.impl.DataBaseException;
import dev.torhugo.ekanrest.mapper.BeneficiaryMapper;
import dev.torhugo.ekanrest.repository.BeneficiaryRepository;
import dev.torhugo.ekanrest.service.BeneficiaryService;
import dev.torhugo.ekanrest.service.DocumentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static dev.torhugo.ekanrest.util.ConstantUtil.PATH_RETRIEVE_BENEFICIARY;

@Service
@Slf4j
@RequiredArgsConstructor
public class BeneficiaryServiceImpl implements BeneficiaryService {

    private final BeneficiaryRepository beneficiaryRepository;
    private final BeneficiaryMapper beneficiaryMapping;

    private final DocumentService documentService;

    @Override
    public BeneficiaryResponseDTO createBeneficiary(final BeneficiaryInclusionDTO beneficiary) {
        log.info("[1] Validating existing Beneficiary with name: {}.", beneficiary.name());
        if (retrieveBeneficiary(beneficiary.name()))
            throw new DataBaseException("Beneficiary already exists!", beneficiary.name(), PATH_RETRIEVE_BENEFICIARY, "[GET]");
        log.info("[2] Mapping to Beneficiary.");
        final BeneficiaryModel model = mappingBeneficiary(beneficiary);
        log.info("[3] Saving to beneficiary.");
        final Long beneficiaryId = savingBeneficiary(model);
        log.info("[4] Saving to Documents.");
        saveDocument(beneficiary.lsDocument(), beneficiaryId);
        log.info("[5] Mapping to return.");
        return mappingToResponseCreate(model, beneficiaryId);
    }

    @Override
    public List<BeneficiariesDTO> getAllBeneficiaries() {
        log.info("[1] Find all beneficiaries.");
        final List<BeneficiaryModel> beneficiaries = retrieveBeneficiaries();
        log.info("[2] Retrieve documents.");
        return retrieveDocuments(beneficiaries);
    }

    private List<BeneficiariesDTO> retrieveDocuments(final List<BeneficiaryModel> beneficiaries) {
        List<BeneficiariesDTO> lsBeneficiaries = new ArrayList<>();
        for (BeneficiaryModel beneficiary: beneficiaries){
            log.info("[3] Retrieve documents by beneficiaryId: {}.", beneficiary.getBeneficiaryId());
            final List<DocumentDTO> documents =
                    documentService.retrieveDocumentByBeneficiaryId(beneficiary.getBeneficiaryId());
            log.info("[4] Mapping to documents and beneficiary in DTO.");
            lsBeneficiaries.add(mappingBeneficiaries(beneficiary, documents));
        }
        log.info("[5] Mapping to return.");
        return lsBeneficiaries;
    }

    private BeneficiariesDTO mappingBeneficiaries(final BeneficiaryModel beneficiary,
                                                        final List<DocumentDTO> documents) {
        return beneficiaryMapping.mappingBeneficiaries(beneficiary, documents);
    }

    private List<BeneficiaryModel> retrieveBeneficiaries() {
        return beneficiaryRepository.retrieveAllBeneficiaries();
    }

    private void saveDocument(final List<DocumentInclusionDTO> documents,
                              final Long beneficiaryId) {
        documentService.saveDocument(documents, beneficiaryId);
    }

    private BeneficiaryResponseDTO mappingToResponseCreate(final BeneficiaryModel model,
                                                           final Long beneficiaryId) {
        return beneficiaryMapping.mappingToResponseCreate(model, beneficiaryId);
    }

    private Long savingBeneficiary(final BeneficiaryModel model) {
        return beneficiaryRepository.saveBeneficiary(model);
    }

    private BeneficiaryModel mappingBeneficiary(final BeneficiaryInclusionDTO beneficiary) {
        return beneficiaryMapping.mapping(beneficiary);
    }

    private boolean retrieveBeneficiary(final String name) {
        return Objects.nonNull(beneficiaryRepository.retrieveByName(name));
    }
}
