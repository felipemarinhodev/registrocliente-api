package br.com.felipemarinho.registrocliente.api.entidade;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Endereco {

	@Id
	private String id;
	
	@NotBlank(message = "O campo CEP é obrigatório.")
	@Size(min = 8)
	private String cep;
	
	@NotBlank(message = "O campo Logradouro é obrigatório.")
	private String logradouro;
	
	@NotBlank(message = "O campo Bairro é obrigatório.")
	private String bairro;
	
	@NotBlank(message = "O campo Cidade é obrigatório.")
	private String cidade;
	
	@NotBlank(message = "O campo Unidade da Federeação é obrigatória.")
	@Size(min = 2, max = 2)
	private String uf;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}
	
	
}
