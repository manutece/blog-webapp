package com.project.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.blog.model.Dislike;
import com.project.blog.model.Post;
import com.project.blog.model.Usuario;

public interface DislikeRepository extends JpaRepository<Dislike, Long> {

	boolean existsByPostAndUsuario(Post post, Usuario usuario);
}