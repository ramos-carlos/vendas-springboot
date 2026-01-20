package com.xbrain_vendasapi.controller;

import com.xbrain_vendasapi.dto.VendaRequestDTO;
import com.xbrain_vendasapi.entity.Venda;
import com.xbrain_vendasapi.service.VendaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vendas")
@AllArgsConstructor
public class VendaController {

    private VendaService vendaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)//201
    public Venda criarVenda(@RequestBody @Valid VendaRequestDTO dto) {//ativa as validações do DTO
        //chama o service para salvar
        return vendaService.criarVenda(dto);
    }
}
