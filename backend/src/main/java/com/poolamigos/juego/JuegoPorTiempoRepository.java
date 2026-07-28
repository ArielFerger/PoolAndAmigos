package com.poolamigos.juego;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JuegoPorTiempoRepository extends JpaRepository<JuegoPorTiempo, Long> {

    List<JuegoPorTiempo> findByTipo(TipoJuego tipo);
}
