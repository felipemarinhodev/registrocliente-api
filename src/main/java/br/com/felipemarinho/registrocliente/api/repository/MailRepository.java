package br.com.felipemarinho.registrocliente.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.felipemarinho.registrocliente.api.entidade.Mail;

public interface MailRepository extends MongoRepository<Mail, String>{

	
}
