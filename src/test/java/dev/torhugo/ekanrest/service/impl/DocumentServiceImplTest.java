package dev.torhugo.ekanrest.service.impl;

import dev.torhugo.ekanrest.lib.data.dto.DocumentDTO;
import dev.torhugo.ekanrest.mapper.DocumentMapper;
import dev.torhugo.ekanrest.repository.DocumentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
class DocumentServiceImplTest {
    @Mock
    DocumentMapper documentMapping;
    @Mock
    DocumentRepository documentRepository;
    @InjectMocks
    DocumentServiceImpl documentServiceImpl;

    @InjectMocks
    DocumentServiceImplTestObject object;

    private Long beneficiaryId = null;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        beneficiaryId = 1L;
    }


    /**
     * Test save document.
     */
    @Test
    void testSaveDocument() {
        when(documentMapping.mapping(anyLong(), any()))
                .thenReturn(List.of(object.documentModel()));

        documentServiceImpl.saveDocument(List.of(object.documentInclusionDTO()), beneficiaryId);

        verify(documentMapping, times(1)).mapping(anyLong(), any());
    }

    /**
     * Test retrieve document by beneficiary id.
     */
    @Test
    void testRetrieveDocumentByBeneficiaryId() {
        when(documentMapping.mappingToDocuments(any())).thenReturn(List.of(object.documentDTO()));
        when(documentRepository.retrieveByBeneficiaryId(anyLong())).thenReturn(List.of(object.documentModel()));

        List<DocumentDTO> result = documentServiceImpl.retrieveDocumentByBeneficiaryId(beneficiaryId);

        Assertions.assertEquals(List.of(object.documentDTO()), result);
        verify(documentRepository, times(1)).retrieveByBeneficiaryId(anyLong());
        verify(documentMapping, times(1)).mappingToDocuments(any());
    }
}