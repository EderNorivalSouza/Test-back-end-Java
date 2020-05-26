package br.com.xy.inc.poi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ValidationFilter extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public ValidationFilter() {
		super();
	}

	public ValidationFilter(String message) {
		super(message);
	}

}
