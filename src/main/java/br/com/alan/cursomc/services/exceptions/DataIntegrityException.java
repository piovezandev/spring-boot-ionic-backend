package br.com.alan.cursomc.services.exceptions;

public class DataIntegrityException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public DataIntegrityException(String msg) {
		super(msg);
	}

	public DataIntegrityException(String msg, Throwable rootCause) {
		super(msg, rootCause);
	}
	
}
