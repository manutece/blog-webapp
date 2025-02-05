package com.project.blog.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.project.blog.model.Dislike;
import com.project.blog.model.Like;
import com.project.blog.model.Post;
import com.project.blog.model.Usuario;
import com.project.blog.repository.DislikeRepository;

@Service
public class DislikeService {
	private final DislikeRepository dislikeRepository;
	
	public DislikeService(DislikeRepository dislikeRepository) {
		this.dislikeRepository = dislikeRepository;
	}
	
	public Dislike guardarDislike(Dislike dislike) {
		dislike.setFechaDislike(LocalDateTime.now());
		return dislikeRepository.save(dislike);
	}
	
	public boolean existeDislike(Post post, Usuario usuario) {
		return dislikeRepository.existsByPostAndUsuario(post, usuario);
	}
}
