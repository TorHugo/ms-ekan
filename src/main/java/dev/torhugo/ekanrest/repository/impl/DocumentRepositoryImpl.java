package dev.torhugo.ekanrest.repository.impl;

import dev.torhugo.ekanrest.lib.data.database.DatabaseService;
import dev.torhugo.ekanrest.lib.data.domain.DocumentModel;
import dev.torhugo.ekanrest.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
@PropertySource("classpath:query/document_tb.properties")
public class DocumentRepositoryImpl implements DocumentRepository {

    private final DatabaseService service;

    @Value("${SPI.DOCUMENT_TB}")
    private String queryPersistDocument;
    @Value("${SPI.DOCUMENT_TB.WHERE.BENEFICIARY_ID}")
    private String queryRetrieveByBeneficiaryId;

    @Override
    public void saveDocuments(final DocumentModel documentModel) {
        service.persist(queryPersistDocument, documentModel);
    }

    @Override
    public List<DocumentModel> retrieveByBeneficiaryId(final Long beneficiaryId) {
        return service.retrieveList(queryRetrieveByBeneficiaryId,
                buildParam(beneficiaryId),
                BeanPropertyRowMapper.newInstance(DocumentModel.class));
    }

    private MapSqlParameterSource buildParam(final Long beneficiaryId) {
        return new MapSqlParameterSource("beneficiaryId", beneficiaryId);
    }
}
