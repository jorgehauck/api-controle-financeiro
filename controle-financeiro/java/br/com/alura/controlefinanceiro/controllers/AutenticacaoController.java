package br.com.alura.controlefinanceiro.controllers;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.controlefinanceiro.dto.TokenDTO;
import br.com.alura.controlefinanceiro.dto.UsuarioDTO;
import br.com.alura.controlefinanceiro.security.TokenService;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<TokenDTO> autenticar(@RequestBody UsuarioDTO usuarioDTO) throws AuthenticationException
	{
		UsernamePasswordAuthenticationToken dadosLogin = usuarioDTO.converter();
		Authentication authentication = authManager.authenticate(dadosLogin);
		String token = tokenService.gerarToken(authentication);
		
		return ResponseEntity.ok(new TokenDTO(token, "Bearer"));
	}
}
