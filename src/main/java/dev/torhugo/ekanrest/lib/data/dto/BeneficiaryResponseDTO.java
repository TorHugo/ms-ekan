package dev.torhugo.ekanrest.lib.data.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder
public record BeneficiaryResponseDTO(
        @JsonProperty("beneficiary_id")
        Long beneficiaryId,
        @JsonProperty("name")
        String name,
        @JsonProperty("created_at")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "UTC")
        LocalDateTime createdAt,
        @JsonProperty("_links")
        List<LinkResponseDTO> links
) {

}
