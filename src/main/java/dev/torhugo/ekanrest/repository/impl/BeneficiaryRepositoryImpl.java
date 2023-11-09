package dev.torhugo.ekanrest.repository.impl;

import dev.torhugo.ekanrest.lib.data.database.DatabaseService;
import dev.torhugo.ekanrest.lib.data.domain.BeneficiaryModel;
import dev.torhugo.ekanrest.repository.BeneficiaryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
@PropertySource("classpath:query/beneficiary_tb.properties")
public class BeneficiaryRepositoryImpl implements BeneficiaryRepository {

    private final DatabaseService service;

    @Value("${SPS.BENEFICIARY_TB.WHERE.NAME}")
    private String queryRetrieveBeneficiaryByName;

    @Value("${SPS.BENEFICIARY_TB.WHERE.BENEFICIARY_ID}")
    private String queryRetrieveBeneficiaryById;

    @Value("${SPI.BENEFICIARY_TB}")
    private String queryPersistBeneficiary;

    @Value("${SPS.BENEFICIARY_TB}")
    private String queryRetrieveBeneficiaries;

    @Override
    public BeneficiaryModel retrieveByName(final String name) {
        return service.retrieve(queryRetrieveBeneficiaryByName,
                                buildParamName(name),
                                BeanPropertyRowMapper.newInstance(BeneficiaryModel.class))
                .orElse(null);
    }

    @Override
    public Long saveBeneficiary(final BeneficiaryModel model) {
        return service.persistReturn(queryPersistBeneficiary, model);
    }

    @Override
    public List<BeneficiaryModel> retrieveAllBeneficiaries() {
        return service.retrieveList(queryRetrieveBeneficiaries,
                                    BeanPropertyRowMapper.newInstance(BeneficiaryModel.class));
    }

    @Override
    public BeneficiaryModel retrieveById(final Long beneficiaryId) {
        return service.retrieve(queryRetrieveBeneficiaryById,
                        buildParamId(beneficiaryId),
                        BeanPropertyRowMapper.newInstance(BeneficiaryModel.class))
                .orElse(null);
    }

    private MapSqlParameterSource buildParamName(final String name) {
        return new MapSqlParameterSource("name", name);
    }
    private MapSqlParameterSource buildParamId(final Long beneficiaryId) {
        return new MapSqlParameterSource("beneficiaryId", beneficiaryId);
    }
}
