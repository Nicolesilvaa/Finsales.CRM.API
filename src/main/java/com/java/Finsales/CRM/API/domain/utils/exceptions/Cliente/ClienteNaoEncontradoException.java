package com.java.Finsales.CRM.API.domain.utils.exceptions.Cliente;

public class ClienteNaoEncontradoException extends RuntimeException {
    public ClienteNaoEncontradoException(String message) {
        super("Cliente não encontrado");
    }
}
