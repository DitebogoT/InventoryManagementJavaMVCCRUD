package com.ditebogo.inventorymanagement.Controllers;


import com.ditebogo.inventorymanagement.DTO.*;
import com.ditebogo.inventorymanagement.Service.InventoryManagementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inventoryManagement")
public class InventoryManagementController {

    private final InventoryManagementService inventoryManagementService;

    public InventoryManagementController(
            InventoryManagementService inventoryManagementService) {

        this.inventoryManagementService = inventoryManagementService;
    }

    // =========================
    // PRODUCT ENDPOINTS
    // =========================

    @PostMapping("/products")
    public ResponseEntity<ProductResponse> createProduct(
            @RequestBody ProductRequest productRequest) {

        ProductResponse response =
                inventoryManagementService.createProduct(productRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductResponse>> getAllProducts() {

        return ResponseEntity.ok(
                inventoryManagementService.getAllProducts());
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductResponse> getProductById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                inventoryManagementService.getProductById(id));
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable Long id,
            @RequestBody ProductRequest productRequest) {

        return ResponseEntity.ok(
                inventoryManagementService.updateProduct(id, productRequest));
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {

        inventoryManagementService.deleteProduct(id);

        return ResponseEntity.noContent().build();
    }


    // =========================
    // LOCATION ENDPOINTS
    // =========================

    @PostMapping("/locations")
    public ResponseEntity<LocationResponse> createLocation(
            @RequestBody LocationRequest locationRequest) {

        LocationResponse response =
                inventoryManagementService.createLocation(locationRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/locations")
    public ResponseEntity<List<LocationResponse>> getAllLocations() {

        return ResponseEntity.ok(
                inventoryManagementService.getAllLocations());
    }

    @GetMapping("/locations/{id}")
    public ResponseEntity<LocationResponse> getLocationById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                inventoryManagementService.getLocationById(id));
    }

    @PutMapping("/locations/{id}")
    public ResponseEntity<LocationResponse> updateLocation(
            @PathVariable Long id,
            @RequestBody LocationRequest locationRequest) {

        return ResponseEntity.ok(
                inventoryManagementService.updateLocation(id, locationRequest));
    }

    @DeleteMapping("/locations/{id}")
    public ResponseEntity<Void> deleteLocation(@PathVariable Long id) {

        inventoryManagementService.deleteLocation(id);

        return ResponseEntity.noContent().build();
    }


    // =========================
    // INVENTORY ENDPOINTS
    // =========================

    @PostMapping("/inventory")
    public ResponseEntity<InventoryResponse> createInventory(
            @RequestBody InventoryRequest inventoryRequest) {

        InventoryResponse response =
                inventoryManagementService.createInventory(inventoryRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/inventory")
    public ResponseEntity<List<InventoryResponse>> getAllInventory() {

        return ResponseEntity.ok(
                inventoryManagementService.getAllInventory());
    }

    @GetMapping("/inventory/{id}")
    public ResponseEntity<InventoryResponse> getInventoryById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                inventoryManagementService.getInventoryById(id));
    }

    @PutMapping("/inventory/{id}")
    public ResponseEntity<InventoryResponse> updateInventory(
            @PathVariable Long id,
            @RequestBody InventoryRequest inventoryRequest) {

        return ResponseEntity.ok(
                inventoryManagementService.updateInventory(id, inventoryRequest));
    }

    @DeleteMapping("/inventory/{id}")
    public ResponseEntity<Void> deleteInventory(@PathVariable Long id) {

        inventoryManagementService.deleteInventory(id);

        return ResponseEntity.noContent().build();
    }


}
