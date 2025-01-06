package com.project.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.blog.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
	
	@Query(value = "select * from posts where post_id in (select post_id from likes group by post_id order by count(*) limit 10)", nativeQuery = true)
	List<Post> buscarDestacados();
}
