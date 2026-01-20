package com.xbrain_vendasapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VendaRequestDTO {

    @NotNull(message = "A data da venda é obrigatória")
    private LocalDate dataVenda;

    @NotNull(message = "O valor da venda é obrigatório")
    @Positive(message = "O valor da venda deve ser maior que zero")
    private BigDecimal valor;

    @NotNull(message = "O vendedor é obrigatório")
    private Long vendedorId;

    @NotBlank(message = "O nome do vendedor é obrigatório")
    private String vendedorNome;
}
