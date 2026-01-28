package com.java.Finsales.CRM.API.controller;

import com.java.Finsales.CRM.API.domain.utils.exceptions.ClienteExistenteException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ClienteControllerAdvice {

    @ControllerAdvice
    public class ClienteControllerAdvice {

        @ExceptionHandler(ClienteExistenteException.class)
        public ResponseEntity<String> clienteExistente(ClienteExistenteException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }

        @ExceptionHandler(ClienteNaoEncontradoException.class)
        public ResponseEntity<String> clienteNaoEncontrado(ClienteNaoEncontradoException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }

        @ExceptionHandler(IllegalArgumentException.class)
        public ResponseEntity<String> dadosInvalidos(IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }

        @ExceptionHandler(Exception.class)
        public ResponseEntity<String> erroGenerico(Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno inesperado");
        }
    }

}
