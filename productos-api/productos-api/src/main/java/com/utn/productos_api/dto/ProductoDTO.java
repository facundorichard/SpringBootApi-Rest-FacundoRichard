package com.utn.productos_api.dto;

import com.utn.productos_api.model.Categoria;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

@Schema(description = "Datos necesarios para crear o actualizar un producto")
public class ProductoDTO {

    @NotBlank
    @Size(min = 3, max = 100)
    @Schema(description = "Nombre del producto", example = "Camiseta deportiva")
    private String nombre;

    @Size(max = 500)
    @Schema(description = "Descripción del producto", example = "Camiseta 100% algodón, color azul")
    private String descripcion;

    @NotNull
    @DecimalMin("0.01")
    @Schema(description = "Precio del producto", example = "49.99")
    private Double precio;

    @NotNull
    @Min(0)
    @Schema(description = "Cantidad disponible en stock", example = "10")
    private Integer stock;

    @NotNull
    @Schema(description = "Categoría del producto", example = "ROPA")
    private String categoria;

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }

    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }

    public String  getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

}