package com.example.sport2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Вимикаємо CSRF для REST API
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/users/**").hasRole("ADMIN")  // Доступ до користувачів тільки для адміністратора
                        .requestMatchers("/attendance/**", "/gyms/**", "/schedule/**", "/subscriptions/**").authenticated()  // Лише автентифіковані користувачі
                        .anyRequest().permitAll()  // Дозволяємо всі інші запити
                )
                .formLogin(form -> form
                        .loginPage("/login")  // Налаштовуємо сторінку входу
                        .defaultSuccessUrl("/main", true)  // Перенаправлення після успішного входу
                        .permitAll()  // Дозволяємо доступ до сторінки входу
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")  // Перенаправлення після виходу
                        .permitAll()
                )
                .httpBasic(httpBasic -> {});  // Дозволяємо базову аутентифікацію для REST API
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Шифрування паролів
    }
}
