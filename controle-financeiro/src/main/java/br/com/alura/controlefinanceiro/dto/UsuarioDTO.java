package br.com.alura.controlefinanceiro.dto;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import br.com.alura.controlefinanceiro.model.Usuario;

public class UsuarioDTO {

	private String nome;
	private String email;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private String senha;
	
	public UsuarioDTO() {}

	public UsuarioDTO(String nome, String email, String senha) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		
	}
	
	public UsuarioDTO(Usuario usuario) {
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
		this.senha = usuario.getSenha();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public UsernamePasswordAuthenticationToken converter() {
		return new UsernamePasswordAuthenticationToken(email, senha);
	}
	
	public Usuario toUser() {
		Usuario user = new Usuario();
		user.setNome(nome);
		user.setEmail(email);
		user.setSenha(senha);	
		return user;
	}
}
