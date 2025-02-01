package com.project.blog.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.blog.model.Usuario;
import com.project.blog.repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService{
	private UsuarioRepository usuarioRepository;
	private PasswordEncoder passwordEncoder;
	
	public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
		this.usuarioRepository = usuarioRepository;	
		this.passwordEncoder = passwordEncoder;
	}
	
	public void registrarUsuario(Usuario usuario) {
		usuario.setContraseña(passwordEncoder.encode(usuario.getContraseña()));
		usuarioRepository.save(usuario);
	}
	
	public Optional<Usuario> obtenerUsuarioPorNombre(String nombre) {
		return usuarioRepository.findByNombre(nombre);
	}
	
	public Usuario logear(String nombre, String contrseña) {
		return usuarioRepository.findByNombreAndContraseña(nombre, contrseña);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("Buscando usuario: " + username);
		Usuario usuario = usuarioRepository.findByNombre(username)
				.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
		
		return User.withUsername(usuario.getNombre())
				.password(usuario.getContraseña())
				.roles("USER")
				.build();
	}

}
