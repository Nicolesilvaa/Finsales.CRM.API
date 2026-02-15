package com.java.Finsales.CRM.API.domain.utils.exceptions.Cliente;

public class ClienteInativoException extends RuntimeException {
    public ClienteInativoException(String mensage) {
        super("Cliente inativo");
    }
}
