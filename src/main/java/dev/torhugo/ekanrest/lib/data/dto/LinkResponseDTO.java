package dev.torhugo.ekanrest.lib.data.dto;

import lombok.Builder;

@Builder
public record LinkResponseDTO(
        String description,
        String method,
        String href
) {
}
