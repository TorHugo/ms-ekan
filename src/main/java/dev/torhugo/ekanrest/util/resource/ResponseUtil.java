package dev.torhugo.ekanrest.util.resource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseUtil<T> {
    private LocalDateTime timeStamp;
    private Integer numberStatus;
    private transient T response;
    private String message;

    public ResponseUtil(final HttpEnumUtil httpEnumUtil, final T data){
        this.timeStamp = LocalDateTime.now();
        this.numberStatus = httpEnumUtil.getHttpStatus().value();
        this.message = httpEnumUtil.getMessage();
        this.response = data;
    }
}
