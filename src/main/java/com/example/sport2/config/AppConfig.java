package com.example.sport2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "com.example.sport2.repository")
@EnableTransactionManagement
public class AppConfig {
    // Цей файл більше не потребує вручну створюваних Bean
    // Spring Boot автоматично налаштує EntityManagerFactory та TransactionManager
}
