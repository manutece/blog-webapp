CREATE TABLE usuarios (
    usuarioId INT PRIMARY KEY AUTO_INCREMENT,
    nombreUsuario VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    contraseña VARCHAR(255) NOT NULL,
    fechaRegistro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE categorias (
    categoriaId INT PRIMARY KEY AUTO_INCREMENT,
    nombreCategoria VARCHAR(50) NOT NULL
);

CREATE TABLE posts (
    postID INT PRIMARY KEY AUTO_INCREMENT,
    usuarioId INT,
    categoriaId INT,
    contenido TEXT NOT NULL,
    fechaPost TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (usuarioId) REFERENCES usuarios(usuarioId),
    FOREIGN KEY (categoriaId) REFERENCES categorias(categoriaId)
);

CREATE TABLE likes (
    likeID INT PRIMARY KEY AUTO_INCREMENT,
    postID INT,
    usuarioID INT,
    fechaLike TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (postId) REFERENCES posts(postId),
    FOREIGN KEY (usuarioId) REFERENCES usuarios(usuarioId)
);

CREATE TABLE dislikes (
    dislikeId INT PRIMARY KEY AUTO_INCREMENT,
    postId INT,
    usuarioId INT,
    fechaDislike TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (postId) REFERENCES posts(postId),
    FOREIGN KEY (usuarioId) REFERENCES usuarios(usuarioId)
);
