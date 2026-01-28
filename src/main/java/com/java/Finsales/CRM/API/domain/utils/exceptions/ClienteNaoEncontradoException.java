package com.java.Finsales.CRM.API.domain.utils.exceptions;

public class ClienteNaoEncontradoException extends RuntimeException {
    public ClienteNaoEncontradoException(String message) {
        super("Cliente não encontrado");
    }
}
