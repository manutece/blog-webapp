package com.project.blog.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.project.blog.model.Like;
import com.project.blog.model.Post;
import com.project.blog.model.Usuario;
import com.project.blog.repository.LikeRepository;

@Service
public class LikeService {
	private final LikeRepository likeRepository;
	
	public LikeService(LikeRepository likeRepository) {
		this.likeRepository = likeRepository;
	}
	
	public Like guardarLike(Like like) {
		like.setFechaLike(LocalDateTime.now());
		return likeRepository.save(like);
	}
	
	public boolean existeLike(Usuario usuario, Post post) {
		return likeRepository.existsByPostAndUsuario(post, usuario);
	}
}
