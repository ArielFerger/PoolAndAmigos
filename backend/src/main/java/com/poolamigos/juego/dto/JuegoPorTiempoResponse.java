package com.poolamigos.juego.dto;

import com.poolamigos.juego.JuegoPorTiempo;
import com.poolamigos.juego.TipoJuego;

public record JuegoPorTiempoResponse(
        Long id,
        TipoJuego tipo,
        Integer numero
) {

    public static JuegoPorTiempoResponse from(JuegoPorTiempo juego) {
        return new JuegoPorTiempoResponse(
                juego.getId(),
                juego.getTipo(),
                juego.getNumero()
        );
    }
}
