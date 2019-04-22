package br.com.felipemarinho.registrocliente.api.service;

import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.felipemarinho.registrocliente.api.entidade.Endereco;

@Component
public interface EnderecoService {

	Endereco criarOuAtualizar(Endereco endereco);
	
	Optional<Endereco> findById(String id);
	
	void delete(String id);

	void removerDoUsuario(Optional<Endereco> endereco);
}
