package com.xbrain_vendasapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class VendedorRelatorioDTO {

    private String nome;
    private Long totalVendas;
    private BigDecimal mediaVendasDiarias;

}
