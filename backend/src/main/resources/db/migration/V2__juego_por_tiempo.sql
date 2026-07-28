CREATE TABLE juego_por_tiempo (
    id     BIGSERIAL PRIMARY KEY,
    tipo   VARCHAR(20) NOT NULL,
    numero INTEGER     NOT NULL,

    CONSTRAINT chk_juego_tipo
        CHECK (tipo IN ('MESA_POOL', 'PING_PONG', 'METEGOL')),

    -- El numero es unico POR TIPO: pueden convivir "Mesa 1" y "Ping Pong 1",
    -- porque son objetos fisicos distintos.
    CONSTRAINT uq_juego_tipo_numero UNIQUE (tipo, numero)
);

INSERT INTO juego_por_tiempo (tipo, numero) VALUES ('MESA_POOL', 1);
