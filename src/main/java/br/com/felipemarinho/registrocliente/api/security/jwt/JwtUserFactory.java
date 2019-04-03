package br.com.felipemarinho.registrocliente.api.security.jwt;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import br.com.felipemarinho.registrocliente.api.entidade.Usuario;
import br.com.felipemarinho.registrocliente.api.enums.ProfileEnum;

public class JwtUserFactory {
	
	private JwtUserFactory() {
		
	}
	
	public static JwtUser create(Usuario usuario) {
		return new JwtUser(usuario.getId(), usuario.getNomeUsuario(), usuario.getPassword(), mapToGrantedAuthorities(usuario.getProfile()));
	}

	private static List<GrantedAuthority> mapToGrantedAuthorities(ProfileEnum profile) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(profile.toString()));
		return authorities;
	}

}
