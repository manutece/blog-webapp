package com.project.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.blog.model.Like;
import com.project.blog.model.Post;
import com.project.blog.model.Usuario;

public interface LikeRepository extends JpaRepository<Like, Long> {
	
	boolean existsByPostAndUsuario(Post post, Usuario usuario);

}
