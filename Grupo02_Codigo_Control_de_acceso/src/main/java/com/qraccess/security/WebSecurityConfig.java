package com.qraccess.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.qraccess.security.filters.JwtRequestFilter;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Autowired;

@Configuration
public class WebSecurityConfig {

	  @Autowired
	  private JwtRequestFilter jwtRequestFilter;

  @Bean
  SecurityFilterChain web(HttpSecurity http) throws Exception {
    http
    	.csrf().disable()
        .authorizeHttpRequests((authorize) -> authorize
          //.requestMatchers("/public/**").permitAll()	
        .requestMatchers("/login", "/signin").permitAll()
        .requestMatchers("/events/**").permitAll()
        .requestMatchers("/admin/**").hasRole("ADMIN")
        .requestMatchers("/customer/**").hasRole("CUSTOMER")
        .anyRequest().authenticated()
        )
        .cors(withDefaults())
        .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
        .sessionManagement((session) -> session
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );
    ;
    return http.build();
  }
  

  @Bean
  AuthenticationManager authenticationManager(AuthenticationConfiguration
      authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }
  /* (1) By default, Spring Security form login/http basic auth are enabled.
  However, as soon as any servlet-based configuration is provided,
  form based login or/and http basic auth must be explicitly provided.

  * (2) If our stateless API uses token-based authentication, such as JWT,
    we don't need CSRF protection
  */
  
  @Bean
  PasswordEncoder passwordEncoder() {
	  return new BCryptPasswordEncoder();
  }
}