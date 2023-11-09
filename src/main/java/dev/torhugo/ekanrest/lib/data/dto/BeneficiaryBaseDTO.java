package dev.torhugo.ekanrest.lib.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record BeneficiaryBaseDTO(
        String name,
        @JsonProperty("phone_number")
        String phoneNumber,
        @JsonProperty("birth_date")
        LocalDate birthDate
) {
}
