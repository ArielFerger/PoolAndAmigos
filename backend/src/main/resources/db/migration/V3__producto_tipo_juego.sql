-- Un producto de tiempo ahora tiene que decir a que juego aplica,
-- para saber que recurso ocupa el turno que genera.
ALTER TABLE producto ADD COLUMN tipo_juego VARCHAR(20);

-- Los productos de tiempo que ya existen apuntan a la mesa de pool:
-- era el unico juego que habia cuando se crearon.
UPDATE producto SET tipo_juego = 'MESA_POOL' WHERE tipo = 'TIEMPO_MESA';

-- Mismo patron que chk_producto_minutos: el campo es obligatorio para
-- TIEMPO_MESA y tiene que estar vacio para CONSUMO.
ALTER TABLE producto ADD CONSTRAINT chk_producto_tipo_juego CHECK (
    (tipo = 'TIEMPO_MESA' AND tipo_juego IN ('MESA_POOL', 'PING_PONG', 'METEGOL'))
    OR (tipo = 'CONSUMO' AND tipo_juego IS NULL)
);
