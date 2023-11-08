package dev.torhugo.ekanrest.util.resource;

import org.springframework.http.ResponseEntity;

import java.util.Objects;

public interface ResourceUtil {

    default <T>ResponseEntity<ResponseUtil<T>> returnResponse(final HttpEnumUtil httpEnumUtil, final T response){
        return ResponseEntity.status(httpEnumUtil.getHttpStatus()).body(new ResponseUtil(httpEnumUtil, response));
    }

    default <T>ResponseEntity<ResponseUtil<T>> returnSuccess(final T response){
        return Objects.nonNull(response) ? this.returnResponse(HttpEnumUtil.HTTP_200, response) : this.returnNoValue();
    }

    default <T> ResponseEntity<ResponseUtil<T>> returnNoValue(){
        return this.returnResponse(HttpEnumUtil.HTTP_204, null);
    }
}
