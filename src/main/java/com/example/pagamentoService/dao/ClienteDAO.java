package com.example.pagamentoService.dao;

import com.example.pagamentoService.entidade.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteDAO extends JpaRepository<Cliente, Long> {
}
