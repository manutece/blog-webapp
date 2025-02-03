package com.project.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import com.project.blog.service.UsuarioService;

@Configuration
public class ConfigSeguridad{
	
	private final UsuarioService usuarioService;
	private final PasswordEncoder passwordEncoder;
	
	 public ConfigSeguridad(UsuarioService usuarioService, PasswordEncoder passwordEncoder) {
	        this.usuarioService = usuarioService;
	        this.passwordEncoder = passwordEncoder;
	    }
	
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/user/**", "/css/**", "/js/**", "/busqueda/**").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
            	.defaultSuccessUrl("/")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/user/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
            )
            .sessionManagement(session -> session
            		.maximumSessions(1)
            		.maxSessionsPreventsLogin(false)
            		);

        return http.build();
    }
	
	@Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
            .userDetailsService(usuarioService)
            .passwordEncoder(passwordEncoder)
            .and()
            .build();
    }
	
	@Bean 
	public HttpSessionEventPublisher httpSessionEventPublisher() {
		return new HttpSessionEventPublisher();
	}

}
