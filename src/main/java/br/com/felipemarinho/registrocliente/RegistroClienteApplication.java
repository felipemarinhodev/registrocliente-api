package br.com.felipemarinho.registrocliente;

import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.felipemarinho.registrocliente.api.entidade.Usuario;
import br.com.felipemarinho.registrocliente.api.enums.ProfileEnum;
import br.com.felipemarinho.registrocliente.api.repository.UsuarioRepository;

@SpringBootApplication
public class RegistroClienteApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegistroClienteApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			initClientes(usuarioRepository, passwordEncoder);
		};
	}

	private void initClientes(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
		Usuario admin = new Usuario();
		admin.setNomeUsuario("admin");
		admin.setPassword(passwordEncoder.encode("123456"));
		admin.setProfile(ProfileEnum.ROLE_ADMIN);
		
		Usuario comum = new Usuario();
		comum.setNomeUsuario("comum");
		comum.setPassword(passwordEncoder.encode("123456"));
		comum.setProfile(ProfileEnum.ROLE_COMUM);
		
		Optional<Usuario> adminOptional = usuarioRepository.findByNomeUsuario(admin.getNomeUsuario());
		Optional<Usuario> comumOptional = usuarioRepository.findByNomeUsuario(comum.getNomeUsuario());
		if (!adminOptional.isPresent()) {
			usuarioRepository.save(admin);
		}
		if (!comumOptional.isPresent()) {
			usuarioRepository.save(comum);
		}
	}
}
