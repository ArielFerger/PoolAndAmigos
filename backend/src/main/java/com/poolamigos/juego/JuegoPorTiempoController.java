package com.poolamigos.juego;

import com.poolamigos.juego.dto.JuegoPorTiempoResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/juegos")
public class JuegoPorTiempoController {

    private final JuegoPorTiempoService juegoPorTiempoService;

    public JuegoPorTiempoController(JuegoPorTiempoService juegoPorTiempoService) {
        this.juegoPorTiempoService = juegoPorTiempoService;
    }

    @GetMapping
    public List<JuegoPorTiempoResponse> listar(
            @RequestParam(name = "tipo", required = false) TipoJuego tipo) {
        return juegoPorTiempoService.listar(tipo);
    }
}
