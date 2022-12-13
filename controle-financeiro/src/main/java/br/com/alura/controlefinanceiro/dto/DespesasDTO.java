package br.com.alura.controlefinanceiro.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import br.com.alura.controlefinanceiro.enums.Categoria;
import br.com.alura.controlefinanceiro.model.Despesas;

public class DespesasDTO {

	@NotBlank
	private String descricao;
	
	@NotNull
	private Double valor;
	
	@NotNull
	private String data;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private Long receitaId;
	
	private Categoria categoria;
	
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public DespesasDTO() {}

	public DespesasDTO(String descricao, Double valor, String data, Long receitaId, Categoria categoria) {
		this.descricao = descricao;
		this.valor = valor;
		this.data = data.formatted(formatter);
		this.categoria = categoria;
	}
	
	public DespesasDTO(Despesas despesas) {
		this.descricao = despesas.getDescricao();
		this.valor = despesas.getValor();
		this.data = despesas.getData().format(formatter);
		this.receitaId = despesas.getReceitas().getId();
		this.categoria = despesas.getCategoria();
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	public Long getReceitaId() {
		return receitaId;
	}

	public void setReceitaId(Long receitaId) {
		this.receitaId = receitaId;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Despesas toDespesas() {
		Despesas despesas = new Despesas();
		despesas.setDescricao(descricao);
		despesas.setValor(valor);
		despesas.setData(LocalDate.parse(data, formatter));
		return despesas;
	}
}
