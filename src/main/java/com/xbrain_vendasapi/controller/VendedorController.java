package com.xbrain_vendasapi.controller;

import com.xbrain_vendasapi.dto.VendedorRelatorioDTO;
import com.xbrain_vendasapi.service.VendaService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/vendedores")
@AllArgsConstructor
public class VendedorController {

    private VendaService vendaService;

    @GetMapping("/relatorio")
    public List<VendedorRelatorioDTO> relatorio(
            //datas inciais e finais do periodo
            @RequestParam LocalDate dataInicio,
            @RequestParam LocalDate dataFim
    ) {
        //gera o relatorio com base no periodo informado
        return vendaService.relatorio(dataInicio, dataFim);
    }
}
