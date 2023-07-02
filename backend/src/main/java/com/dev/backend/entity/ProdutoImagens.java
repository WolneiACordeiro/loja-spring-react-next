package com.dev.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "produto_imagens")
@Data
public class ProdutoImagens {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;

    @Column(length = 1000)
    private String caminhoImagem;

}
