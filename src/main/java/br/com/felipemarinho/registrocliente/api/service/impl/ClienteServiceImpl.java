package br.com.felipemarinho.registrocliente.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.felipemarinho.registrocliente.api.entidade.Cliente;
import br.com.felipemarinho.registrocliente.api.entidade.Endereco;
import br.com.felipemarinho.registrocliente.api.entidade.Mail;
import br.com.felipemarinho.registrocliente.api.entidade.Telefone;
import br.com.felipemarinho.registrocliente.api.repository.ClienteRepository;
import br.com.felipemarinho.registrocliente.api.repository.EnderecoRepository;
import br.com.felipemarinho.registrocliente.api.response.Response;
import br.com.felipemarinho.registrocliente.api.service.ClienteService;
import br.com.felipemarinho.registrocliente.api.service.EnderecoService;
import br.com.felipemarinho.registrocliente.api.service.MailService;
import br.com.felipemarinho.registrocliente.api.service.TelefoneService;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private TelefoneService telefoneService;
	
	@Autowired 
	private EnderecoService enderecoService;
	
	@Override
	public Response<String> remover(String id) {
		Response<String> response = new Response<String>();
		Cliente cliente = this.clienteRepository.findOne(id);
		if (cliente == null) {
			response.getErros().add("Registro não foi encontrado id: "+ id);
			return response;
		}
		
		removerVinculosDoCliente(cliente);
		this.clienteRepository.delete(id);
		return new Response<>();
	}

	private void removerVinculosDoCliente(Cliente cliente) {
		this.mailService.removerDoUsuario(Optional.of(cliente.getMails()));
		this.telefoneService.removerDoUsuario(Optional.of(cliente.getTelefones()));
		this.enderecoService.removerDoUsuario(Optional.of(cliente.getEndereco()));
	}

	@Override
	public Page<Cliente> buscarTodos(int pagina, int quantidade) {
		Pageable page = new PageRequest(pagina, quantidade);
		return this.clienteRepository.findAll(page);
	}

	@Override
	public Response<Cliente> buscarCliente(String id) {
		Response<Cliente> response = new Response<Cliente>();
		Cliente cliente = this.clienteRepository.findOne(id);
		if (cliente == null) {
			response.getErros().add("Registro não foi encontrado id: "+ id);
			return response;
		}
		response.setData(cliente);
		return response;
	}

	@Override
	public Response<Cliente> adicionar(Cliente cliente) {
		Response<Cliente> response = new Response<Cliente>();
		persistirEndereco(cliente);
		persistirMails(cliente);
		persistirTelefone(cliente);
		Cliente clientePersiste = this.clienteRepository.save(cliente);
		response.setData(clientePersiste);
		return response;
	}

	private void persistirEndereco(Cliente cliente) {
		Endereco endereco = this.enderecoRepository.save(cliente.getEndereco());
		cliente.setEndereco(endereco);
	}

	private void persistirMails(Cliente cliente) {
		List<Mail> mails = this.mailService.salvar(cliente.getMails());
		cliente.setMails(mails);
	}

	private void persistirTelefone(Cliente cliente) {
		List<Telefone> telefones = this.telefoneService.salvar(cliente.getTelefones());
		cliente.setTelefone(telefones);
	}

	@Override
	public Response<Cliente> atualizar(String id, Cliente cliente) {
		Response<Cliente> response = new Response<Cliente>();
		Cliente clienteSalvo = this.clienteRepository.findOne(id);
		BeanUtils.copyProperties(cliente, clienteSalvo, "id");
		response.setData(this.clienteRepository.save(clienteSalvo));
		return response;
	}

}
