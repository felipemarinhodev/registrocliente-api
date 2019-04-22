package br.com.felipemarinho.registrocliente.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.felipemarinho.registrocliente.api.entidade.Mail;
import br.com.felipemarinho.registrocliente.api.repository.MailRepository;
import br.com.felipemarinho.registrocliente.api.response.Response;
import br.com.felipemarinho.registrocliente.api.service.MailService;

@Service
public class MailServiceImpl implements MailService{

	@Autowired
	private MailRepository mailRepository;
	
	@Override
	public List<Mail> salvar(List<Mail> emails) {
		List<Mail> listaDeEmail = new ArrayList<>();
		emails.forEach(email -> {
			Mail mail = this.mailRepository.save(email);
			listaDeEmail.add(mail);
		});
		return listaDeEmail;
	}

	@Override
	public Response<String> remover(String id) {
		Response<String> response = new Response<String>();
		Mail mail = this.mailRepository.findOne(id);
		if (mail == null) {
			response.getErros().add("Registro não foi encontrado id: "+ id);
			return response;
		}
		this.mailRepository.delete(id);
		return new Response<>();
	}

	@Override
	public void removerDoUsuario(Optional<List<Mail>> mails) {
		if (mails.isPresent()) {
			removerMailsDaLista(mails.get());
		}
	}

	private void removerMailsDaLista(List<Mail> mails) {
		mails.forEach(mail -> {
			mailRepository.delete(mail);
		});
	}
	
}
