package br.com.alura.controlefinanceiro.controllers;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.controlefinanceiro.dto.UsuarioDTO;
import br.com.alura.controlefinanceiro.model.Usuario;
import br.com.alura.controlefinanceiro.security.AutenticacaoService;

@RestController
@RequestMapping("/cadastrar")
public class UsuarioController {

	@Autowired
	private AutenticacaoService autenticacaoService;
	
	@PostMapping
	@Transactional
	public ResponseEntity<UsuarioDTO> cadastrarUsuario(@RequestBody UsuarioDTO usuarioDTO)
	{
		Usuario user = usuarioDTO.toUser();
		autenticacaoService.cadastrarUsuario(user);
		return ResponseEntity.ok(new UsuarioDTO(user));
	}
}
