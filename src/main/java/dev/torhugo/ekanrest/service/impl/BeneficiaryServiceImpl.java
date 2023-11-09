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
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static dev.torhugo.ekanrest.util.ConstantUtil.*;
import static dev.torhugo.ekanrest.util.ExceptionUtil.throwException;

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
        log.info("[1] getAllBeneficiaries().");
        final List<BeneficiaryModel> beneficiaries = retrieveBeneficiaries();
        return retrieveDocuments(beneficiaries);
    }

    @Override
    public BeneficiariesDTO getBeneficiaryById(final Long beneficiaryId) {
        log.info("[1] getBeneficiaryById().");
        final BeneficiaryModel beneficiaryModel = retrieveBeneficiaryById(beneficiaryId);
        return retrieveDocumentsByBeneficiaryId(beneficiaryModel);
    }

    private BeneficiariesDTO retrieveDocumentsByBeneficiaryId(final BeneficiaryModel beneficiary) {
        log.info("[2] Mapping to return for beneficiaryId: {}.", beneficiary.getBeneficiaryId());
        final List<DocumentDTO> documents = documentService.retrieveDocumentByBeneficiaryId(beneficiary.getBeneficiaryId());
        return mappingBeneficiaries(beneficiary, documents);
    }

    private List<BeneficiariesDTO> retrieveDocuments(final List<BeneficiaryModel> beneficiaries) {
        log.info("[2] Mapping to return.");
        return beneficiaries.stream()
                .map(this::retrieveDocumentsByBeneficiaryId)
                .toList();
    }


    private BeneficiariesDTO mappingBeneficiaries(final BeneficiaryModel beneficiary,
                                                        final List<DocumentDTO> documents) {
        return beneficiaryMapping.mappingBeneficiaries(beneficiary, documents);
    }

    private List<BeneficiaryModel> retrieveBeneficiaries() {
        log.info("[1] Find all beneficiaries.");
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

    private BeneficiaryModel retrieveBeneficiaryById(final Long beneficiaryId) {
        log.info("[2] Retrieve Beneficiary by Id: {}.", beneficiaryId);
        final BeneficiaryModel beneficiaryModel = beneficiaryRepository.retrieveById(beneficiaryId);
        log.info("[3] Validating existing Beneficiary with id: {}.", beneficiaryId);
        if (Objects.isNull(beneficiaryModel))
            throwException(MESSAGE_BENEFICIARY_NOT_FOUND, beneficiaryId.toString(), PATH_CREATE_BENEFICIARY, HttpMethod.POST.name());
        return beneficiaryModel;
    }
}
