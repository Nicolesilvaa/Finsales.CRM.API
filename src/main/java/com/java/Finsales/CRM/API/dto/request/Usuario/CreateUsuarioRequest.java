package com.java.Finsales.CRM.API.dto.request.Usuario;

import com.java.Finsales.CRM.API.domain.utils.enums.PerfilUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateUsuarioRequest {

    @NotBlank
    @Size(min = 3, max = 100)
    private String nome;

    @NotBlank
    private String documento;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String senha;

}
