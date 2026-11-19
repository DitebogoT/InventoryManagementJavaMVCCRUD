package com.ditebogo.inventorymanagement.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductResponse(Long id, String productName, String description, BigDecimal productPrice,
                              LocalDateTime dateCreated)
{
}
