package com.java.Finsales.CRM.API.config;

import com.java.Finsales.CRM.API.domain.model.Usuario;
import com.java.Finsales.CRM.API.domain.repository.UsuarioRepository;
import com.java.Finsales.CRM.API.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UsuarioRepository usuarioRepository;

    public JwtAuthenticationFilter(JwtService jwtService,
                                   UsuarioRepository usuarioRepository) {
        this.jwtService = jwtService;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Pega o header Authorization
        String authHeader = request.getHeader("Authorization");

        // Se não tiver Bearer, apenas continua o fluxo
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Remove o "Bearer "
        String token = authHeader.substring(7);

        try {

            String email = jwtService.extrairEmail(token);

            // Só autentica se ainda não estiver autenticado
            if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                Usuario usuario = usuarioRepository.findByEmail(email).orElse(null);

                // Verifica se usuário existe e token é válido
                if (usuario != null && jwtService.tokenValido(token)) {

                    // Converte perfil em ROLE
                    List<GrantedAuthority> authorities =
                            List.of(new SimpleGrantedAuthority(usuario.getPerfil().name()));

                    // Cria objeto de autenticação
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(usuario, null, authorities);

                    // Seta no contexto do Spring
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }

        } catch (Exception e) {
            // Se token for inválido, simplesmente não autentica
        }

        filterChain.doFilter(request, response);
    }
}