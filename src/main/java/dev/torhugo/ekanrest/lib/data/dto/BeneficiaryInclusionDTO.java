package dev.torhugo.ekanrest.lib.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record BeneficiaryInclusionDTO(
        String name,
        @JsonProperty("phone_number")
        String phoneNumber,
        @JsonProperty("birth_date")
        LocalDate birthDate,
        @JsonProperty("documents")
        List<DocumentInclusionDTO> lsDocument
) {
}
