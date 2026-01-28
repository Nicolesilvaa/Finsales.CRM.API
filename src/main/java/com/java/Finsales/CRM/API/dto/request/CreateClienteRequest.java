package com.java.Finsales.CRM.API.dto.request;

import com.java.Finsales.CRM.API.domain.utils.enums.TipoCliente;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;



@Getter
@Setter
@NoArgsConstructor
public class CreateClienteRequest {

    @NotBlank
    @Size(min = 3, max = 100)
    private String nome;

    @NotBlank
    private String documento;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String telefone;

    @NotNull
    private TipoCliente tipoCliente;

}
