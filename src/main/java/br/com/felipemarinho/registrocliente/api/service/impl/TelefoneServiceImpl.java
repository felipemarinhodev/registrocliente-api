package br.com.felipemarinho.registrocliente.api.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.felipemarinho.registrocliente.api.entidade.Telefone;
import br.com.felipemarinho.registrocliente.api.repository.TelefoneRepository;
import br.com.felipemarinho.registrocliente.api.service.TelefoneService;

@Service
public class TelefoneServiceImpl implements TelefoneService{

	@Autowired
	private TelefoneRepository telefoneRepository;
	
	@Override
	public List<Telefone> salvar(List<Telefone> telefones) {
		List<Telefone> listaDeTelefone = new ArrayList<>();
		telefones.forEach(telefone -> {
			Telefone tel = this.telefoneRepository.save(telefone);
			listaDeTelefone.add(tel);
		});
		return listaDeTelefone;
	}

}
