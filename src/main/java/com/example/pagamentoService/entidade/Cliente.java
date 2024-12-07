package com.example.pagamentoService.entidade;

import jakarta.persistence.*;

@Entity
@Table(name = "CLIENTES")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NUM_SEQ_CLIENTE")
    private Long id;

    @Column(name = "NOME", length = 100)
    private String nome;

    @Column(name = "CPF")
    private Long cpf;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

}
