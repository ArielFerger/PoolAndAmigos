package com.poolamigos.producto.dto;


import com.poolamigos.producto.CategoriaProducto;
import com.poolamigos.producto.TipoProducto;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

import static com.poolamigos.producto.TipoProducto.CONSUMO;
import static com.poolamigos.producto.TipoProducto.TIEMPO_MESA;

// Este es el DTO de entrada: lo que el frontend manda en el body de un POST o PUT
public record ProductoRequest(
        @NotBlank(message = "nombre obligatorio")
        String nombre,
        @NotNull(message = "Categoria obligatoria")
        CategoriaProducto categoria,
        @NotNull(message = "precio obligatorio")
        @DecimalMin(value = "0", message = "no puede ser negativo")
        BigDecimal precio,
        @NotNull(message = "tipo obligatorio")
        TipoProducto tipo,
        Integer minutos
) {
    @AssertTrue(message = "minutos obligatorio y mayor a 0 para TIEMPO_MESA, y no debe enviarse para CONSUMO")
    public boolean isMinutosConsistente() {
        if (tipo == null) {
            return true;
        }
        boolean tieneMinutosValidos = minutos != null && minutos > 0;
        return (tipo == TIEMPO_MESA) == tieneMinutosValidos;
    }
}
