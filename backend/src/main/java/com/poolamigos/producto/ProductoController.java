package com.poolamigos.producto;

import com.poolamigos.producto.dto.ProductoRequest;
import com.poolamigos.producto.dto.ProductoResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    //traemos el service
    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public List<ProductoResponse> listar(
            @RequestParam(name = "soloActivos", defaultValue = "false") boolean soloActivos) {
        return productoService.listar(soloActivos);
    }

    @GetMapping("/{id}")
    public ProductoResponse obtener(@PathVariable Long id) {
        return productoService.obtener(id);
    }

    @PostMapping
    public ResponseEntity<ProductoResponse> crear(@Valid @RequestBody ProductoRequest productoRequest) {
        ProductoResponse creado = productoService.crearProducto(productoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponse> actualizar(@PathVariable Long id, @Valid @RequestBody ProductoRequest productoRequest) {
        ProductoResponse actualizado = productoService.actualizarProducto(id, productoRequest);
        return ResponseEntity.status(HttpStatus.OK).body(actualizado);
    }

    @PatchMapping("/{id}/activo")
    public ProductoResponse cambiarActivo(@PathVariable Long id, @RequestBody Map<String, Boolean> body) {
        Boolean activo = body.get("activo");
        return productoService.cambiarActivo(id, Boolean.TRUE.equals(activo));
    }


}