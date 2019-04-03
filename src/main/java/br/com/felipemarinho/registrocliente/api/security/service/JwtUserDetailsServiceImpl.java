package br.com.felipemarinho.registrocliente.api.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.felipemarinho.registrocliente.api.entidade.Usuario;
import br.com.felipemarinho.registrocliente.api.security.jwt.JwtUserFactory;
import br.com.felipemarinho.registrocliente.api.service.UsuarioService;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioService usuarioService;
	
	@Override
	public UserDetails loadUserByUsername(String nomeUsuario) throws UsernameNotFoundException {
		Optional<Usuario> usuarioOptional = usuarioService.findByNomeUsuario(nomeUsuario);
		
		if (usuarioOptional.isPresent()) {
			return JwtUserFactory.create(usuarioOptional.get());
		} else {
			throw new UsernameNotFoundException(String.format("Não foi encontrado usuário com o nome de usuario: '%s'", nomeUsuario));
		}
	}

}
