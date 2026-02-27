package com.java.Finsales.CRM.API.service;

import com.java.Finsales.CRM.API.domain.model.Usuario;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

import static javax.crypto.Cipher.SECRET_KEY;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private Long expiration;

    public String gerarToken(Usuario usuario) {

        return Jwts.builder()
                .setSubject(usuario.getEmail())
                .claim("id", usuario.getId())
                .claim("perfil", usuario.getPerfil().name())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // expira em 1h
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    //Senha(String) -> bytes -> senha criptografada
    private Key getSignInKey() {
        byte[] keyBytes = secretKey.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extrairEmail(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean tokenValido(String token) {

        try {

            Jwts.parserBuilder()
                    .setSigningKey(getSignInKey())
                    .build()
                    .parseClaimsJws(token); //Valida token

            return true;

        } catch (ExpiredJwtException e) {
            System.out.println("Token expirado");
        } catch (UnsupportedJwtException e) {
            System.out.println("Token não suportado");
        } catch (MalformedJwtException e) {
            System.out.println("Token malformado");
        } catch (SignatureException e) {
            System.out.println("Assinatura inválida");
        } catch (IllegalArgumentException e) {
            System.out.println("Token vazio");
        }

        return false;
    }
}
