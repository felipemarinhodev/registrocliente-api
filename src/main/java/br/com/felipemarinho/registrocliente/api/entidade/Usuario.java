package br.com.felipemarinho.registrocliente.api.entidade;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.felipemarinho.registrocliente.api.enums.ProfileEnum;

@Document
public class Usuario {

	@Id
	private String id;
	
	@Indexed(unique = true)
	@NotBlank(message = "Nome de usuario é obrigatório.")
	private String nomeUsuario;
	
	@NotBlank(message = "Senha obrigatória.")
	@Size(min = 6)
	private String password;
	
	private ProfileEnum profile;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public ProfileEnum getProfile() {
		return profile;
	}

	public void setProfile(ProfileEnum profile) {
		this.profile = profile;
	}

}
