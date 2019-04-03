package br.com.felipemarinho.registrocliente.api.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import br.com.felipemarinho.registrocliente.api.entidade.Cliente;
import br.com.felipemarinho.registrocliente.api.response.Response;

@Component
public interface ClienteService {
	
	Response<String> remover(String id);
	
	Page<Cliente> buscarTodos(int pagina, int quantidade);
	
	Response<Cliente> buscarCliente(String id);
	
	Response<Cliente> adicionar(Cliente cliente);

	Response<Cliente> atualizar(String id, Cliente cliente);

}
