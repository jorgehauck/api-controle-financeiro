package br.com.alura.controlefinanceiro.enums;

public enum Categoria {

	ALIMENTACAO("ALIMENTAÇÃO"),
	SAUDE("SAUDE"),
	MORADIA("MORADIA"),
	TRANSPORTE("TRANSPORTE"),
	EDUCACAO("EDUCACAO"),
	LAZER("LAZER"),
	IMPREVISTOS("IMPREVISTOS"),
	OUTRAS("OUTRAS");
	
	private String categoria;
	
	private Categoria(String categoria) {
		this.categoria = categoria;
	}
	
	public String getCategoria() {
		return this.categoria;
	}
}
