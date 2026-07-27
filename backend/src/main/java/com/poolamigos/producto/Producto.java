package com.poolamigos.producto;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "producto")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING, length = 20)
// Estrategia de herencia de tabla única
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // Constructor vacío requerido por JPA

public abstract class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generación automática del ID
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "precio", nullable = false, precision = 12, scale = 2)
    private BigDecimal precio;

    @Column(name = "activo")
    private boolean activo = true;

    @Enumerated(EnumType.STRING) //
    @Column(name = "categoria", nullable = false, length = 20)
    private CategoriaProducto categoria;

    protected Producto(String nombre, CategoriaProducto categoria, BigDecimal precio) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
    }

    public abstract TipoProducto getTipo();

    public abstract Integer getMinutos();

}

