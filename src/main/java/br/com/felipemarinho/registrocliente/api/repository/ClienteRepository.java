package br.com.felipemarinho.registrocliente.api.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.felipemarinho.registrocliente.api.entidade.Cliente;

public interface ClienteRepository extends MongoRepository<Cliente, String>{
	
	Optional<Cliente> findByCpf(String cpf);
}
