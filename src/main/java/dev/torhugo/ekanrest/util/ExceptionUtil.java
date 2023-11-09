package dev.torhugo.ekanrest.util;

import dev.torhugo.ekanrest.lib.exception.impl.DataBaseException;

public class ExceptionUtil {

    private ExceptionUtil(){

    }

    public static void throwException(final String message,
                                      final String identifier,
                                      final String path,
                                      final String method){
        throw new DataBaseException(message, identifier, path, method);
    }
}
