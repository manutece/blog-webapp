package com.project.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.blog.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	Optional<Usuario> findByNombre(String nombre);
	
	Usuario findByNombreAndContraseña(String nombre, String contraseña);

}
