package com.ditebogo.inventorymanagement.Service;

import com.ditebogo.inventorymanagement.Entities.*;
import com.ditebogo.inventorymanagement.DTO.*;
import com.ditebogo.inventorymanagement.Repository.*;
import com.ditebogo.inventorymanagement.Exceptions.*;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;

@Service
public class InventoryManagementService {

    private final ProductRepository productRepository;
    private final InventoryRepository inventoryRepository;
    private final LocationRepository locationRepository;

    public InventoryManagementService(ProductRepository productRepository, InventoryRepository inventoryRepository, LocationRepository locationRepository)
    {
        this.productRepository = productRepository;
        this.inventoryRepository = inventoryRepository;
        this.locationRepository = locationRepository;
    }

    //Create Product
    @Transactional
    public ProductResponse createProduct(ProductRequest productRequest){

        Product product = new Product();

        product.setProductName(productRequest.productName());
        product.setProductCode(productRequest.productCode());
        product.setProductPrice(productRequest.productPrice());
        product.setDescription(productRequest.description());

        Product savedProduct = productRepository.save((product));

        return mapToResponse(savedProduct);

    }

    private ProductResponse mapToResponse (Product product)
    {
        return  new ProductResponse(
                product.getId(),
                product.getProductName(),
                product.getDescription(),
                product.getProductPrice(),
                product.getDateCreated()
        );
    }

     //Read All Products
    @Transactional(readOnly = true)
    public List<ProductResponse> getAllProducts() {

        return productRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public ProductResponse getProductById(Long id) {

        Product product = findProductOrThrow(id);

        return mapToResponse(product);
    }
    // Update Product
    @Transactional
    public ProductResponse updateProduct(Long id, ProductRequest productRequest) {

        Product product = findProductOrThrow(id);

        product.setProductName(productRequest.productName());
        product.setProductCode(productRequest.productCode());
        product.setProductPrice(productRequest.productPrice());
        product.setDescription(productRequest.description());

        Product updatedProduct = productRepository.save(product);

        return mapToResponse(updatedProduct);
    }
    private Product findProductOrThrow(Long id) {

        return productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Product not found with ID: " + id));
    }

    //Create Location
    @Transactional
    public LocationResponse createLocation(LocationRequest locationRequest)
    {
        Location location = new Location();
        location.setLocationName(locationRequest.locationName());
        location.setBuilding(locationRequest.building());
        location.setAisle(locationRequest.aisle());

        Location savedLocation = locationRepository.save(location);

        return mapLocationResponse(savedLocation);
    }

    private LocationResponse mapLocationResponse (Location location)
    {
        return new LocationResponse(
                location.getId(),
                location.getLocationName(),
                location.getBuilding(),
                location.getAisle(),
                location.getDateCreated()
        );
    }

    // Read All Locations
    @Transactional(readOnly = true)
    public List<LocationResponse> getAllLocations() {

        return locationRepository.findAll()
                .stream()
                .map(this::mapLocationResponse)
                .toList();
    }

    // Read One Location
    @Transactional(readOnly = true)
    public LocationResponse getLocationById(Long id) {

        Location location = findLocationOrThrow(id);

        return mapLocationResponse(location);
    }

    // Update Location
    @Transactional
    public LocationResponse updateLocation(Long id, LocationRequest locationRequest) {

        Location location = findLocationOrThrow(id);

        location.setLocationName(locationRequest.locationName());
        location.setBuilding(locationRequest.building());
        location.setAisle(locationRequest.aisle());

        Location updatedLocation = locationRepository.save(location);

        return mapLocationResponse(updatedLocation);
    }

    // Delete Location
    @Transactional
    public void deleteLocation(Long id) {

        Location location = findLocationOrThrow(id);

        locationRepository.delete(location);
    }

    private Location findLocationOrThrow(Long id) {

        return locationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Location not found with ID: " + id));
    }

    @Transactional
    public InventoryResponse createInventory (InventoryRequest inventoryRequest)
    {
        Inventory inventory = new Inventory();
        inventory.setQuantity(inventoryRequest.quantity());
        inventory.setMinimumStockLevel(inventoryRequest.minimumStockLevel());
        inventory.setLastUpdated(inventoryRequest.lastUpdated());

        // Verifying that the referenced product and location exist
        findProductOrThrow(inventoryRequest.productId());
        findLocationOrThrow(inventoryRequest.locationId());

        Inventory savedInventory = inventoryRepository.save(inventory);

        return mapInventoryResponse(savedInventory);
    }

    @Transactional(readOnly = true)
    public List<InventoryResponse> getAllInventory() {

        return inventoryRepository.findAll()
                .stream()
                .map(this::mapInventoryResponse)
                .toList();
    }

    // Read One Inventory
    @Transactional(readOnly = true)
    public InventoryResponse getInventoryById(Long id) {

        Inventory inventory = findInventoryOrThrow(id);

        return mapInventoryResponse(inventory);
    }

    // Update Inventory
    @Transactional
    public InventoryResponse updateInventory(Long id, InventoryRequest inventoryRequest) {

        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventory not found"));

        Product product = productRepository.findById(inventoryRequest.productId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Location location = locationRepository.findById(inventoryRequest.locationId())
                .orElseThrow(() -> new RuntimeException("Location not found"));

        inventory.setQuantity(inventoryRequest.quantity());
        inventory.setMinimumStockLevel(inventoryRequest.minimumStockLevel());

        inventory.setProductId(product);
        inventory.setLocationId(location);

        Inventory updatedInventory = inventoryRepository.save(inventory);

        return mapInventoryResponse(updatedInventory);
    }

    // Delete Inventory
    @Transactional
    public void deleteInventory(Long id) {

        Inventory inventory = findInventoryOrThrow(id);

        inventoryRepository.delete(inventory);
    }

    private Inventory findInventoryOrThrow(Long id) {

        return inventoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Inventory not found with ID: " + id));
    }

    private InventoryResponse mapInventoryResponse (Inventory inventory)
    {
        return new InventoryResponse(
                inventory.getId(),
                inventory.getQuantity(),
                inventory.getMinimumStockLevel(),
                inventory.getLastUpdated(),
                inventory.getProductId(),
                inventory.getLocationId()
        );

    }
}
