package com.ditebogo.inventorymanagement.Repository;

import com.ditebogo.inventorymanagement.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long>{

    boolean existsByProductCode(String productCode);

    Optional<Product> findByProductCode(String productCode);

    //This Spring Data JPA method helps with searching for an entity in the DB , which just contains a word that's supplied
    List<Product> findByProductNameContainingIgnoreCase(String productName);

}
