package dev.torhugo.ekanrest.mapper.impl;

import dev.torhugo.ekanrest.lib.data.domain.BeneficiaryModel;
import dev.torhugo.ekanrest.lib.data.dto.*;
import dev.torhugo.ekanrest.mapper.BeneficiaryMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static dev.torhugo.ekanrest.util.ConstantUtil.*;
import static dev.torhugo.ekanrest.util.MethodUtil.*;

@Component
public class BeneficiaryMapperImpl implements BeneficiaryMapper {
    @Override
    public BeneficiaryModel mapping(final BeneficiaryInclusionDTO beneficiary) {
        return BeneficiaryModel.builder()
                .name(beneficiary.name())
                .phoneNumber(beneficiary.phoneNumber())
                .birthDate(beneficiary.birthDate())
                .inActive(Boolean.TRUE)
                .build();
    }

    @Override
    public BeneficiaryResponseDTO mappingToResponseCreate(final BeneficiaryModel model,
                                                          final Long beneficiaryId) {

        return BeneficiaryResponseDTO.builder()
                .beneficiaryId(beneficiaryId)
                .name(model.getName())
                .createdAt(LocalDateTime.now())
                .links(buildLink(beneficiaryId))
                .build();
    }

    @Override
    public BeneficiariesDTO mappingBeneficiaries(final BeneficiaryModel beneficiary,
                                                 final List<DocumentDTO> documents) {
        return BeneficiariesDTO.builder()
                        .beneficiaryId(beneficiary.getBeneficiaryId())
                        .name(beneficiary.getName())
                        .phoneNumber(beneficiary.getPhoneNumber())
                        .birthDate(beneficiary.getBirthDate())
                        .createdAt(beneficiary.getCreatedAt())
                        .updatedAt(beneficiary.getUpdatedAt())
                        .documents(documents)
                        .links(buildLink(beneficiary.getBeneficiaryId()))
                    .build();
    }

    @Override
    public BeneficiaryModel mappingToUpdate(final BeneficiaryModel beneficiaryModel,
                                            final BeneficiaryBaseDTO newBeneficiary) {
        beneficiaryModel.setName(Objects.isNull(newBeneficiary.name()) ? beneficiaryModel.getName() : newBeneficiary.name());
        beneficiaryModel.setBirthDate(Objects.isNull(newBeneficiary.birthDate()) ? beneficiaryModel.getBirthDate() : newBeneficiary.birthDate());
        beneficiaryModel.setPhoneNumber(Objects.isNull(newBeneficiary.phoneNumber()) ? beneficiaryModel.getPhoneNumber() : newBeneficiary.phoneNumber());
        return beneficiaryModel;
    }

    @Override
    public BeneficiaryFullResponseDTO mappingToResponseUpdate(final BeneficiaryModel beneficiaryModel,
                                                          final BeneficiaryBaseDTO newBeneficiary) {
        return BeneficiaryFullResponseDTO.builder()
                .beneficiaryId(beneficiaryModel.getBeneficiaryId())
                .name(Objects.isNull(newBeneficiary.name()) ? beneficiaryModel.getName() : newBeneficiary.name())
                .birthDate(Objects.isNull(newBeneficiary.birthDate()) ? beneficiaryModel.getBirthDate() : newBeneficiary.birthDate())
                .phoneNumber(Objects.isNull(newBeneficiary.phoneNumber()) ? beneficiaryModel.getPhoneNumber() : newBeneficiary.phoneNumber())
                .createdAt(beneficiaryModel.getCreatedAt())
                .updatedAt(LocalDateTime.now())
                .links(buildLink(beneficiaryModel.getBeneficiaryId()))
                .build();
    }

    @Override
    public BeneficiaryResponseDTO mappingToResponseDelete(final BeneficiaryModel beneficiaryModel) {
        return BeneficiaryResponseDTO.builder()
                .beneficiaryId(beneficiaryModel.getBeneficiaryId())
                .name(beneficiaryModel.getName())
                .createdAt(beneficiaryModel.getCreatedAt())
                .updatedAt(beneficiaryModel.getUpdatedAt())
                .links(buildLinkDelete(beneficiaryModel.getName()))
                .build();
    }

    private List<LinkResponseDTO> buildLinkDelete(final String name) {
        List<LinkResponseDTO> links = new ArrayList<>();
        links.add(buildToPost(MESSAGE_REACTIVATE_BENEFICIARY, PATH_REACTIVATE_BENEFICIARY, name));
        return links;
    }

    private List<LinkResponseDTO> buildLink(final Long beneficiaryId) {
        List<LinkResponseDTO> links = new ArrayList<>();
        links.add(buildToGet(MESSAGE_RETRIEVE_BENEFICIARY, PATH_RETRIEVE_BENEFICIARY, beneficiaryId.toString()));
        links.add(buildToGet(MESSAGE_RETRIEVE_DOCUMENTS_BENEFICIARY, PATH_RETRIEVE_DOCUMENTS_BENEFICIARY, beneficiaryId.toString()));
        links.add(buildToPost(MESSAGE_ADD_DOCUMENT_BENEFICIARY, PATH_ADD_DOCUMENT_BENEFICIARY, beneficiaryId.toString()));
        links.add(buildToPut(MESSAGE_UPDATE_BENEFICIARY, PATH_UPDATE_BENEFICIARY, beneficiaryId.toString()));
        links.add(buildToDelete(MESSAGE_DELETE_BENEFICIARY, PATH_DELETE_BENEFICIARY, beneficiaryId.toString()));
        return links;
    }
}
