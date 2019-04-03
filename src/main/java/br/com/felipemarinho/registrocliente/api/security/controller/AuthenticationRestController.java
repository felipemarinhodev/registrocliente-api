package br.com.felipemarinho.registrocliente.api.security.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.felipemarinho.registrocliente.api.entidade.Usuario;
import br.com.felipemarinho.registrocliente.api.security.jwt.JwtAuthenticationRequest;
import br.com.felipemarinho.registrocliente.api.security.jwt.JwtTokenUtil;
import br.com.felipemarinho.registrocliente.api.security.model.CurrentUsuario;
import br.com.felipemarinho.registrocliente.api.service.UsuarioService;

@RestController
@CrossOrigin(origins = "*")
public class AuthenticationRestController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private UsuarioService usuarioService;


	@PostMapping(value="/api/auth")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) {
		final UserDetails userDetails = this.userDetailsService.loadUserByUsername(authenticationRequest.getNomeUsuario());
		final String token = this.jwtTokenUtil.generateToken(userDetails);
		Optional<Usuario> UsuarioOptinal = usuarioService.findByNomeUsuario(authenticationRequest.getNomeUsuario());
		if (UsuarioOptinal.isPresent()) {
			final Usuario usuario = UsuarioOptinal.get();
			usuario.setPassword(null);
			return ResponseEntity.ok(new CurrentUsuario(token, usuario));	
		}
		return ResponseEntity.badRequest().body(null);
	}
	
	@PostMapping(value="/api/refresh")
	public ResponseEntity<?>  refreshAndGetAuthenticationToken(HttpServletRequest request) {
		final String token = request.getHeader("Authorization");
		String cpf = jwtTokenUtil.refreshToken(token);
		Optional<Usuario> usuarioOptional = usuarioService.findByNomeUsuario(cpf);
		if (jwtTokenUtil.canTokenBeRefreshed(token) && usuarioOptional.isPresent()) {
			String refreshToken = jwtTokenUtil.refreshToken(token);
			return ResponseEntity.ok(new CurrentUsuario(refreshToken, usuarioOptional.get()));
		}
		else {
			return ResponseEntity.badRequest().body(null);
		}
	}
}
