package com.project.blog.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.blog.model.Categoria;
import com.project.blog.model.Post;
import com.project.blog.model.Usuario;
import com.project.blog.service.CategoriaService;
import com.project.blog.service.PostService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class BlogController {

	private final PostService postService;
	private final CategoriaService categoriaService;

	public BlogController(PostService postService, CategoriaService categoriaService) {
		this.postService = postService;
		this.categoriaService = categoriaService;
	}

	@GetMapping("")
	public String homeBlog(@RequestParam(name = "categoria", required = false) String categoria, Model model) {
		List<Categoria> categorias = categoriaService.obtenerTodos();
		model.addAttribute("categorias", categorias);
		
		List<Post> posts;
		if (categoria == null || categoria.equals("Destacados")) {
			posts = postService.buscarDestacados();
			System.out.println(posts);
		} else {
			posts = postService.buscarPorCategoria(categoria);
		}
		System.out.println(posts);
		System.out.println(posts.size());
		
		model.addAttribute("posts", posts);
		return "home";
	}

	@GetMapping("/{nombreUsuario}")
	public String perfilUsuario(Model model, @PathVariable String nombreUsuario) {
		List<Post> posts = postService.buscarPorUsuario(nombreUsuario);
		model.addAttribute("posts", posts);
		return "home";
	}

	@GetMapping("/busqueda")
	public String busquedaPost(Model model, @RequestParam("busqueda") String busqueda,
			@RequestParam("tipo") String tipo) {
		List<Post> posts = new ArrayList<Post>();
		if (tipo.equals("usuario")) {
			posts = postService.buscarPorUsuarioParcial(busqueda);
		} else {
			posts = postService.buscarPorContenido(busqueda);
		}
		model.addAttribute("posts", posts);
		return "home";
	}

}
