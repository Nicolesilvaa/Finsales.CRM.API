package com.java.Finsales.CRM.API.dto.request;

import com.java.Finsales.CRM.API.domain.utils.EstadoCliente;
import com.java.Finsales.CRM.API.domain.utils.TipoCliente;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateClienteRequest {

    @NotBlank
    @Size(min = 3, max = 100)
    private String nome;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String telefone;

    @NotNull
    private EstadoCliente status;
}
