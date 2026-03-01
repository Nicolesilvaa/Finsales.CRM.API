package com.java.Finsales.CRM.API.config;

import com.java.Finsales.CRM.API.domain.model.Usuario;
import com.java.Finsales.CRM.API.domain.repository.UsuarioRepository;
import com.java.Finsales.CRM.API.domain.utils.enums.PerfilUsuario;
import com.java.Finsales.CRM.API.domain.utils.enums.StatusUsuario;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminInitializerConfig {

    @Bean
    public CommandLineRunner criarAdmin(UsuarioRepository repository,
                                        PasswordEncoder encoder) {
        return args -> {

            if (repository.findByEmail("admin@crm.com").isEmpty()) {

                Usuario admin = new Usuario();
                admin.setNome("Admin");
                admin.setEmail("admin@crm.com");
                admin.setSenha(encoder.encode("123456"));
                admin.setPerfil(PerfilUsuario.ADMIN);
                admin.setStatus(StatusUsuario.ATIVO);

                repository.save(admin);
            }
        };
    }
}