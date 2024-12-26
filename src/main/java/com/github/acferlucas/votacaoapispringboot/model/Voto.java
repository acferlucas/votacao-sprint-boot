package com.github.acferlucas.votacaoapispringboot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Voto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Pauta pauta;

    @Column(nullable = false)
    private String associadoId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoVoto voto;

    public enum TipoVoto {
        SIM, NAO
    }
}
