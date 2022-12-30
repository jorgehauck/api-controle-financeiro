package com.controle.financeiro.project.dto;

public class ResumoMesDTO {

	private Double saldoReceitaMes;
	private Double saldoDespesaMes;
	private Double totalAlimentacao;
	private Double totalSaude;
	private Double totalMoradia;
	private Double totalTransporte;
	private Double totalEducacao;
	private Double totalLazer;
	private Double totalImprevistos;
	private Double totalOutrasDespesas;
	private Double saldoFinalMes;
	
	public ResumoMesDTO() {}

	public ResumoMesDTO(Double saldoReceitaMes, Double saldoDespesaMes, Double totalAlimentacao, Double totalSaude,
			Double totalMoradia, Double totalTransporte, Double totalEducacao, Double totalLazer,
			Double totalImprevistos, Double totalOutrasDespesas, Double saldoFinalMes) {
		this.saldoReceitaMes = saldoReceitaMes;
		this.saldoDespesaMes = saldoDespesaMes;
		this.totalAlimentacao = totalAlimentacao;
		this.totalSaude = totalSaude;
		this.totalMoradia = totalMoradia;
		this.totalTransporte = totalTransporte;
		this.totalEducacao = totalEducacao;
		this.totalLazer = totalLazer;
		this.totalImprevistos = totalImprevistos;
		this.totalOutrasDespesas = totalOutrasDespesas;
		this.saldoFinalMes = saldoFinalMes;
	}

	public Double getSaldoReceitaMes() {
		return saldoReceitaMes;
	}

	public void setSaldoReceitaMes(Double saldoReceitaMes) {
		this.saldoReceitaMes = saldoReceitaMes;
	}

	public Double getSaldoDespesaMes() {
		return saldoDespesaMes;
	}

	public void setSaldoDespesaMes(Double saldoDespesaMes) {
		this.saldoDespesaMes = saldoDespesaMes;
	}

	public Double getTotalAlimentacao() {
		return totalAlimentacao;
	}

	public void setTotalAlimentacao(Double totalAlimentacao) {
		this.totalAlimentacao = totalAlimentacao;
	}

	public Double getTotalSaude() {
		return totalSaude;
	}

	public void setTotalSaude(Double totalSaude) {
		this.totalSaude = totalSaude;
	}

	public Double getTotalMoradia() {
		return totalMoradia;
	}

	public void setTotalMoradia(Double totalMoradia) {
		this.totalMoradia = totalMoradia;
	}

	public Double getTotalTransporte() {
		return totalTransporte;
	}

	public void setTotalTransporte(Double totalTransporte) {
		this.totalTransporte = totalTransporte;
	}

	public Double getTotalEducacao() {
		return totalEducacao;
	}

	public void setTotalEducacao(Double totalEducacao) {
		this.totalEducacao = totalEducacao;
	}

	public Double getTotalLazer() {
		return totalLazer;
	}

	public void setTotalLazer(Double totalLazer) {
		this.totalLazer = totalLazer;
	}

	public Double getTotalImprevistos() {
		return totalImprevistos;
	}

	public void setTotalImprevistos(Double totalImprevistos) {
		this.totalImprevistos = totalImprevistos;
	}

	public Double getTotalOutrasDespesas() {
		return totalOutrasDespesas;
	}

	public void setTotalOutrasDespesas(Double totalOutrasDespesas) {
		this.totalOutrasDespesas = totalOutrasDespesas;
	}

	public Double getSaldoFinalMes() {
		return saldoFinalMes;
	}

	public void setSaldoFinalMes(Double saldoFinalMes) {
		this.saldoFinalMes = saldoFinalMes;
	}
}
