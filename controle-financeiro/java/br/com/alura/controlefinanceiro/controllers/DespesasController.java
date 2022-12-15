package br.com.alura.controlefinanceiro.controllers;

import java.net.URI;
import java.util.List;

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

import br.com.alura.controlefinanceiro.dto.DespesasDTO;
import br.com.alura.controlefinanceiro.services.DespesasService;

@RestController
@RequestMapping("/despesas")
public class DespesasController {

	@Autowired
	private DespesasService despesasService;
	
	@PostMapping
	public ResponseEntity<DespesasDTO> cadastrarDespesas(@RequestBody DespesasDTO despesas, UriComponentsBuilder uriBuilder) {
		DespesasDTO despesasDto = despesasService.cadastrarDespesas(despesas);
		
		URI uri = uriBuilder.path("/despesas").build().toUri();
		
		return ResponseEntity.created(uri).body(despesasDto);
	}
	
	@GetMapping("/lista-despesas")
	public ResponseEntity<Page<DespesasDTO>> getListaDespesas(Pageable pageable) {
		Page<DespesasDTO> despesasDTO = despesasService.getListaDespesas(pageable);
		
		if(despesasDTO.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(despesasDTO);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DespesasDTO> getDetalhamentoDespesas(@PathVariable Long id) {
		DespesasDTO despesasDTO = despesasService.getDetalhamentoDespesas(id);
		
		return ResponseEntity.ok(despesasDTO);
	}
	
	@GetMapping
	public ResponseEntity<List<DespesasDTO>> getDescricaoDespesas(@RequestParam("descricao") String descricao) {
		List<DespesasDTO> despesa = despesasService.getDescricaoDespesas(descricao);
		
		return ResponseEntity.ok(despesa);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<DespesasDTO> atualizarDespesa(@PathVariable Long id, @RequestBody DespesasDTO despesa) {
		DespesasDTO dep = despesasService.atualizarDespesa(id, despesa);
		
		return ResponseEntity.ok(dep);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletarDespesa(@PathVariable Long id) {
		despesasService.deletarDespesa(id);
		
		return ResponseEntity.ok().build();
	}
}
