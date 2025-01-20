package com.project.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.blog.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

}
