package com.xbrain_vendasapi.repository;

import com.xbrain_vendasapi.entity.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface VendaRepository extends JpaRepository<Venda, Long> {

    //lista de vendas por periodo
    List<Venda> findByDataVendaBetween(LocalDate inicio, LocalDate fim);
}
