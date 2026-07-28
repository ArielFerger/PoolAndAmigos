package com.poolamigos.juego;

import com.poolamigos.juego.dto.JuegoPorTiempoResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class JuegoPorTiempoService {

    private final JuegoPorTiempoRepository juegoPorTiempoRepository;

    public JuegoPorTiempoService(JuegoPorTiempoRepository juegoPorTiempoRepository) {
        this.juegoPorTiempoRepository = juegoPorTiempoRepository;
    }

    /** Si tipo es null devuelve todos los juegos; si no, solo los de ese tipo. */
    public List<JuegoPorTiempoResponse> listar(TipoJuego tipo) {
        List<JuegoPorTiempo> juegos;
        if (tipo == null) {
            juegos = juegoPorTiempoRepository.findAll();
        } else {
            juegos = juegoPorTiempoRepository.findByTipo(tipo);
        }
        return juegos.stream().map(JuegoPorTiempoResponse::from).toList();
    }
}
