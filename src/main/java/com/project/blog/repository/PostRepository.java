package com.project.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.blog.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
	
	@Query(value = "select * from posts where post_id in (select post_id from likes group by post_id order by count(*) limit 10)", nativeQuery = true)
	List<Post> buscarDestacados();
	
	@Query(value = "select p from Post p where p.usuario.nombre = ?1")
	List<Post> buscarPorUsuario(String nombreUsuario);
	
	@Query(value = "select p from Post p where p.usuario.nombre like %?1%")
	List<Post> buscarPorUsuarioParcial(String nombreUsuario);
	
	@Query(value = "select p from Post p where p.contenido like %?1%")
	List<Post> buscarPorContenido(String contenido);
	
	List<Post> findByContenidoContaining(String contenido);
}
