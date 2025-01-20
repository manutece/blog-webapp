package com.project.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.blog.model.Usuario;
import com.project.blog.service.UsuarioService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UsuarioController {
	private UsuarioService usuarioService;
	
	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	@GetMapping("/registro")
	public String mostrarRegistroForm(Model model) {
		model.addAttribute("nuevoUsuario", new Usuario());
		return "registro";
	}
	
	@PostMapping("/registrar")
	public String registrarUsuario(@ModelAttribute Usuario usuario) {
		usuarioService.registrarUsuario(usuario);
		return "redirect:/login";
	}
	
	@GetMapping("/login")
	public String mostrarLoginForm(Model model) {
		return "login";
	}
	
	@PostMapping("/logear")
	public String autenticarUsuario(@RequestParam String nombre, @RequestParam String contraseña, HttpSession sesion, Model model) {
		Usuario usuario = usuarioService.logear(nombre, contraseña);
		
		if (usuario != null) {
			sesion.setAttribute("user", usuario);
			return "redirect:/";
		} else {
			model.addAttribute("error", "Nombre de usuario o contraseña incorrectos");
			return "login";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession sesion) {
		sesion.invalidate();
		return "redirect:/";
	}

}
