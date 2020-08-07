package br.com.alan.cursomc.domain.enums;

public enum EstadoPagamento {

	PENDENTE(1, "Pendente"),
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado");
	
	
	private int codigo;
	private String descricao;

	private EstadoPagamento(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public static EstadoPagamento toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for (EstadoPagamento tipo : EstadoPagamento.values()) {
			if(cod.equals(tipo.getCodigo())) {
				return tipo;
			}
		}
		
		throw new IllegalArgumentException("codigo invalido: "+cod);
	}
}