package com.java.Finsales.CRM.API.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//Bean é um objeto gerenciado pelo spring. Essa classe registra um objeto de senha criptografada dentro do sistema
@Configuration
public class SecurityBeansConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}