package br.com.felipemarinho.registrocliente.api.entidade;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.felipemarinho.registrocliente.api.enums.TipoTelefone;

@Document
public class Telefone {
	
	@Id
	private String id;
	
	private String telefone;

	private TipoTelefone tipoTelefone;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public TipoTelefone getTipoTelefone() {
		return tipoTelefone;
	}

	public void setTipoTelefone(TipoTelefone tipoTelefone) {
		this.tipoTelefone = tipoTelefone;
	}
}
