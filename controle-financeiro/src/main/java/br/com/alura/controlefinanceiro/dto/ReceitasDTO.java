package br.com.alura.controlefinanceiro.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import static  java.util.stream.Collectors.toList;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.alura.controlefinanceiro.model.Receitas;

public class ReceitasDTO {
	
	@NotBlank(message = "Campo descrição obrigatório!")
	private String descricao;
	
	@NotNull
	private Double valor;
	
	@NotNull
	private String data;
	
	private List<DespesasDTO> despesas = new ArrayList<>();
	
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public ReceitasDTO() {}
	
	public ReceitasDTO(String descricao, Double valor, String data, List<DespesasDTO> despesas) {
		this.descricao = descricao;
		this.valor = valor;
		this.data = data;
		this.despesas = despesas;
	}
	
	public ReceitasDTO(Receitas receitas) {
		descricao = receitas.getDescricao();
		valor = receitas.getValor();
		data = receitas.getData().format(formatter);
		despesas = receitas.getDespesas().stream().map(x -> new DespesasDTO(x)).collect(toList());
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
	
	public List<DespesasDTO> getDespesas() {
		return despesas;
	}

	public Receitas toReceitas() {
		Receitas receitas = new Receitas();
		receitas.setDescricao(descricao);
		receitas.setValor(valor);
		receitas.setData(LocalDate.parse(data, formatter));
		return receitas;
	}
}
