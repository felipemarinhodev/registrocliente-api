package br.com.felipemarinho.registrocliente.api.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.felipemarinho.registrocliente.api.entidade.Cliente;
import br.com.felipemarinho.registrocliente.api.response.Response;
import br.com.felipemarinho.registrocliente.api.service.ClienteService;

@RestController
@RequestMapping("/api/cliente")
@CrossOrigin(origins = "*")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	@PostMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Cliente>> cadastrar(HttpServletRequest request, @RequestBody @Valid Cliente cliente, BindingResult result) {
		Response<Cliente> response = this.clienteService.adicionar(cliente);
		return ResponseEntity.ok(response);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Cliente>> atualizar(@RequestBody @Valid Cliente cliente, @PathVariable String id) {
		Response<Cliente> response = this.clienteService.atualizar(id, cliente);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Response<Cliente>> pesquisar(@PathVariable String id) {
		Response<Cliente> cliente = this.clienteService.buscarCliente(id);
		if (cliente.temErro()) {
			return ResponseEntity.badRequest().body(cliente);
		}
		return ResponseEntity.ok(cliente);
	}
	
	@GetMapping(value = "{pagina}/{quantidade}")
	public ResponseEntity<Response<Page<Cliente>>> buscarTodos(@PathVariable int pagina, @PathVariable int quantidade) {
		Response<Page<Cliente>> response = new Response<Page<Cliente>>();
		Page<Cliente> clientes = this.clienteService.buscarTodos(pagina, quantidade);
		response.setData(clientes);
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<String>> remover(@PathVariable("id") String id) {
		Response<String> response = this.clienteService.remover(id);
		if (response.temErro()) {
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}
}
