package com.poolamigos.producto;

import com.poolamigos.producto.dto.ProductoRequest;
import com.poolamigos.producto.dto.ProductoResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ProductoService {
    final private ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<ProductoResponse> listar(boolean soloActivos) {
        List<Producto> productos;
        if (soloActivos) {
            productos = productoRepository.findByActivoTrue();
        } else {
            productos = productoRepository.findAll();
        }
        return productos.stream().map(ProductoResponse::from).toList();
    }

    public ProductoResponse obtener(Long id) {
        Producto producto = productoRepository.findById(id).orElse(null);
        if (producto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el producto con el id: " + id);
        }
        return ProductoResponse.from(producto);
    }

    @Transactional
    public ProductoResponse crearProducto(ProductoRequest request) {
        Producto producto = construirDesde(request); // definir tipo del objeto Body
        Producto guardado = productoRepository.save(producto);
        return ProductoResponse.from(guardado);
    }

    private Producto construirDesde(ProductoRequest request) {
        return switch (request.tipo()) {
            case CONSUMO -> new ProductoConsumo(request.nombre(), request.categoria(), request.precio());
            case TIEMPO_MESA -> new ProductoTiempoMesa(
                    request.nombre(), request.categoria(), request.precio(), request.minutos());
        };
    }

    @Transactional
    public ProductoResponse actualizarProducto(Long id, ProductoRequest request) {
        Producto existente = productoRepository.findById(id).orElse(null);
        if (existente == null) { //exception del producto con id nulo
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el producto con el id: " + id);
        }
        if (existente.getTipo() != request.tipo()) {
            productoRepository.delete(existente);
            productoRepository.flush(); // flush() fuerza que ese DELETE se ejecute ya mismo, en este instante, antes de seguir
            Producto reemplazo = construirDesde(request);
            return ProductoResponse.from(productoRepository.save(reemplazo));
        }

        existente.setNombre(request.nombre());
        existente.setCategoria(request.categoria());
        existente.setPrecio(request.precio());
        if (existente instanceof ProductoTiempoMesa tiempoMesa) { // instanceof, comprueba que sea un objeto de ProductoTiempoMesa
            tiempoMesa.setMinutos(request.minutos());
        }
        return ProductoResponse.from(existente);
    }

    @Transactional
    public ProductoResponse cambiarActivo(Long id, boolean activo) { //true or false
        Producto existente = productoRepository.findById(id).orElse(null);
        if (existente == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el producto con el id: " + id);
        }
        existente.setActivo(activo);
        return ProductoResponse.from(existente);
    }

}
