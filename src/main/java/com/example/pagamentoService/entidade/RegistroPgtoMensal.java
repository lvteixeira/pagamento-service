package com.example.pagamentoService.entidade;

import jakarta.persistence.*;

import java.security.Timestamp;

@Entity
@Table(name = "REGISTRO_PGTO_MENSAL")
public class RegistroPgtoMensal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NUM_SEQ_RGPGTO")
    private Long id;

    @Column(name = "DESCR_RGPGTO", length = 100)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "NUM_CLIENTE")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "NUM_TIPO_PGTO")
    private TipoPgto tipoPgto;

    @Column(name = "DATAHORA_RGPGTO")
    private Timestamp dataHora;

    @ManyToOne
    @JoinColumn(name = "NUM_PEDIDO")
    private Pedido pedido;

    // Getters and Setters
}
