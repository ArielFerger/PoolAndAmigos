package com.poolamigos.producto.dto;

import com.poolamigos.producto.CategoriaProducto;
import com.poolamigos.producto.Producto;
import com.poolamigos.producto.TipoProducto;

import java.math.BigDecimal;

public record ProductoResponse(
        Long id,
        String nombre,
        CategoriaProducto categoria,
        TipoProducto tipo,
        BigDecimal precio,
        Integer minutos,
        boolean activo
) {
    /*
        El metodo estático desde(Producto). Es el patron "factory method": en vez de que el Service arme el new ProductoResponse(...)
        a mano cada vez que necesite convertir una entidad, centralizas esa conversión aca, una sola vez.
        Si mañana el DTO cambia (agregas un campo, sacas otro), solo tocas este metodo, no cada lugar del codigo que construye un ProductoResponse.
    */
    public static ProductoResponse from(Producto producto){ // esta conversion se puede hacer desde el service, pero para ya hacerlo de antemanos lo hacemos aqui,
        return new ProductoResponse(                        // el problema de hacerlo en otros archivos es generarlo repetitivamente al mismo bloque...
                producto.getId(),
                producto.getNombre(),
                producto.getCategoria(),
                producto.getTipo(),
                producto.getPrecio(),
                producto.getMinutos(),
                producto.isActivo()
        );
    }
}
