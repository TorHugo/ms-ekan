package dev.torhugo.ekanrest.lib.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record DocumentInclusionDTO(
        @JsonProperty("type_document")
        String typeDocument,
        String description
) {
}
