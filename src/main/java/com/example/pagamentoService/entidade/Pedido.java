package com.example.pagamentoService.entidade;

import jakarta.persistence.*;

@Entity
@Table(name = "PEDIDOS")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NUM_SEQ_PEDIDO")
    private Long id;

    @Column(name = "DESCR_PEDIDO", length = 100)
    private String descricao;

    @Column(name = "NUM_ITEM")
    private Integer numItem;

    @Column(name = "VALOR_ITEM")
    private Double valorItem;
}
