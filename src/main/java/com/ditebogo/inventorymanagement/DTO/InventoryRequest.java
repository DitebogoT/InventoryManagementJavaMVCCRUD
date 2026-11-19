package com.ditebogo.inventorymanagement.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public record InventoryRequest(

        @NotNull(message = "Product ID is required")
        Long productId,

        @NotNull(message = "Location ID is required")
        Long locationId,

        @NotNull(message = "Quantity is required")
        @PositiveOrZero(message = "Quantity cannot be negative")
        Integer quantity,

        @NotNull(message = "Last update cannot be empty")
        LocalDateTime lastUpdated,

        @NotNull(message = "Minimum stock level is required")
        @PositiveOrZero(message = "Minimum stock level cannot be negative")
        Integer minimumStockLevel

) {

}
