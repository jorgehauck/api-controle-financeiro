package br.com.alura.controlefinanceiro.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.alura.controlefinanceiro.model.Usuario;
import br.com.alura.controlefinanceiro.repositories.UsuarioRepository;

@Component
public class AutenticacaoService implements UserDetailsService {

	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			Optional<Usuario> usuario = usuarioRepository.findByEmail(username);
		
		if(usuario.isPresent()) 
		{
			return usuario.get();
		}
		throw new UsernameNotFoundException("Dados inválidos!");
	}
	
	public UserDetails cadastrarUsuario(Usuario usuario) throws UsernameNotFoundException {
		Optional<Usuario> user = usuarioRepository.findByEmail(usuario.getEmail());
		
		if(user.isEmpty()) 
		{
			Usuario u = new Usuario();
			u.setEmail(usuario.getEmail());
			u.setNome(usuario.getNome());
			u.setSenha(encoder.encode(usuario.getSenha()));
			usuarioRepository.save(u);
			return u;
		}	
		throw new UsernameNotFoundException("Usuário existente!");
	}
}
