package com.project.blog.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.blog.model.Post;
import com.project.blog.model.Usuario;
import com.project.blog.service.CategoriaService;
import com.project.blog.service.PostService;
import com.project.blog.service.UsuarioService;

@Controller
@RequestMapping("/postear")
public class PostController {

	private final CategoriaService categoriaService;
	private final PostService postService;
	private final UsuarioService usuarioService;
	
	public PostController(CategoriaService categoriaService, PostService postService, UsuarioService usuarioService) {
		this.categoriaService = categoriaService;
		this.postService = postService;
		this.usuarioService = usuarioService;
	}
	
	@GetMapping("")
	public String postForm(Model model, Principal usuario) {
		model.addAttribute("categorias", categoriaService.obtenerTodos());
		
		List<Post> posts = postService.buscarPorUsuario(usuario.getName());
		model.addAttribute("posts", posts);
		model.addAttribute("post", new Post());
		return "postform";
	}
	
	@PostMapping("/nuevo")
	public String postNuevo(@ModelAttribute("post") Post post, @ModelAttribute("username") String username, RedirectAttributes redirectAttributes) {
		try {
				Optional<Usuario> usuario = usuarioService.obtenerUsuarioPorNombre(username);
				if (usuario.isEmpty()) {
					throw new UsernameNotFoundException("Usuario " + username + " no encontrado.");
				}
				  post.setUsuario(usuario.get());
			      postService.guardarPost(post);
		    } catch (Exception e) {
		      redirectAttributes.addAttribute("mensaje", e.getMessage());
		    }
		System.out.println(post);
		return "redirect:/" + username;
	}
}
