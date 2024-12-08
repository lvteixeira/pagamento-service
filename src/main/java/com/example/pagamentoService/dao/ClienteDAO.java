package com.example.pagamentoService.dao;

import com.example.pagamentoService.entidade.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClienteDAO extends JpaRepository<Cliente, Long> {
    @Query("SELECT c FROM Cliente c")
    List<Cliente> listarClientes();
}