package com.xbrain_vendasapi.service;

import com.xbrain_vendasapi.dto.VendaRequestDTO;
import com.xbrain_vendasapi.dto.VendedorRelatorioDTO;
import com.xbrain_vendasapi.entity.Venda;
import com.xbrain_vendasapi.repository.VendaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class VendaService {

    private VendaRepository vendaRepository;

    //cria uma nova venda a partir de um DTO de requisição
    public Venda criarVenda(VendaRequestDTO dto) {
        Venda venda = new Venda();
        venda.setDataVenda(dto.getDataVenda());
        venda.setValor(dto.getValor());
        venda.setVendedorId(dto.getVendedorId());
        venda.setVendedorNome(dto.getVendedorNome());

        //salva a venda no banco e retorna o objeto persistido
        return vendaRepository.save(venda);
    }

    //listar relatorio de vendas por vendedor em um periodo especifico
    public List<VendedorRelatorioDTO> relatorio(LocalDate inicio, LocalDate fim) {

        //busca todas as vendas do periodo informado
        List<Venda> vendas = vendaRepository.findByDataVendaBetween(inicio, fim);

        //calcula o numero de total de dias no periodo
        long diasPeriodo = ChronoUnit.DAYS.between(inicio, fim) + 1;

        //agrupando as vendas pelo nome do vendedor
        Map<String, List<Venda>> vendasPorVendedor =
                vendas.stream().collect(Collectors.groupingBy(Venda::getVendedorNome));

        List<VendedorRelatorioDTO> resultado = new ArrayList<>();

        //para cada vendedor, calcula o total de vendas e sua media diaria
        for (Map.Entry<String, List<Venda>> entry : vendasPorVendedor.entrySet()) {
            String nome = entry.getKey();
            long totalVendas = entry.getValue().size();

            //media diaria de vendas no periodo
            BigDecimal mediaVendasDiarias = BigDecimal.valueOf(totalVendas)
                    .divide(BigDecimal.valueOf(diasPeriodo), 2, RoundingMode.HALF_UP);

            // cria a DTO e add na lista de resultados
            resultado.add(new VendedorRelatorioDTO(nome, totalVendas, mediaVendasDiarias));
        }
        return resultado;
    }
}
