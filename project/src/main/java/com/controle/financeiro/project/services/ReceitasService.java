package com.controle.financeiro.project.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.controle.financeiro.project.dto.ReceitasDTO;
import com.controle.financeiro.project.exceptionhandler.ReceitasDuplicateException;
import com.controle.financeiro.project.exceptionhandler.ReceitasNotFoundException;
import com.controle.financeiro.project.model.Receitas;
import com.controle.financeiro.project.repositories.ReceitasRepository;

@Service
public class ReceitasService {

	@Autowired
	private ReceitasRepository receitasRepository;

	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	@Transactional
	public ReceitasDTO cadastrarReceitas(ReceitasDTO receitas) {
		List<Receitas> descricoesReceitas = receitasRepository.findByDescricao(receitas.getDescricao());
		
		for(Receitas rec: descricoesReceitas) 
		{
			if(rec.getDescricao().equals(receitas.getDescricao())) 
			{
				throw new ReceitasDuplicateException();
			}
		}		
		Receitas rec = receitas.toReceitas();
		receitasRepository.save(rec);
		return new ReceitasDTO(rec);
	}

	public Page<ReceitasDTO> getListaReceitas(Pageable pageable) {
		List<Receitas> receitasList = receitasRepository.getReceitas(pageable);
		
		Page<Receitas> imp = new PageImpl<>(receitasList, pageable, receitasList.size());
		
		return imp.map(x -> new ReceitasDTO(x));
	}

	public Page<ReceitasDTO> getDetalhamentoReceitas(Long id, Pageable pageable) {
		List<Receitas> receitaList = receitasRepository.getDetalhamentoReceitas(id, pageable);
		
		Page<Receitas> imp = new PageImpl<>(receitaList, pageable, receitaList.size());
		
		if(imp.isEmpty()) 
		{
			throw new ReceitasNotFoundException();
		}
		return imp.map(x -> new ReceitasDTO(x));
	}

	@Transactional
	public ReceitasDTO atualizarReceita(Long id, ReceitasDTO receita) {
		Optional<Receitas> rec = receitasRepository.findById(id);

		if(rec.isPresent()) 
		{
			Receitas receitaUpdate = rec.get();
			receitaUpdate.setDescricao(receita.getDescricao());
			receitaUpdate.setValor(receita.getValor());
			receitaUpdate.setData(LocalDate.parse(receita.getData(), formatter));
			receitasRepository.save(receitaUpdate);
			return new ReceitasDTO(receitaUpdate);
		}
		throw new ReceitasNotFoundException();
	}
	
	public Page<ReceitasDTO> getReceitasDescricao(String descricao, Pageable pageable) {
		List<Receitas> receitaList = receitasRepository.getDescricaoReceitas(descricao, pageable);
		
		Page<Receitas> imp = new PageImpl<>(receitaList, pageable, receitaList.size());
		
		if(imp.isEmpty()) 
		{
			throw new ReceitasNotFoundException();
		}
		
		return imp.map(x -> new ReceitasDTO(x));
	}
	
	@Transactional
	public void deletarReceita(Long id) {
		Optional<Receitas> receita = receitasRepository.findById(id);
		
		if(receita.isEmpty()) 
		{
			throw new ReceitasNotFoundException();
		}
		receitasRepository.deleteById(id);
	}
}
