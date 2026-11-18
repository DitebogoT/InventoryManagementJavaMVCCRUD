package com.ditebogo.inventorymanagement.Repository;

import com.ditebogo.inventorymanagement.Entities.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory,Long> {

    boolean existsById (Long id);

    Optional<Inventory> findById(Long id);

}
