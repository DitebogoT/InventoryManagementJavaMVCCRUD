package com.ditebogo.inventorymanagement.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record ProductRequest(

        @NotBlank(message = "Product name cannot be empty")
        @Size(max = 100, message = "Product name must not exceed 100 characters")
        String productName,

        @NotBlank(message = "Description cannot be empty")
        @Size(max = 250, message = "Description must not exceed 250 characters")
        String description,

        @NotBlank(message = "Product code cannot be empty")
        String productCode,

        @NotNull(message = "Product price is required")
        @PositiveOrZero(message = "Product price cannot be negative")
        BigDecimal productPrice

) {
}
