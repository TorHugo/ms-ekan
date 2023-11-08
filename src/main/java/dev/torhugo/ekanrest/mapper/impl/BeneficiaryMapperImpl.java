package dev.torhugo.ekanrest.mapper.impl;

import dev.torhugo.ekanrest.lib.data.domain.BeneficiaryModel;
import dev.torhugo.ekanrest.lib.data.dto.BeneficiaryInclusionDTO;
import dev.torhugo.ekanrest.lib.data.dto.BeneficiaryResponseDTO;
import dev.torhugo.ekanrest.mapper.BeneficiaryMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collections;

import static dev.torhugo.ekanrest.util.ConstantUtil.MESSAGE_RETRIEVE_BENEFICIARY;
import static dev.torhugo.ekanrest.util.ConstantUtil.PATH_RETRIEVE_BENEFICIARY;
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
                .links(Collections.singletonList(
                        buildToGet(MESSAGE_RETRIEVE_BENEFICIARY,
                                    PATH_RETRIEVE_BENEFICIARY,
                                    beneficiaryId.toString()))
                )
                .build();
    }
}
