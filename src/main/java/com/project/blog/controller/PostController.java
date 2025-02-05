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

import com.project.blog.model.Dislike;
import com.project.blog.model.Like;
import com.project.blog.model.Post;
import com.project.blog.model.Usuario;
import com.project.blog.repository.LikeRepository;
import com.project.blog.service.CategoriaService;
import com.project.blog.service.DislikeService;
import com.project.blog.service.LikeService;
import com.project.blog.service.PostService;
import com.project.blog.service.UsuarioService;

@Controller
@RequestMapping("/post")
public class PostController {

	private final CategoriaService categoriaService;
	private final PostService postService;
	private final UsuarioService usuarioService;
	private final LikeService likeService;
	private final DislikeService dislikeService;
	
	public PostController(CategoriaService categoriaService, PostService postService, UsuarioService usuarioService, LikeService likeService, DislikeService dislikeService) {
		this.categoriaService = categoriaService;
		this.postService = postService;
		this.usuarioService = usuarioService;
		this.likeService = likeService;
		this.dislikeService = dislikeService;
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
	
	@PostMapping("/like")
	public String like(@ModelAttribute("postId")Long postId, Principal usuario, RedirectAttributes redirectAttributes) {
		try {
			
			Optional<Usuario> user = usuarioService.obtenerUsuarioPorNombre(usuario.getName());
			if (user.isEmpty()) {
				throw new UsernameNotFoundException("Usuario " + user + " no encontrado.");
			}
			Optional<Post> post = postService.buscarPorId(postId);
			if (post.isEmpty()) {
				throw new Exception("Post no encontrado");
			}
			
			if (likeService.existeLike(user.get(), post.get())) {
				redirectAttributes.addFlashAttribute("alertaLike", "Ya has dado 'Me gusta' al post de " + post.get().getUsuario().getNombre());
				return "redirect:/";
			}
			Like like = new Like();
			like.setUsuario(user.get());
			like.setPost(post.get());
			likeService.guardarLike(like);
			redirectAttributes.addFlashAttribute("alertaLike", "Le has dado 'Me gusta' al post de " + post.get().getUsuario().getNombre());
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("alertaLike", "Error al dar 'Me gusta'");
		}
		return "redirect:/";
	}
	
	@PostMapping("/dislike")
	public String dislike(@ModelAttribute("postId")Long postId, Principal usuario, RedirectAttributes redirectAttributes) {
		try {
			
			Optional<Usuario> user = usuarioService.obtenerUsuarioPorNombre(usuario.getName());
			if (user.isEmpty()) {
				throw new UsernameNotFoundException("Usuario " + user + " no encontrado.");
			}
			Optional<Post> post = postService.buscarPorId(postId);
			if (post.isEmpty()) {
				throw new Exception("Post no encontrado");
			}
			if (dislikeService.existeDislike(post.get(), user.get())) {
				redirectAttributes.addFlashAttribute("alertaDislike", "Ya has dado un 'No me gusta' al post de " + post.get().getUsuario().getNombre());
				return "redirect:/";
			}
			
			Dislike dislike = new Dislike();
			dislike.setUsuario(user.get());
			dislike.setPost(post.get());
			dislikeService.guardarDislike(dislike);
			redirectAttributes.addFlashAttribute("alertaDislike", "Le has dado un 'No me gusta' al post de " + post.get().getUsuario().getNombre());
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("alertaDislike", "Error al dar 'No me gusta'");
		}
		return "redirect:/";
	}
}
