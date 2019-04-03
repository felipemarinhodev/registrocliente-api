package br.com.felipemarinho.registrocliente.api.service;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.felipemarinho.registrocliente.api.entidade.Mail;
import br.com.felipemarinho.registrocliente.api.response.Response;

@Component
public interface MailService {

	List<Mail> salvar(List<Mail> emails);

	Response<String> remover(String id);
	
}
