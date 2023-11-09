package dev.torhugo.ekanrest.util;

import dev.torhugo.ekanrest.lib.data.dto.LinkResponseDTO;
import org.springframework.http.HttpMethod;

public class MethodUtil {

    private MethodUtil() {}

    public static LinkResponseDTO buildToGet(final String description,
                                             final String path,
                                             final String identifier){
        return LinkResponseDTO.builder()
                .description(description)
                .method(HttpMethod.GET.name())
                .href("/api/".concat(path).concat(identifier))
                .build();
    }

    public static LinkResponseDTO buildToPost(final String description,
                                             final String path,
                                             final String identifier){
        return LinkResponseDTO.builder()
                .description(description)
                .method(HttpMethod.POST.name())
                .href("/api/".concat(path).concat(identifier))
                .build();
    }

    public static LinkResponseDTO buildToPut(final String description,
                                              final String path,
                                              final String identifier){
        return LinkResponseDTO.builder()
                .description(description)
                .method(HttpMethod.PUT.name())
                .href("/api/".concat(path).concat(identifier))
                .build();
    }

}
