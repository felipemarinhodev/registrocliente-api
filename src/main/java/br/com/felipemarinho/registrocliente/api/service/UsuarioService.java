package br.com.felipemarinho.registrocliente.api.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import br.com.felipemarinho.registrocliente.api.entidade.Usuario;

@Component
public interface UsuarioService {
	
	void remover(String id);
	
	Page<Usuario> buscarTodos(int pagina, int quantidade);

	Optional<Usuario> findByNomeUsuario(String nomeUsuario);

	Usuario criarOuAtualizar(Usuario usuario);

	Optional<Usuario> buscarPorId(String id);

}
