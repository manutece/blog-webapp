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

import com.project.blog.model.Post;
import com.project.blog.service.PostService;

@Controller
@RequestMapping("/")
public class BlogController {

	private final PostService postService;

	public BlogController(PostService postService) {
		this.postService = postService;
	}

	@GetMapping("")
	public String homeBlog(Model model) {
		List<Post> destacados = postService.buscarDestacados();
		model.addAttribute("posts", destacados);
		return "home";
	}
	
	@GetMapping("/{nombreUsuario}")
	public String perfilUsuario(Model model, @PathVariable String nombreUsuario) {
		List<Post> posts = postService.buscarPorUsuario(nombreUsuario);
		model.addAttribute("posts", posts);
		return "home";
	}
	
	@GetMapping("/busqueda")
	public String busquedaPost(Model model, @RequestParam("busqueda") String busqueda, @RequestParam("tipo") String tipo) {
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
