package dev.torhugo.ekanrest.mapper.impl;

import dev.torhugo.ekanrest.lib.data.domain.BeneficiaryModel;
import dev.torhugo.ekanrest.lib.data.dto.*;
import dev.torhugo.ekanrest.mapper.BeneficiaryMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static dev.torhugo.ekanrest.util.ConstantUtil.*;
import static dev.torhugo.ekanrest.util.MethodUtil.buildToGet;

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

    private List<LinkResponseDTO> buildLink(final Long beneficiaryId) {
        List<LinkResponseDTO> links = new ArrayList<>();
        links.add(buildToGet(MESSAGE_RETRIEVE_BENEFICIARY, PATH_RETRIEVE_BENEFICIARY, beneficiaryId.toString()));
        links.add(buildToGet(MESSAGE_RETRIEVE_DOCUMENTS_BENEFICIARY, PATH_RETRIEVE_DOCUMENTS_BENEFICIARY, beneficiaryId.toString()));
        return links;
    }
}
