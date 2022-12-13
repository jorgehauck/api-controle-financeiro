package br.com.alura.controlefinanceiro.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.alura.controlefinanceiro.dto.DespesasDTO;
import br.com.alura.controlefinanceiro.enums.Categoria;
import br.com.alura.controlefinanceiro.exceptionhandler.DespesasDuplicateException;
import br.com.alura.controlefinanceiro.exceptionhandler.DespesasNotFoundException;
import br.com.alura.controlefinanceiro.model.Despesas;
import br.com.alura.controlefinanceiro.model.Receitas;
import br.com.alura.controlefinanceiro.repositories.DespesasRepository;
import br.com.alura.controlefinanceiro.repositories.ReceitasRepository;

@Service
public class DespesasService {

	@Autowired
	private ReceitasRepository receitasRepository;

	@Autowired
	private DespesasRepository despesasRepository;

	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	@Transactional
	public DespesasDTO cadastrarDespesas(DespesasDTO despesas) {
		Receitas receita = receitasRepository.findById(despesas.getReceitaId()).get();
		List<Despesas> dep = despesasRepository.findByDescricao(despesas.getDescricao());

		for (Despesas obj : dep) {
			if (obj.getDescricao().equals(despesas.getDescricao())) {
				throw new DespesasDuplicateException();
			}
		}

		Despesas despesa = despesas.toDespesas();
		despesa.setReceitas(receita);
		receita.getDespesas().add(despesa);

		if (despesas.getCategoria() == null) {
			despesa.setCategoria(Categoria.OUTRAS);
			receitasRepository.save(receita);
		}
		despesa.setCategoria(despesas.getCategoria());
		receitasRepository.save(receita);
		return new DespesasDTO(despesa);
	}

	public Page<DespesasDTO> getListaDespesas(Pageable pageable) {
		Page<Despesas> despesas = despesasRepository.findAll(pageable);
		Page<DespesasDTO> despesasDto = despesas.map(DespesasDTO::new);
		return despesasDto;
	}

	public DespesasDTO getDetalhamentoDespesas(Long id) {
		Optional<Despesas> despesas = despesasRepository.findById(id);

		if (despesas.isPresent()) {
			return new DespesasDTO(despesas.get());
		}
		throw new DespesasNotFoundException();
	}

	public List<DespesasDTO> getDescricaoDespesas(String descricao) {
		System.out.println(descricao);
		List<Despesas> despesa = despesasRepository.getDescricaoDespesas(descricao);
		List<DespesasDTO> despesaDTO = despesa.stream().map(dep -> new DespesasDTO(dep)).collect(Collectors.toList());

		for (Despesas dep : despesa) {
			if (dep.getDescricao() == null) {
				throw new DespesasNotFoundException();
			}
		}
		return despesaDTO;
	}

	@Transactional
	public DespesasDTO atualizarDespesa(Long id, DespesasDTO despesa) {
		Optional<Despesas> despesas = despesasRepository.findById(id);

		if (despesas.isPresent()) {
			Despesas dep = despesas.get();
			dep.setDescricao(despesa.getDescricao());
			dep.setValor(despesa.getValor());
			dep.setData(LocalDate.parse(despesa.getData(), formatter));
			despesasRepository.save(dep);
			return new DespesasDTO(dep);
		}
		throw new DespesasNotFoundException();
	}

	@Transactional
	public void deletarDespesa(Long id) {
		Optional<Despesas> despesa = despesasRepository.findById(id);

		if (despesa.isEmpty()) {
			throw new DespesasNotFoundException();
		}
		despesasRepository.deleteById(id);
	}
}
