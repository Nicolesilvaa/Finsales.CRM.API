package com.java.Finsales.CRM.API.dto.request.Oportunidade;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOportunidadeRequest {

    @NotNull
    private Long clienteId;

    @NotNull
    private Long produtoId;
}
