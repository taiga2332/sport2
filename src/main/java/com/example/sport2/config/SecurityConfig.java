package com.example.sport2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())  // Вимикаємо CSRF для REST API
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/users/**").hasRole("ADMIN")  // Лише адміністратор має доступ
                        .requestMatchers("/attendance/**", "/gyms/**", "/schedule/**", "/subscriptions/**").authenticated()  // Лише автентифіковані
                        .anyRequest().permitAll()  // Дозволяємо всі інші запити
                )
                .httpBasic(withDefaults());  // Використовуємо базову аутентифікацію
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
