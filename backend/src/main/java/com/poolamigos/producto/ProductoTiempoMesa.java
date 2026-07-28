package com.poolamigos.producto;

import com.poolamigos.juego.TipoJuego;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@DiscriminatorValue("TIEMPO_MESA")
public class ProductoTiempoMesa extends Producto {
    @Column (name = "minutos", nullable = false)
    private Integer minutos;

    // A que juego aplica: mesa de pool, ping pong o metegol.
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_juego", nullable = false, length = 20)
    private TipoJuego tipoJuego;

    protected ProductoTiempoMesa() {
        super();
    }
    public ProductoTiempoMesa(String nombre, CategoriaProducto categoria, BigDecimal precio, Integer minutos, TipoJuego tipoJuego) {
        super(nombre, categoria, precio);
        this.minutos = minutos;
        this.tipoJuego = tipoJuego;
    }

    @Override
    public TipoProducto getTipo(){
        return TipoProducto.TIEMPO_MESA;
    }

    @Override
    public Integer getMinutos() {
        return minutos;
    }
    public void setMinutos(Integer minutos) {
        this.minutos = minutos;
    }

    @Override
    public TipoJuego getTipoJuego() {
        return tipoJuego;
    }
    public void setTipoJuego(TipoJuego tipoJuego) {
        this.tipoJuego = tipoJuego;
    }


}
