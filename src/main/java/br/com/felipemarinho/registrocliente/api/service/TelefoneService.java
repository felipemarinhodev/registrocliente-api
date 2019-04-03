package br.com.felipemarinho.registrocliente.api.service;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.felipemarinho.registrocliente.api.entidade.Telefone;

@Component
public interface TelefoneService {

	List<Telefone> salvar(List<Telefone> telefones);
	
}
