package br.com.felipemarinho.registrocliente.api.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.felipemarinho.registrocliente.api.entidade.Endereco;
import br.com.felipemarinho.registrocliente.api.repository.EnderecoRepository;
import br.com.felipemarinho.registrocliente.api.service.EnderecoService;

@Service
public class EnderecoServiceImpl implements EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Override
	public Endereco criarOuAtualizar(Endereco endereco) {
		return this.enderecoRepository.save(endereco);
	}

	@Override
	public Optional<Endereco> findById(String id) {
		return this.findById(id);
	}

	@Override
	public void delete(String id) {
		this.enderecoRepository.delete(id);
	}

	@Override
	public void removerDoUsuario(Optional<Endereco> endereco) {
		if (endereco.isPresent()) {
			enderecoRepository.delete(endereco.get());
		}
	}

}
