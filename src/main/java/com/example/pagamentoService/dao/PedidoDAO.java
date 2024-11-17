package com.example.pagamentoService.dao;

import com.example.pagamentoService.entidade.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoDAO extends JpaRepository<Pedido, Long> {
}
