package com.xbrain_vendasapi.repository;

import com.xbrain_vendasapi.entity.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendaRepository extends JpaRepository<Venda, Long> {
}
