package dev.torhugo.ekanrest.lib.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class StandardError implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "UTC")
	private Instant timestamp;
	private Integer status;
	private String identifier;
	private String message;
	private String path;
}
