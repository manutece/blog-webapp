package com.project.blog.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.blog.model.Categoria;
import com.project.blog.model.Post;
import com.project.blog.model.Usuario;
import com.project.blog.repository.PostRepository;

@Service
public class PostService {
	
	private final PostRepository postRepository;
	
	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}
	
	public List<Post> buscarTodos(){
		return postRepository.findAll();
	}
	
	public List<Post> buscarDestacados(){
		return postRepository.buscarDestacados();
	}
	
	public List<Post> buscarPorUsuario(String nombreUsuario){
		return postRepository.buscarPorUsuario(nombreUsuario);
	}
	
	public List<Post> buscarPorUsuarioParcial(String nombreUsuario){
		return postRepository.buscarPorUsuarioParcial(nombreUsuario);
	}
	
	public List<Post> buscarPorContenido(String contenido){
		return postRepository.buscarPorContenido(contenido);
	}
	
	public List<Post> buscarPorCategoria(String categoria){
		return postRepository.buscarPorCategoria(categoria);
	}
	
	public Optional<Post> buscarPorId(Long postId) {
		return postRepository.findById(postId);
	}

	public Post guardarPost(Post post) {
		post.setFechaPost(LocalDateTime.now());
		return postRepository.save(post);
	}

}
