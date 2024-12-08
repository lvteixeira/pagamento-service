package com.example.pagamentoService.dto;

import com.example.pagamentoService.entidade.Cliente;

import java.io.Serializable;

public class ClienteDTO implements Serializable {
    private Long id;
    private String nome;
    private Long cpf;

    public ClienteDTO() {
    }

    public ClienteDTO(Long id, String nome, Long cpf) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
    }

    public ClienteDTO(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.cpf = cliente.getCpf();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Long getCpf() {
        return cpf;
    }
}
