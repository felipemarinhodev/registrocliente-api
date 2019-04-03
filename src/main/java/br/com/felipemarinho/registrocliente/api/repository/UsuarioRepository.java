package br.com.felipemarinho.registrocliente.api.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.felipemarinho.registrocliente.api.entidade.Usuario;

public interface UsuarioRepository extends MongoRepository<Usuario, String>{

	Optional<Usuario> findByNomeUsuario(String nomeUsuario);
	
}
