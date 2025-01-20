package com.project.blog.service;

import org.springframework.stereotype.Service;

import com.project.blog.model.Usuario;
import com.project.blog.repository.UsuarioRepository;

@Service
public class UsuarioService {
	private UsuarioRepository usuarioRepository;
	
	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;	
	}
	
	public void registrarUsuario(Usuario usuario) {
		usuarioRepository.save(usuario);
	}
	
	public Usuario obtenerUsuarioPorNombre(String nombre) {
		return usuarioRepository.findByNombre(nombre);
	}
	
	public Usuario logear(String nombre, String contrseña) {
		return usuarioRepository.findByNombreAndContraseña(nombre, contrseña);
	}

}
