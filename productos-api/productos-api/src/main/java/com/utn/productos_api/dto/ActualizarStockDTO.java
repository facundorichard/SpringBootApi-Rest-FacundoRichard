package com.utn.productos_api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "ActualizarStockDTO", description = "DTO para actualizar únicamente el stock de un producto")
public class ActualizarStockDTO {

    @NotNull(message = "El stock no puede ser nulo")
    @Min(value = 0, message = "El stock no puede ser negativo")
    @Schema(description = "Cantidad nueva de stock del producto", example = "10", required = true, minimum = "0")
    private Integer stock;

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
