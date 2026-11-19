package com.ditebogo.inventorymanagement.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LocationRequest(

        @NotBlank(message = "Location name cannot be empty")
        @Size(max = 100, message = "Location name must not exceed 100 characters")
        String locationName,

        @NotBlank(message = "Building cannot be empty")
        String building,

        String aisle,

        String description

) {
}
