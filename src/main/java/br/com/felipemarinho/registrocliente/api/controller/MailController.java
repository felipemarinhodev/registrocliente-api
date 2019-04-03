package br.com.felipemarinho.registrocliente.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.felipemarinho.registrocliente.api.response.Response;
import br.com.felipemarinho.registrocliente.api.service.MailService;

@RestController
@RequestMapping("/api/mail")
public class MailController {

	@Autowired
	private MailService mailService;
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<String>> remover(@PathVariable("id") String id) {
		Response<String> response = this.mailService.remover(id);
		if (response.temErro()) {
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}
	
}
