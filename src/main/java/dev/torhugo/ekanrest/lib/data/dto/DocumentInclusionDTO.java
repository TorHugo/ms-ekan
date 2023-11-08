package dev.torhugo.ekanrest.lib.data.dto;

import lombok.Builder;

@Builder
public record DocumentInclusionDTO(
        String typeDocument,
        String description
) {
}
