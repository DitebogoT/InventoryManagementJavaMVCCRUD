package com.ditebogo.inventorymanagement.DTO;

import com.ditebogo.inventorymanagement.Entities.Location;
import com.ditebogo.inventorymanagement.Entities.Product;

import java.time.LocalDateTime;

public record InventoryResponse(Long id, Integer quantity, Integer minimumStockLevel, LocalDateTime lastUpdated,
                                Product productId, Location locationId )
{
}
