package com.java.Finsales.CRM.API.controller;

import com.java.Finsales.CRM.API.domain.utils.exceptions.ClienteExistenteException;
import com.java.Finsales.CRM.API.domain.utils.exceptions.ClienteInativoException;
import com.java.Finsales.CRM.API.domain.utils.exceptions.ClienteNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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

    @ExceptionHandler(ClienteInativoException.class)
    public ResponseEntity<String> clienteInativo(ClienteInativoException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> dadosInvalidos(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
    //Gerado por IA
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> erroValidacao(MethodArgumentNotValidException ex) {
        String mensagem = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .findFirst()
                .orElse("Dados inválidos");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensagem);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> erroJson(HttpMessageNotReadableException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Requisição malformada ou valor inválido");
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> erroGenerico(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }


}
