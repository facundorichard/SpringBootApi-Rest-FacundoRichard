package com.utn.productos_api.dto;

import com.utn.productos_api.model.Categoria;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO de respuesta que representa un producto completo con todos sus atributos")
public class ProductoResponseDTO {

    @Schema(
            description = "Identificador único del producto",
            example = "1",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private Long id;

    @Schema(
            description = "Nombre del producto",
            example = "Camiseta deportiva"
    )
    private String nombre;

    @Schema(
            description = "Descripción detallada del producto",
            example = "Camiseta de entrenamiento de tela respirable"
    )
    private String descripcion;

    @Schema(
            description = "Precio actual del producto en pesos argentinos",
            example = "15999.99"
    )
    private Double precio;

    @Schema(
            description = "Cantidad disponible en stock",
            example = "35"
    )
    private Integer stock;

    @Schema(
            description = "Categoría a la que pertenece el producto",
            example = "ROPA_DEPORTIVA"
    )
    private String categoria;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }

    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
}
