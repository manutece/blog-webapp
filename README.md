#  Blog WebApp  

Bienvenido a **Blog WebApp**, una aplicación inspirada en Twitter donde los usuarios pueden compartir opiniones en distintas categorías, reaccionar con "likes" y "dislikes", y explorar contenido a través de búsquedas.  

##  ¿Por qué este proyecto?  
Este proyecto nació como una práctica para construir aplicaciones con **Spring Boot**, integrando tecnologías esenciales como seguridad, plantillas dinámicas y persistencia de datos.  

##  Tecnologías utilizadas  
- **Backend:** Spring Boot + Maven  
- **Frontend:** Thymeleaf  
- **Seguridad:** Spring Security (manejo de sesiones)  
- **Persistencia:** JPA + PostgreSQL  
- **Estilos y UI:** Bootstrap  

##  Características principales  
- Creación de posts categorizadas  
- Perfil de usuario con listado de sus posts  
- Reacciones de "like" y "dislike" en los posts  
- Búsqueda avanzada por contenido o usuario  
- Autenticación y registro de usuarios  

##  Rutas y Endpoints  

###  Página principal  
 `GET "/"` → Muestra todos los posts publicados.  

###  Gestión de usuarios  
 `GET "/user/registro"` → Formulario de registro de usuario.  
 `POST "/user/registro"` → Crea un nuevo usuario.  
 `GET "/{nombreUsuario}"` → Muestra el perfil del usuario y sus posts.  

###  Publicaciones  
 `GET "/post"` → Formulario para crear un nuevo post.  
 `POST "/post/nuevo"` → Envía los datos para registrar un nuevo post.  

###  Búsqueda  
 `GET "/busqueda?busqueda={texto}&tipo={contenido|usuario}"`  
Permite buscar publicaciones por contenido o por nombre de usuario.  

###  Reacciones  
 `POST "/post/like"` → Agrega un "like" a un post.  
 `POST "/post/dislike"` → Agrega un "dislike" a un post.   

##  Seguridad  
La autenticación se maneja con **Spring Security**, permitiendo sesiones seguras para cada usuario registrado.  

---

