package com.controle.financeiro.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.controle.financeiro.project.dto.ResumoMesDTO;
import com.controle.financeiro.project.exceptionhandler.ResumoMesNotFoundException;
import com.controle.financeiro.project.model.Despesas;
import com.controle.financeiro.project.model.Receitas;
import com.controle.financeiro.project.repositories.DespesasRepository;
import com.controle.financeiro.project.repositories.ReceitasRepository;

@Service
public class ResumoService {

	@Autowired
	private DespesasRepository despesasRepository;
	
	@Autowired
	private ReceitasRepository receitasRepository;

	public ResumoMesDTO resumoMes(Integer ano, Integer mes) {

		Double totalReceitas = 0.0;
		Double totalDespesas = 0.0;
		Double saldoFinalMes = 0.0;
		Double totalAlimentacao = 0.0;
		Double totalSaude = 0.0;
		Double totalMoradia = 0.0;
		Double totalTransporte = 0.0;
		Double totalEducacao = 0.0;
		Double totalLazer = 0.0;
		Double totalImprevistos = 0.0;
		Double totalOutros = 0.0;

		List<Despesas> despesasList = despesasRepository.getResumoDespesas(ano, mes);
		
		for(Despesas desp : despesasList) 
		{
			switch(desp.getCategoria())
			{
				case ALIMENTACAO:
					totalAlimentacao += desp.getValor();
					break;
				
				case SAUDE: 
					totalSaude += desp.getValor();
					break;
					
				case MORADIA: 
					totalMoradia += desp.getValor();
					break;
					
				case TRANSPORTE:
					totalTransporte += desp.getValor();
					break;
					
				case EDUCACAO:
					totalEducacao += desp.getValor();
					break;
					
				case LAZER: 
					totalLazer += desp.getValor();
					break;
					
				case IMPREVISTOS: 
					totalImprevistos += desp.getValor();
					break;
			
				default:
					totalOutros += desp.getValor();
				break;
			}
			
			totalDespesas += desp.getValor();
		}
		
		List<Receitas> resumoReceitasList = receitasRepository.getResumoReceitas(ano, mes);
		
		if(resumoReceitasList.isEmpty())
		{
			throw new ResumoMesNotFoundException();
		}
		
		for(Receitas rec : resumoReceitasList) 
		{
			totalReceitas += rec.getValor();
		}
		
		saldoFinalMes = totalReceitas - totalDespesas;
		
		ResumoMesDTO resumoDto = new ResumoMesDTO();
		resumoDto.setSaldoReceitaMes(totalReceitas);
		resumoDto.setSaldoDespesaMes(totalDespesas);
		resumoDto.setSaldoFinalMes(saldoFinalMes);
		resumoDto.setTotalAlimentacao(totalAlimentacao);
		resumoDto.setTotalSaude(totalSaude);
		resumoDto.setTotalMoradia(totalMoradia);
		resumoDto.setTotalTransporte(totalTransporte);
		resumoDto.setTotalEducacao(totalEducacao);
		resumoDto.setTotalLazer(totalLazer);
		resumoDto.setTotalImprevistos(totalImprevistos);
		resumoDto.setTotalOutrasDespesas(totalOutros);
		
		return resumoDto;
	}
}
