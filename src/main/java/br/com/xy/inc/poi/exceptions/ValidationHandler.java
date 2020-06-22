package br.com.xy.inc.poi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ValidationHandler {

	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, Object> handle(MethodArgumentNotValidException exception) {
		Map <String, Object> errors = exception.getBindingResult()
				.getFieldErrors()
				.stream()
				.collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
		return errors;
	}
}

//	@Autowired
//	private MessageSource messageSource;
//Tratamento de erro com List depende de outra classe chamada FormErrorDto
//		List <FormErrorDto> dto = new ArrayList<>();
//		List <FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
//		fieldErrors.forEach(e ->{
//			String message = messageSource.getMessage(e, LocaleContextHolder.getLocale());
//			FormErrorDto erro = new FormErrorDto(e.getField(), message);
//			dto.add(erro);
//		});