package com.java.Finsales.CRM.API.domain.utils.exceptions.Cliente;

public class ClienteExistenteException extends RuntimeException {
    public ClienteExistenteException(String message) {
        super("O cliente já existe no sistema");
    }
}
