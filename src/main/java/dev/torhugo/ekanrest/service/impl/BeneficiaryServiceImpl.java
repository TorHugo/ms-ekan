package dev.torhugo.ekanrest.service.impl;

import dev.torhugo.ekanrest.lib.data.domain.BeneficiaryModel;
import dev.torhugo.ekanrest.lib.data.dto.*;
import dev.torhugo.ekanrest.mapper.BeneficiaryMapper;
import dev.torhugo.ekanrest.repository.BeneficiaryRepository;
import dev.torhugo.ekanrest.service.BeneficiaryService;
import dev.torhugo.ekanrest.service.DocumentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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
        if (Objects.nonNull(retrieveBeneficiary(beneficiary.name())))
            throwException(MESSAGE_BENEFICIARY_ALREADY_EXISTS, beneficiary.name(), PATH_RETRIEVE_BENEFICIARY, HttpMethod.GET.name());
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

    @Override
    public BeneficiaryResponseDTO addDocument(final Long beneficiaryId,
                                        final List<DocumentInclusionDTO> documents) {
        log.info("[1] getBeneficiaryById().");
        final BeneficiaryModel beneficiaryModel = retrieveBeneficiaryById(beneficiaryId);
        log.info("[2] Saving to documents.");
        saveDocument(documents, beneficiaryId);
        log.info("[3] Mapping to return.");
        return mappingToResponseCreate(beneficiaryModel, beneficiaryId);
    }

    @Override
    public BeneficiaryFullResponseDTO updateBeneficiary(final Long beneficiaryId,
                                                    final BeneficiaryBaseDTO newBeneficiary) {
        log.info("[1] updateBeneficiary().");
        final BeneficiaryModel beneficiaryModel = retrieveBeneficiaryById(beneficiaryId);
        log.info("[2] Update to beneficiary in the database.");
        updateBeneficiary(beneficiaryModel, newBeneficiary);
        log.info("[3] Mapping to return.");
        return mappingToResponseUpdate(beneficiaryModel, newBeneficiary);
    }

    @Override
    public BeneficiaryResponseDTO deleteBeneficiary(final Long beneficiaryId) {
        log.info("[1] updateBeneficiary().");
        final BeneficiaryModel beneficiaryModel = retrieveBeneficiaryById(beneficiaryId);
        log.info("[2] Delete to beneficiary in the database.");
        deleteBeneficiary(beneficiaryModel);
        log.info("[3] Mapping to return.");
        return mappingToResponseDelete(beneficiaryModel);
    }

    @Override
    public BeneficiaryResponseDTO reactivateBeneficiary(final String name) {
        log.info("[1] Validating existing Beneficiary with name: {}.", name);
        final BeneficiaryModel beneficiaryModel = retrieveBeneficiaryIsNotActive(name);
        if (Objects.isNull(beneficiaryModel))
            throwException(MESSAGE_BENEFICIARY_NOT_FOUND, name, PATH_CREATE_BENEFICIARY, HttpMethod.POST.name());
        log.info("[2] Reactivate beneficiary.");
        reactivateBeneficiary(beneficiaryModel);
        log.info("[3] Mapping to return.");
        return mappingToResponseCreate(beneficiaryModel, beneficiaryModel.getBeneficiaryId());
    }

    private void reactivateBeneficiary(final BeneficiaryModel beneficiaryModel) {
        beneficiaryRepository.reactivateBeneficiary(beneficiaryModel.getBeneficiaryId());
    }

    private BeneficiaryResponseDTO mappingToResponseDelete(final BeneficiaryModel beneficiaryModel) {
        return beneficiaryMapping.mappingToResponseDelete(beneficiaryModel);
    }

    private void deleteBeneficiary(final BeneficiaryModel beneficiaryModel) {
        beneficiaryRepository.deleteById(beneficiaryModel.getBeneficiaryId());
    }

    private BeneficiaryFullResponseDTO mappingToResponseUpdate(final BeneficiaryModel beneficiaryModel,
                                                           final BeneficiaryBaseDTO newBeneficiary) {
        return beneficiaryMapping.mappingToResponseUpdate(beneficiaryModel, newBeneficiary);
    }

    private void updateBeneficiary(final BeneficiaryModel beneficiaryModel,
                                   final BeneficiaryBaseDTO beneficiary) {
        beneficiaryRepository.updateBeneficiary(mappingBeneficiary(beneficiaryModel, beneficiary));
    }

    private BeneficiaryModel mappingBeneficiary(final BeneficiaryModel beneficiaryModel,
                                                final BeneficiaryBaseDTO beneficiary) {
        return beneficiaryMapping.mappingToUpdate(beneficiaryModel, beneficiary);
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

    private BeneficiaryModel retrieveBeneficiary(final String name) {
        return beneficiaryRepository.retrieveByName(name);
    }

    private BeneficiaryModel retrieveBeneficiaryIsNotActive(final String name) {
        return beneficiaryRepository.retrieveByNameIsNotActive(name);
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
