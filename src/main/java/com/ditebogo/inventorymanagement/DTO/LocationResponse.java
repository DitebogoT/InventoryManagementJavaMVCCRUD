package com.ditebogo.inventorymanagement.DTO;

import java.time.LocalDateTime;

public record LocationResponse(Long id, String locationName, String building, String aisle, LocalDateTime dateCreated) {
}
