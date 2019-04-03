package br.com.felipemarinho.registrocliente.api.entidade;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Cliente {
	
	@Id
	private String id;
	
	@NotBlank(message = "Nome do usuario é obrigatório.")
	private String nome;
	
	@Indexed(unique = true)
	@NotBlank(message = "Nome de usuario é obrigatório.")
	@CPF(message = "Favor informa o CPF. ex: xxx.xxx.xxx-xx.")
	private String cpf;
	
	@DBRef(lazy = false)
	private Endereco endereco;
	
	@DBRef(lazy = false)
	private List<Telefone> telefones;
	
	@DBRef(lazy = false)
	private List<Mail> mails;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<Mail> getMails() {
		return mails;
	}

	public void setMails(List<Mail> mails) {
		this.mails = mails;
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefone(List<Telefone> telefones) {
		this.telefones = telefones;
	}
}
