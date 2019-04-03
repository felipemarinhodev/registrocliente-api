package br.com.felipemarinho.registrocliente.api.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.felipemarinho.registrocliente.api.entidade.Usuario;
import br.com.felipemarinho.registrocliente.api.response.Response;
import br.com.felipemarinho.registrocliente.api.service.UsuarioService;

@RestController
@RequestMapping("/api/usu")
@CrossOrigin(origins = "*")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Usuario>> criar(HttpServletRequest request, @RequestBody Usuario usuario, BindingResult result) {
		Response<Usuario> response = new Response<Usuario>();
		try {
			validarCPFCliente(usuario, result);
			if(result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
			Usuario clientePersiste = (Usuario) this.usuarioService.criarOuAtualizar(usuario);
			response.setData(clientePersiste);
		} catch (DuplicateKeyException dE) {
			response.getErros().add("CPF já registrado!");
			return ResponseEntity.badRequest().body(response);
		} catch (Exception e) {
			response.getErros().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}
	
	@PutMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Usuario>> atualizar(HttpServletRequest request, @RequestBody Usuario usuario, BindingResult result) {
		Response<Usuario> response = new Response<Usuario>();
		try {
			validarAtualizacaoCliente(usuario, result);
			if(result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			} 
			usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
			Usuario clientePersiste = (Usuario) this.usuarioService.criarOuAtualizar(usuario);
			response.setData(clientePersiste);
		} catch (Exception e) {
				response.getErros().add(e.getMessage());
				return ResponseEntity.badRequest().body(response);
			}
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "{id}")
	public ResponseEntity<Response<Usuario>> buscarPorId(@PathVariable("id") String id) {
		Response<Usuario> response = new Response<Usuario>();
		Optional<Usuario> clienteOptional = this.usuarioService.buscarPorId(id);
		if (!clienteOptional.isPresent()) {
			response.getErros().add("Registro não foi encontrado id: "+ id);
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(clienteOptional.get());
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "{page}/{count}")
	public ResponseEntity<Response<Page<Usuario>>> buscarTodos(@PathVariable int page, @PathVariable int count) {
		Response<Page<Usuario>> response = new Response<Page<Usuario>>();
		Page<Usuario> usuarios = this.usuarioService.buscarTodos(page, count);
		response.setData(usuarios);
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping(value = "{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Usuario>> remover(@PathVariable("id") String id) {
		Response<Usuario> response = new Response<Usuario>();
		Optional<Usuario> clienteOptional = this.usuarioService.buscarPorId(id);
		if (!clienteOptional.isPresent()) {
			response.getErros().add("Registro não foi encontrado id: "+ id);
			return ResponseEntity.badRequest().body(response);
		}
		this.usuarioService.remover(id);
		return ResponseEntity.ok(new Response<>());
	}
	
		
	private void validarAtualizacaoCliente(Usuario usuario, BindingResult result) {
		if(usuario.getId() == null) {
			result.addError(new ObjectError("Cliente", "Id não informado"));
		}
		this.validarCPFCliente(usuario, result);
	}
	
	private void validarCPFCliente(Usuario usuario, BindingResult result) {
		if(usuario.getNomeUsuario() == null) {
			result.addError(new ObjectError("Cliente", "CPF não informado"));
		}
	}
}
