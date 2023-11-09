package dev.torhugo.ekanrest.service.impl;

import dev.torhugo.ekanrest.lib.data.domain.BeneficiaryModel;
import dev.torhugo.ekanrest.lib.data.dto.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class BeneficiaryServiceImplTestObject {
    public BeneficiaryModel beneficiaryModel(){
        return BeneficiaryModel.builder()
                    .beneficiaryId(1L)
                    .name("Test")
                    .phoneNumber("123")
                    .birthDate(LocalDate.of(2023, 11, 1))
                    .inActive(true)
                .build();
    }

    public BeneficiaryResponseDTO beneficiaryResponseDTO(){
        return BeneficiaryResponseDTO.builder()
                    .beneficiaryId(1L)
                    .name("Test")
                    .createdAt(LocalDateTime.of(2023, 11, 1, 1, 1))
                    .updatedAt(null)
                    .links(List.of(linkResponseDTO()))
                .build();
    }

    public BeneficiaryInclusionDTO beneficiaryInclusionDTO(){
        return BeneficiaryInclusionDTO.builder()
                    .name("Test")
                    .phoneNumber("123")
                    .birthDate(LocalDate.of(2023, 11, 1))
                    .lsDocument(documentsInclusion())
                .build();
    }

    public BeneficiariesDTO beneficiariesDTO(){
        return BeneficiariesDTO.builder()
                .beneficiaryId(1L)
                .name("Test")
                .phoneNumber("123")
                .birthDate(LocalDate.of(2023, 11, 1))
                .createdAt(LocalDateTime.of(2023, 11, 1, 1, 1))
                .updatedAt(null)
                .documents(documents())
                .links(List.of(linkResponseDTO()))
                .build();
    }

    public List<DocumentInclusionDTO> documentsInclusion() {
        return List.of(
                DocumentInclusionDTO.builder()
                    .typeDocument("typeDocument")
                    .description("description")
                .build());
    }

    public List<DocumentDTO> documents(){
        return List.of(
                DocumentDTO.builder()
                    .typeDocument("typeDocument")
                    .description("description")
                    .inActive(true)
                    .createdAt(LocalDateTime.of(2023, 11, 1, 1, 1))
                    .updatedAt(null)
                .build()
        );
    }

    public LinkResponseDTO linkResponseDTO(){
        return LinkResponseDTO.builder()
                   .description("description")
                   .method("method")
                   .href("href")
               .build();
    }

    public BeneficiaryFullResponseDTO beneficiaryFullResponseDTO(){
        return BeneficiaryFullResponseDTO.builder()
                   .beneficiaryId(1L)
                   .name("Test")
                   .phoneNumber("123")
                   .birthDate(LocalDate.of(2023, 11, 1))
                   .createdAt(LocalDateTime.of(2023, 11, 1, 1, 1))
                   .updatedAt(LocalDateTime.of(2023, 11, 1, 1, 1))
                   .links(List.of(linkResponseDTO()))
               .build();
    }

    public BeneficiaryBaseDTO beneficiaryBaseDTO(){
        return BeneficiaryBaseDTO.builder()
                  .name("Test")
                  .phoneNumber("123")
                  .birthDate(LocalDate.of(2023, 11, 1))
              .build();
    }
}
