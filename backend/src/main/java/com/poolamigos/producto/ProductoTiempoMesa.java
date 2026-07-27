package com.poolamigos.producto;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@DiscriminatorValue("TIEMPO_MESA")
public class ProductoTiempoMesa extends Producto {
    @Column (name = "minutos", nullable = false)
    private Integer minutos;

    protected ProductoTiempoMesa() {
        super();
    }
    public ProductoTiempoMesa(String nombre, CategoriaProducto categoria, BigDecimal precio, Integer minutos) {
        super(nombre, categoria, precio);
        this.minutos = minutos;
    }

    @Override
    public TipoProducto getTipo(){
        return TipoProducto.TIEMPO_MESA;
    }

    public Integer getMinutos() {
        return minutos;
    }
    public void setMinutos(Integer minutos) {
        this.minutos = minutos;
    }


}
