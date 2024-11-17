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
}
