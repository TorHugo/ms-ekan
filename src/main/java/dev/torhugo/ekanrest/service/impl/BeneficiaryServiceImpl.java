package dev.torhugo.ekanrest.service.impl;

import dev.torhugo.ekanrest.lib.data.domain.BeneficiaryModel;
import dev.torhugo.ekanrest.lib.data.domain.DocumentModel;
import dev.torhugo.ekanrest.lib.data.dto.BeneficiaryInclusionDTO;
import dev.torhugo.ekanrest.lib.data.dto.BeneficiaryResponseDTO;
import dev.torhugo.ekanrest.lib.data.dto.DocumentInclusionDTO;
import dev.torhugo.ekanrest.lib.exception.impl.DataBaseException;
import dev.torhugo.ekanrest.mapper.BeneficiaryMapper;
import dev.torhugo.ekanrest.mapper.DocumentMapper;
import dev.torhugo.ekanrest.repository.BeneficiaryRepository;
import dev.torhugo.ekanrest.repository.DocumentRepository;
import dev.torhugo.ekanrest.service.BeneficiaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static dev.torhugo.ekanrest.util.ConstantUtil.PATH_RETRIEVE_BENEFICIARY;

@Service
@Slf4j
@RequiredArgsConstructor
public class BeneficiaryServiceImpl implements BeneficiaryService {

    private final BeneficiaryRepository beneficiaryRepository;
    private final BeneficiaryMapper beneficiaryMapping;
    private final DocumentMapper documentMapping;
    private final DocumentRepository documentRepository;

    @Override
    public BeneficiaryResponseDTO createBeneficiary(final BeneficiaryInclusionDTO beneficiary) {
        log.info("[1] Validating existing Beneficiary with name: {}.", beneficiary.name());
        if (retrieveBeneficiary(beneficiary.name()))
            throw new DataBaseException("Beneficiary already exists!", beneficiary.name(), PATH_RETRIEVE_BENEFICIARY, "[GET]");
        log.info("[2] Mapping to Beneficiary.");
        final BeneficiaryModel model = mappingBeneficiary(beneficiary);
        log.info("[3] Saving to beneficiary.");
        final Long beneficiaryId = savingBeneficiary(model);
        log.info("[4] Mapping to Documents.");
        final List<DocumentModel> lsDocuments = mappingDocuments(beneficiaryId, beneficiary.lsDocument());
        log.info("[5] Saving to documents.");
        savingDocuments(lsDocuments);
        log.info("[6] Mapping to return.");
        return mappingToResponseCreate(model, beneficiaryId);
    }

    private BeneficiaryResponseDTO mappingToResponseCreate(final BeneficiaryModel model,
                                                           final Long beneficiaryId) {
        return beneficiaryMapping.mappingToResponseCreate(model, beneficiaryId);
    }

    private void savingDocuments(final List<DocumentModel> lsDocuments) {
        lsDocuments.forEach(documentRepository::saveDocuments);
    }

    private List<DocumentModel> mappingDocuments(final Long beneficiaryId,
                                                 final List<DocumentInclusionDTO> documents) {
        return documentMapping.mapping(beneficiaryId, documents);
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
