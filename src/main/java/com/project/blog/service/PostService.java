package com.project.blog.service;

import java.util.ArrayList;
import java.util.List;

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

	public List<Post> generarData(){
		List<Post> data = new ArrayList<>();

		Usuario luis = new Usuario();
		luis.setNombre("luis suarez");
		luis.setEmail("luchito@gmail.com");
		luis.setContraseña("luisluis123");

		Usuario marcos = new Usuario();
		marcos.setNombre("marcos perez");
		marcos.setEmail("marquitos@gmail.com");
		marcos.setContraseña("marquitos123");

		Post post = new Post();
		post.setCategoria(new Categoria("deporte"));
		post.setContenido("Boca es lo mas grande que hay");
		post.setUsuario(luis);

		Post post2 = new Post();
		post2.setCategoria(new Categoria("deporte"));
		post2.setContenido("River se reforzo mal, gallardo es mierda");
		post2.setUsuario(marcos);

		Post post3 = new Post();
		post3.setCategoria(new Categoria("Cine"));
		post3.setContenido("Interestellar esta lamentablemente sobrevalorada");
		post3.setUsuario(marcos);

		Post post4 = new Post();
		post4.setCategoria(new Categoria("Turismo"));
		post4.setContenido("Bariloche es para negros");
		post4.setUsuario(luis);

		data.add(post);
		data.add(post2);
		data.add(post3);
		data.add(post4);

		return data;
	}

}
