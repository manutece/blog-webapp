package com.project.blog.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.blog.model.Post;
import com.project.blog.service.CategoriaService;
import com.project.blog.service.PostService;

@Controller
@RequestMapping("/postear")
public class PostController {

	private final CategoriaService categoriaService;
	private final PostService postService;
	
	public PostController(CategoriaService categoriaService, PostService postService) {
		this.categoriaService = categoriaService;
		this.postService = postService;
	}
	
	@GetMapping("")
	public String postForm(Model model) {
		model.addAttribute("categorias", categoriaService.obtenerTodos());
		List<Post> destacados = postService.buscarPorUsuario("anaruiz");
		model.addAttribute("posts", destacados);
		model.addAttribute("post", new Post());
		return "postform";
	}
	
	@PostMapping("/nuevo")
	public String postNuevo(@ModelAttribute("post") Post post, RedirectAttributes redirectAttributes) {
		try {
		      postService.guardarPost(post);

		      redirectAttributes.addFlashAttribute("mensaje", "Posteo exitoso!");
		    } catch (Exception e) {
		      redirectAttributes.addAttribute("mensaje", e.getMessage());
		    }
		System.out.println(post);
		return "redirect:/";
	}
}
