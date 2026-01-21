package com.xbrain_vendasapi.service;

import com.xbrain_vendasapi.dto.VendedorRelatorioDTO;
import com.xbrain_vendasapi.entity.Venda;
import com.xbrain_vendasapi.repository.VendaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.boot.test.context.SpringBootTest;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@Transactional
public class VendaServiceTest {

    @Autowired
    private VendaService vendaService;

    @Autowired
    private VendaRepository vendaRepository;

    @Test
    void deveCalcularRelatorioCorretamente() {

        // Criando vendas fictícias para simular no banco - vendedor "Carlos"
        Venda venda1 = new Venda(null, LocalDate.of(2026, 1, 2),
                new BigDecimal("100"), 1L, "Carlos");

        Venda venda2 = new Venda(null, LocalDate.of(2026, 1, 4),
                new BigDecimal("150"), 1L, "Carlos");

        vendaRepository.saveAll(List.of(venda1, venda2));

        // Intervalo de datas usado no relatório
        LocalDate dataInicio = LocalDate.of(2026, 1, 1);
        LocalDate dataFim = LocalDate.of(2026, 1, 5);

        // Metodo que gera o relatório de vendas por vendedor
        List<VendedorRelatorioDTO> relatorio =
                vendaService.relatorio(dataInicio, dataFim);

        // Garante que apenas um vendedor foi retornado no relatório
        assertEquals(1, relatorio.size());

        // Recupera os dados do vendedor
        VendedorRelatorioDTO dto = relatorio.get(0);

        // Valida se os cálculos/dados do relatório estão corretos
        assertEquals("Carlos", dto.getNome());
        assertEquals(2L, dto.getTotalVendas());
        assertEquals(new BigDecimal("0.40"), dto.getMediaVendasDiarias());
    }
}
