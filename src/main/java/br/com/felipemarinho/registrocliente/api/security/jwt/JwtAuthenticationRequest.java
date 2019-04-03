package br.com.felipemarinho.registrocliente.api.security.jwt;

import java.io.Serializable;

public class JwtAuthenticationRequest implements Serializable{

	private static final long serialVersionUID = 1L;

	private String nomeUsuario;
	private String password;

	public JwtAuthenticationRequest() {
		super();
	}

	public JwtAuthenticationRequest(String nomeUsuario, String password) {
		super();
		this.nomeUsuario = nomeUsuario;
		this.password = password;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
