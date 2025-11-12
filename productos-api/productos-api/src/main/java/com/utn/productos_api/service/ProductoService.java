package com.utn.productos_api.service;

import com.utn.productos_api.dto.ProductoDTO;
import com.utn.productos_api.dto.ProductoResponseDTO;
import com.utn.productos_api.model.Categoria;
import com.utn.productos_api.model.Producto;
import com.utn.productos_api.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    // Inyección por constructor (mejor práctica)
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    // Crear un nuevo producto
    public Producto crearProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    // Obtener todos los productos
    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    // Obtener producto por ID
    public Optional<Producto> obtenerPorId(Long id) {
        return productoRepository.findById(id);
    }

    // Obtener productos por categoría
    public List<Producto> obtenerPorCategoria(Categoria categoria) {
        return productoRepository.findByCategoria(categoria);
    }

    // Actualizar un producto completo
    public Optional<Producto> actualizarProducto(Long id, Producto productoActualizado) {
        return productoRepository.findById(id).map(p -> {
            p.setNombre(productoActualizado.getNombre());
            p.setDescripcion(productoActualizado.getDescripcion());
            p.setPrecio(productoActualizado.getPrecio());
            p.setStock(productoActualizado.getStock());
            p.setCategoria(productoActualizado.getCategoria());
            return productoRepository.save(p);
        });
    }

    // Actualizar solo el stock
    public Optional<Producto> actualizarStock(Long id, Integer nuevoStock) {
        return productoRepository.findById(id).map(p -> {
            p.setStock(nuevoStock);
            return productoRepository.save(p);
        });
    }

    // Eliminar un producto
    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }

    public ProductoResponseDTO convertirAResponseDTO(Producto producto) {
        ProductoResponseDTO dto = new ProductoResponseDTO();
        dto.setId(producto.getId());
        dto.setNombre(producto.getNombre());
        dto.setDescripcion(producto.getDescripcion());
        dto.setPrecio(producto.getPrecio());
        dto.setStock(producto.getStock());
        dto.setCategoria(producto.getCategoria().toString());
        return dto;
    }

    public Producto convertirADominio(ProductoDTO dto) {
        Producto producto = new Producto();
        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock());
        producto.setCategoria(Categoria.valueOf(dto.getCategoria().toUpperCase()));
        return producto;
    }
}

