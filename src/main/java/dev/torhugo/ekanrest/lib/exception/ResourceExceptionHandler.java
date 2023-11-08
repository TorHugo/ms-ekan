package dev.torhugo.ekanrest.lib.exception;

import dev.torhugo.ekanrest.lib.exception.impl.DataBaseException;
import dev.torhugo.ekanrest.lib.exception.impl.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

	private static final HttpStatus notFound = HttpStatus.NOT_FOUND;
	private static final HttpStatus badRequest = HttpStatus.BAD_REQUEST;


	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> entityNotFound(final ResourceNotFoundException exception,
                                                        final HttpServletRequest request){
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(notFound.value());
		err.setIdentifier("Resource not found.");
		err.setMessage(exception.getMessage());
		err.setPath(request.getRequestURI());

		return ResponseEntity.status(notFound).body(err);
	}

	@ExceptionHandler(DataBaseException.class)
	public ResponseEntity<StandardError> dataBase(final DataBaseException exception){
		StandardError err = new StandardError();

		err.setTimestamp(Instant.now());
		err.setStatus(badRequest.value());
		err.setIdentifier(exception.getError().toString());
		err.setMessage(exception.getMessage());
		err.setPath(exception.getMethod()
				.concat(" ")
				.concat("/api/")
				.concat(exception.getHref())
		);

		return ResponseEntity.status(badRequest).body(err);
	}
}
