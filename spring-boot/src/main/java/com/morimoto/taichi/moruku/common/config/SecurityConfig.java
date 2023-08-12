package com.morimoto.taichi.moruku.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}") 
  private String jwkSetUri; 

  private String firebaseUid;

  @Bean
  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
      .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/api/**").authenticated()
                .anyRequest().permitAll()
      ).oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults())
      ).csrf(csrf -> csrf.disable());;
    return http.build();
  }

  @Bean 
  JwtDecoder jwtDecoder() { 
    JwtDecoder jwtDecoder = NimbusJwtDecoder
                              .withJwkSetUri(jwkSetUri)
                              .build(); 
    return new JwtDecoder() {
      @Override 
      public Jwt decode (String token)  throws JwtException { 
        Jwt  jwt  = jwtDecoder.decode(token); 
        firebaseUid = (String) jwt.getClaims().get("user_id");
        return jwt;
      } 
    }; 
  }

  public String getFirebaseUid() {
    return firebaseUid;
  }
  
}
