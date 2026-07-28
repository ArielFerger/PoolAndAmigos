package com.poolamigos.juego;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Un objeto fisico del bar que se alquila por tiempo: una mesa de pool,
 * un ping pong o un metegol. Todos tienen la misma estructura, por eso
 * es una sola entidad con un enum y no una jerarquia de clases.
 */
@Entity
@Table(name = "juego_por_tiempo")
@Getter
@Setter
public class JuegoPorTiempo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false, length = 20)
    private TipoJuego tipo;

    /** Unico por tipo: puede haber "Mesa 1" y tambien "Ping Pong 1". */
    @Column(name = "numero", nullable = false)
    private Integer numero;

    protected JuegoPorTiempo() {
    }

    public JuegoPorTiempo(TipoJuego tipo, Integer numero) {
        this.tipo = tipo;
        this.numero = numero;
    }
}
