package com.utn.productos_api.controller;

import com.utn.productos_api.dto.ProductoDTO;
import com.utn.productos_api.dto.ProductoResponseDTO;
import com.utn.productos_api.dto.ActualizarStockDTO;
import com.utn.productos_api.model.Categoria;
import com.utn.productos_api.service.ProductoService;
import com.utn.productos_api.exception.ProductoNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@Tag(name = "Productos", description = "Gestión de productos: creación, consulta, actualización y eliminación")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @Operation(summary = "Listar todos los productos", description = "Devuelve una lista con todos los productos registrados")
    @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente")
    @GetMapping
    public List<ProductoResponseDTO> listar() {
        return productoService.obtenerTodos()
                .stream()
                .map(productoService::convertirAResponseDTO)
                .toList();
    }

    @Operation(summary = "Obtener producto por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Producto encontrado"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @GetMapping("/{id}")
    public ProductoResponseDTO obtenerPorId(@PathVariable Long id) {
        return productoService.obtenerPorId(id)
                .map(productoService::convertirAResponseDTO)
                .orElseThrow(() -> new ProductoNotFoundException(id));
    }

    @Operation(summary = "Filtrar productos por categoría")
    @ApiResponse(responseCode = "200", description = "Productos filtrados correctamente")
    @GetMapping("/categoria/{categoria}")
    public List<ProductoResponseDTO> obtenerPorCategoria(@PathVariable Categoria categoria) {
        return productoService.obtenerPorCategoria(categoria)
                .stream()
                .map(productoService::convertirAResponseDTO)
                .toList();
    }

    @Operation(summary = "Crear un nuevo producto")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Producto creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error de validación en los datos")
    })
    @PostMapping
    public ResponseEntity<ProductoResponseDTO> crear(@Valid @RequestBody ProductoDTO dto) {
        var producto = productoService.convertirADominio(dto);
        var guardado = productoService.crearProducto(producto);
        return ResponseEntity.status(201).body(productoService.convertirAResponseDTO(guardado));
    }

    @Operation(summary = "Actualizar producto completo")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Producto actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @PutMapping("/{id}")
    public ProductoResponseDTO actualizar(@PathVariable Long id, @Valid @RequestBody ProductoDTO dto) {
        var producto = productoService.convertirADominio(dto);
        var actualizado = productoService.actualizarProducto(id, producto)
                .orElseThrow(() -> new ProductoNotFoundException(id));
        return productoService.convertirAResponseDTO(actualizado);
    }

    @Operation(summary = "Actualizar solo el stock de un producto")
    @PatchMapping("/{id}/stock")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Stock actualizado correctamente"),
            @ApiResponse(responseCode = "400", description = "Stock inválido"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    public ProductoResponseDTO actualizarStock(@PathVariable Long id, @Valid @RequestBody ActualizarStockDTO dto) {
        var actualizado = productoService.actualizarStock(id, dto.getStock())
                .orElseThrow(() -> new ProductoNotFoundException(id));
        return productoService.convertirAResponseDTO(actualizado);
    }

    @Operation(summary = "Eliminar producto por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Producto eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (productoService.obtenerPorId(id).isEmpty())
            throw new ProductoNotFoundException(id);
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
}
