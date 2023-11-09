package dev.torhugo.ekanrest.lib.data.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder
public record BeneficiaryFullResponseDTO(
        @JsonProperty("beneficiary_id")
        Long beneficiaryId,
        @JsonProperty("name")
        String name,
        @JsonProperty("phone_number")
        String phoneNumber,
        @JsonProperty("birth_date")
        LocalDate birthDate,
        @JsonProperty("created_at")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm", timezone = "UTC")
        LocalDateTime createdAt,
        @JsonProperty("updated_at")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm", timezone = "UTC")
        LocalDateTime updatedAt,
        @JsonProperty("_links")
        List<LinkResponseDTO> links
) {

}
