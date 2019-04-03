package br.com.felipemarinho.registrocliente.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.felipemarinho.registrocliente.api.entidade.Telefone;

public interface TelefoneRepository extends MongoRepository<Telefone, String>{

}
