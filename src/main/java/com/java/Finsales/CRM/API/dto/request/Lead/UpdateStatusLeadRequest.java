package com.java.Finsales.CRM.API.dto.request.Lead;

import com.java.Finsales.CRM.API.domain.utils.enums.StatusLead;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UpdateStatusLeadRequest {

    @NotNull
    private StatusLead status;

}
