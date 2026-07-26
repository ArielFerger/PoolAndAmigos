# pool&amigos

Gestion de un bar-pool: ventas de productos y de tiempo de mesa, cobros en efectivo y
transferencia, turnos de la mesa y caja.

## Stack

- **Backend**: Java 21, Spring Boot 4.1, Spring Data JPA, Flyway, PostgreSQL 17
- **Frontend**: React 19 + TypeScript, Vite 8
- **Entorno**: todo en Docker; no hace falta instalar Java, Maven, Node ni Postgres en Windows

## Levantar el entorno

```bash
docker compose up
```

| Servicio | URL |
|---|---|
| Frontend | http://localhost:5173 |
| Backend  | http://localhost:8080 |
| Postgres | localhost:5432 |

La primera vez tarda varios minutos: descarga las imagenes y las dependencias de
Maven y npm. Despues quedan cacheadas en los volumenes `maven-repo` y el de node_modules.

## Desarrollo

El codigo se edita en Windows y se monta dentro de los contenedores.

- **Frontend**: el hot reload de Vite funciona solo (esta configurado con polling,
  necesario sobre bind mounts de Windows).
- **Backend**: para aplicar cambios hay que recompilar; DevTools reinicia solo al detectar
  las clases nuevas.

  ```bash
  docker compose exec backend mvn compile
  ```

## Configuracion

Las variables viven en `.env` (no se versiona). `.env.example` documenta cuales hacen falta.

## Estructura

```
backend/    Spring Boot; un paquete por modulo de negocio
  src/main/java/com/poolamigos/
    config/    seguridad, CORS
    common/    manejo de errores
    producto/  venta/  pago/  mesa/  turno/  caja/
  src/main/resources/db/migration/   migraciones Flyway

frontend/   React + TypeScript
  src/
    api/         cliente HTTP
    components/  pages/  hooks/  context/  lib/
```
