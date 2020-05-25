package XY.Inc.POI.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ErroValidacaoFiltro extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public ErroValidacaoFiltro() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ErroValidacaoFiltro(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ErroValidacaoFiltro(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
