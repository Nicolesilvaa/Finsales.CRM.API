package com.java.Finsales.CRM.API.dto.request.Oportunidade;

import com.java.Finsales.CRM.API.domain.utils.enums.StatusOportunidade;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UpdateStatusOportunidadeRequest {
    @NotNull
    private StatusOportunidade status;
}
