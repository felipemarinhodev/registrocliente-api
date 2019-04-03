package br.com.felipemarinho.registrocliente.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.felipemarinho.registrocliente.api.entidade.Endereco;

public interface EnderecoRepository  extends MongoRepository<Endereco, String>{

}
