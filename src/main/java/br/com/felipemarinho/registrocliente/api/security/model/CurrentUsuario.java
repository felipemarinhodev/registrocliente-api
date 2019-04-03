package br.com.felipemarinho.registrocliente.api.security.model;

import br.com.felipemarinho.registrocliente.api.entidade.Usuario;

public class CurrentUsuario {
	
	private String token;
	private Usuario usuario;
	
	public CurrentUsuario(String token, Usuario usuario) {
		super();
		this.token = token;
		this.usuario = usuario;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
