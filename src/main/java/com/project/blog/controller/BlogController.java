package com.project.blog.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.blog.model.Post;
import com.project.blog.service.PostService;

@Controller
public class BlogController {

	private final PostService postService;

	public BlogController(PostService postService) {
		this.postService = postService;
	}

	@RequestMapping("/")
	public String homeBlog(Model model) {
		List<Post> destacados = postService.buscarDestacados();
		for (Post p : destacados){
			System.out.println(p);
		}
		model.addAttribute("destacados", destacados);
		return "home";
	}
}
