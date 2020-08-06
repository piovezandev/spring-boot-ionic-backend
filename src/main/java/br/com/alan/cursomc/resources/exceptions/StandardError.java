package br.com.alan.cursomc.resources.exceptions;

import java.io.Serializable;

public class StandardError implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer status;
	private String mesagem;
	private Long timeStamp;
	public StandardError(Integer status, String mesagem, Long timeStamp) {
		super();
		this.status = status;
		this.mesagem = mesagem;
		this.timeStamp = timeStamp;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getMesagem() {
		return mesagem;
	}
	public void setMesagem(String mesagem) {
		this.mesagem = mesagem;
	}
	public Long getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	
}
