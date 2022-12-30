package com.controle.financeiro.project.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.controle.financeiro.project.dto.ReceitasDTO;
import com.controle.financeiro.project.services.ReceitasService;

@RestController
@RequestMapping("/receitas")
public class ReceitasController {

	@Autowired
	private ReceitasService service;
	
	@PostMapping
	public ResponseEntity<ReceitasDTO> cadastrarReceitas(@RequestBody @Valid ReceitasDTO receitas, UriComponentsBuilder uriBuilder) {
		ReceitasDTO rec = service.cadastrarReceitas(receitas);
		
		URI uri = uriBuilder.path("/receitas").build().toUri();
	
		return ResponseEntity.created(uri).body(rec);
	}
	
	@GetMapping("/listar")
	public ResponseEntity<Page<ReceitasDTO>> listarReceitas(Pageable pageable) {
		Page<ReceitasDTO> receitas = service.getListaReceitas(pageable);
		return ResponseEntity.ok(receitas);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Page<ReceitasDTO>> getDetalhamentoReceitas(@PathVariable Long id, Pageable pageable) {
		Page<ReceitasDTO> receita = service.getDetalhamentoReceitas(id, pageable);
		
		if(receita == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(receita);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ReceitasDTO> atualizarReceita(@PathVariable Long id, @RequestBody @Valid ReceitasDTO receitas) {
		ReceitasDTO receita = service.atualizarReceita(id, receitas);
		
		if(receita == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(receita);
	}
	
	@GetMapping
	public ResponseEntity<Page<ReceitasDTO>> getReceitasDescricao(@RequestParam("descricao") String descricao, Pageable pageable) {
		Page<ReceitasDTO> receita = service.getReceitasDescricao(descricao, pageable);
		
		return ResponseEntity.ok(receita);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletarDespesa(@PathVariable Long id) {
		service.deletarReceita(id);
		
		return ResponseEntity.ok().build();
	}
}
