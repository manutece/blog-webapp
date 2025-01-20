package com.project.blog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.blog.model.Categoria;
import com.project.blog.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	private final CategoriaRepository categoriaRepository;
	
	public CategoriaService(CategoriaRepository categoriaRepository) {
		this.categoriaRepository = categoriaRepository;
	}
	
	public List<Categoria> obtenerTodos(){
		return categoriaRepository.findAll();
	}
}
