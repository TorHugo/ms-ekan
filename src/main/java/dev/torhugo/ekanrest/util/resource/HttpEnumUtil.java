package dev.torhugo.ekanrest.util.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum HttpEnumUtil {

    HTTP_200(HttpStatus.OK, HttpStatus.OK.getReasonPhrase()),
    HTTP_201(HttpStatus.CREATED, HttpStatus.CREATED.getReasonPhrase()),
    HTTP_204(HttpStatus.NO_CONTENT, HttpStatus.NO_CONTENT.getReasonPhrase()),
    HTTP_400(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.getReasonPhrase()),
    HTTP_404(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.getReasonPhrase()),
    HTTP_500(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());

    private final HttpStatus httpStatus;
    private final String message;
}
