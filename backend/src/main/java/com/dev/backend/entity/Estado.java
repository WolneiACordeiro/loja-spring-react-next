package com.dev.backend.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "estado")
@Data
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;
    private String sigla;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dataCriacao;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dataAtualizacao;

}
