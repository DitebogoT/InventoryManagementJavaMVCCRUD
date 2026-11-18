package com.ditebogo.inventorymanagement.Repository;

import com.ditebogo.inventorymanagement.Entities.Location;
import com.ditebogo.inventorymanagement.Entities.Product;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location,Long> {

    boolean existsByLocationName(String locationName);

    Optional<Product> findByLocationName(String locationName);
}
