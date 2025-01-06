package com.project.blog.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "dislikes")
@NoArgsConstructor
@AllArgsConstructor
public class Dislike {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long dislikeId;

	@ManyToOne
	@JoinColumn(name= "postId")
	private Post post;

	@ManyToOne
	@JoinColumn(name = "usuarioId")
	private Usuario usuario;

	private LocalDateTime fechaDislike;
}
