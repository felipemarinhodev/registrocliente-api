package br.com.felipemarinho.registrocliente.api.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.felipemarinho.registrocliente.api.entidade.Usuario;
import br.com.felipemarinho.registrocliente.api.repository.UsuarioRepository;
import br.com.felipemarinho.registrocliente.api.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public void remover(String id) {
		this.usuarioRepository.delete(id);
	}

	@Override
	public Page<Usuario> buscarTodos(int pagina, int quantidade) {
		Pageable page = new PageRequest(pagina, quantidade);
		return this.usuarioRepository.findAll(page);
	}

	@Override
	public Optional<Usuario> findByNomeUsuario(String cpf) {
		Optional<Usuario> clienteOptional = usuarioRepository.findByNomeUsuario(cpf);
		return clienteOptional;
	}

	@Override
	public Usuario criarOuAtualizar(Usuario usuario) {
		return this.usuarioRepository.save(usuario);
	}

	@Override
	public Optional<Usuario> buscarPorId(String id) {
		return Optional.of(this.usuarioRepository.findOne(id));
	}

}
