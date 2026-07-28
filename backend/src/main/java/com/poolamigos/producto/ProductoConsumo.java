package com.poolamigos.producto;

import com.poolamigos.juego.TipoJuego;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity
@DiscriminatorValue("CONSUMO")
public class ProductoConsumo extends Producto {

    protected ProductoConsumo() {
        super();
    }

    public ProductoConsumo(String nombre, CategoriaProducto categoria, BigDecimal precio) {
        super(nombre, categoria, precio);
    }

    @Override
    public TipoProducto getTipo() {
        return TipoProducto.CONSUMO;
    }

    @Override
    public Integer getMinutos() {
        return null;
    }

    @Override
    public TipoJuego getTipoJuego() {
        return null;
    }
}
