package com.java.Finsales.CRM.API.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ConverterLeadRequest {

    @NotBlank
    private String documento;
}
