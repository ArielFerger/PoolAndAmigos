CREATE TABLE producto (
    id        BIGSERIAL PRIMARY KEY,
    tipo      VARCHAR(20)    NOT NULL,
    nombre    VARCHAR(100)   NOT NULL,
    categoria VARCHAR(20)    NOT NULL,
    precio    NUMERIC(12, 2) NOT NULL,
    minutos   INTEGER,
    activo    BOOLEAN        NOT NULL DEFAULT TRUE,

    CONSTRAINT chk_producto_tipo
        CHECK (tipo IN ('CONSUMO', 'TIEMPO_MESA')),
    CONSTRAINT chk_producto_categoria
        CHECK (categoria IN ('BEBIDA', 'CERVEZA', 'COMIDA', 'SNACK', 'MESA', 'OTRO')),
    CONSTRAINT chk_producto_precio
        CHECK (precio >= 0),
    CONSTRAINT chk_producto_minutos CHECK (
        (tipo = 'TIEMPO_MESA' AND minutos IS NOT NULL AND minutos > 0)
        OR (tipo = 'CONSUMO' AND minutos IS NULL)
    )
);

CREATE INDEX idx_producto_activo ON producto (activo);
