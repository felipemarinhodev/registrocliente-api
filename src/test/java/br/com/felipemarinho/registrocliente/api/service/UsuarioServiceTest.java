package br.com.felipemarinho.registrocliente.api.service;

import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import br.com.felipemarinho.registrocliente.api.repository.UsuarioRepository;

public class UsuarioServiceTest {

	@InjectMocks
	private UsuarioService usuarioService;
	
	@Mock
	private UsuarioRepository usuarioRepository;
	
	@Test
	public void test_Autenticar_com_cpf_invalido() {
		String cpf = "52297812892";
		assertNull(usuarioService.findByNomeUsuario(cpf));
	}

}
