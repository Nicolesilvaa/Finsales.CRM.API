package com.java.Finsales.CRM.API.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UpdateEmailLeadRequest {

    @NotBlank
    @Email
    private String email;
}
