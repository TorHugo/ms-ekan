package dev.torhugo.ekanrest.service.impl;

import dev.torhugo.ekanrest.lib.data.dto.*;
import dev.torhugo.ekanrest.lib.exception.impl.DataBaseException;
import dev.torhugo.ekanrest.mapper.BeneficiaryMapper;
import dev.torhugo.ekanrest.repository.BeneficiaryRepository;
import dev.torhugo.ekanrest.service.DocumentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.jupiter.api.function.Executable;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
class BeneficiaryServiceImplTest {
    @Mock
    BeneficiaryRepository beneficiaryRepository;
    @Mock
    BeneficiaryMapper beneficiaryMapping;
    @Mock
    DocumentService documentService;
    @InjectMocks
    BeneficiaryServiceImpl beneficiaryServiceImpl;

    @InjectMocks
    BeneficiaryServiceImplTestObject object;

    private Long beneficiaryId;
    private String name;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        beneficiaryId = 1L;
        name = "Test";
    }

    @Test
    void testCreateBeneficiary() {
        when(beneficiaryRepository.retrieveByName(anyString()))
                .thenReturn(null);
        when(beneficiaryRepository.saveBeneficiary(any()))
                .thenReturn(beneficiaryId);
        when(beneficiaryMapping.mapping(any()))
                .thenReturn(object.beneficiaryModel());
        when(beneficiaryMapping.mappingToResponseCreate(any(), anyLong()))
                .thenReturn(object.beneficiaryResponseDTO());

        BeneficiaryResponseDTO result = beneficiaryServiceImpl.createBeneficiary(object.beneficiaryInclusionDTO());

        Assertions.assertEquals(object.beneficiaryResponseDTO(), result);
        verify(beneficiaryRepository, times(1)).retrieveByName(anyString());
        verify(beneficiaryRepository, times(1)).saveBeneficiary(any());
        verify(beneficiaryMapping, times(1)).mapping(any());
        verify(beneficiaryMapping, times(1)).mappingToResponseCreate(any(), anyLong());
    }

    @Test
    void testCreateBeneficiaryError() {
        when(beneficiaryRepository.retrieveByName(anyString()))
                .thenReturn(object.beneficiaryModel());

        Executable executable = () -> beneficiaryServiceImpl.createBeneficiary(object.beneficiaryInclusionDTO());
        assertThrows(DataBaseException.class, executable);

        verify(beneficiaryRepository, times(1)).retrieveByName(anyString());
    }

    @Test
    void testGetAllBeneficiaries() {
        when(beneficiaryRepository.retrieveAllBeneficiaries()).thenReturn(List.of(object.beneficiaryModel()));
        when(beneficiaryMapping.mappingBeneficiaries(any(), any())).thenReturn(object.beneficiariesDTO());

        List<BeneficiariesDTO> result = beneficiaryServiceImpl.getAllBeneficiaries();

        Assertions.assertEquals(List.of(object.beneficiariesDTO()), result);
        verify(beneficiaryRepository, times(1)).retrieveAllBeneficiaries();
        verify(beneficiaryMapping, times(1)).mappingBeneficiaries(any(), any());
    }

    @Test
    void testGetBeneficiaryById() {
        when(beneficiaryRepository.retrieveById(anyLong())).thenReturn(object.beneficiaryModel());
        when(beneficiaryMapping.mappingBeneficiaries(any(), any())).thenReturn(object.beneficiariesDTO());
        when(documentService.retrieveDocumentByBeneficiaryId(anyLong())).thenReturn(object.documents());

        BeneficiariesDTO result = beneficiaryServiceImpl.getBeneficiaryById(beneficiaryId);

        Assertions.assertEquals(object.beneficiariesDTO(), result);
        verify(beneficiaryRepository, times(1)).retrieveById(anyLong());
        verify(beneficiaryMapping, times(1)).mappingBeneficiaries(any(), any());
        verify(documentService, times(1)).retrieveDocumentByBeneficiaryId(anyLong());
    }

    @Test
    void testGetBeneficiaryByIdError() {
        when(beneficiaryRepository.retrieveById(anyLong())).thenReturn(null);

        Executable executable = () -> beneficiaryServiceImpl.getBeneficiaryById(beneficiaryId);
        assertThrows(DataBaseException.class, executable);

        verify(beneficiaryRepository, times(1)).retrieveById(anyLong());
    }

    @Test
    void testAddDocument() {
        when(beneficiaryRepository.retrieveById(anyLong())).thenReturn(object.beneficiaryModel());
        when(beneficiaryMapping.mappingToResponseCreate(any(), anyLong())).thenReturn(object.beneficiaryResponseDTO());

        BeneficiaryResponseDTO result = beneficiaryServiceImpl.addDocument(beneficiaryId, object.documentsInclusion());

        Assertions.assertEquals(object.beneficiaryResponseDTO(), result);
        verify(beneficiaryRepository, times(1)).retrieveById(anyLong());
        verify(beneficiaryMapping, times(1)).mappingToResponseCreate(any(), anyLong());
        verify(documentService, times(1)).saveDocument(any(), anyLong());
    }

    @Test
    void testUpdateBeneficiary() {
        when(beneficiaryRepository.retrieveById(anyLong())).thenReturn(object.beneficiaryModel());
        when(beneficiaryMapping.mappingToUpdate(any(), any())).thenReturn(object.beneficiaryModel());
        when(beneficiaryMapping.mappingToResponseUpdate(any(), any())).thenReturn(object.beneficiaryFullResponseDTO());

        BeneficiaryFullResponseDTO result = beneficiaryServiceImpl.updateBeneficiary(beneficiaryId, object.beneficiaryBaseDTO());

        Assertions.assertEquals(object.beneficiaryFullResponseDTO(), result);
        verify(beneficiaryRepository, times(1)).retrieveById(anyLong());
        verify(beneficiaryMapping, times(1)).mappingToUpdate(any(), any());
        verify(beneficiaryMapping, times(1)).mappingToResponseUpdate(any(), any());
    }

    @Test
    void testDeleteBeneficiary() {
        when(beneficiaryRepository.retrieveById(anyLong())).thenReturn(object.beneficiaryModel());
        when(beneficiaryMapping.mappingToResponseDelete(any())).thenReturn(object.beneficiaryResponseDTO());

        BeneficiaryResponseDTO result = beneficiaryServiceImpl.deleteBeneficiary(beneficiaryId);

        Assertions.assertEquals(object.beneficiaryResponseDTO(), result);
        verify(beneficiaryRepository, times(1)).retrieveById(anyLong());
        verify(beneficiaryMapping, times(1)).mappingToResponseDelete(any());
        verify(beneficiaryRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void testReactivateBeneficiary() {
        when(beneficiaryRepository.retrieveByNameIsNotActive(anyString())).thenReturn(object.beneficiaryModel());
        doNothing().when(beneficiaryRepository).reactivateBeneficiary(object.beneficiariesDTO().beneficiaryId());
        when(beneficiaryMapping.mappingToResponseCreate(any(), anyLong())).thenReturn(object.beneficiaryResponseDTO());

        BeneficiaryResponseDTO result = beneficiaryServiceImpl.reactivateBeneficiary(name);

        Assertions.assertEquals(object.beneficiaryResponseDTO(), result);
        verify(beneficiaryRepository, times(1)).retrieveByNameIsNotActive(anyString());
        verify(beneficiaryMapping, times(1)).mappingToResponseCreate(any(), anyLong());
        verify(beneficiaryRepository, times(1)).reactivateBeneficiary(object.beneficiariesDTO().beneficiaryId());
    }

    @Test
    void testReactivateBeneficiaryError() {
        when(beneficiaryRepository.retrieveByNameIsNotActive(anyString())).thenReturn(null);

        Executable executable = () -> beneficiaryServiceImpl.reactivateBeneficiary(name);

        assertThrows(DataBaseException.class, executable);
        verify(beneficiaryRepository, times(1)).retrieveByNameIsNotActive(anyString());
        }
}