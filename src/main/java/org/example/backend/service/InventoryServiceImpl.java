package org.example.backend.service;

import lombok.RequiredArgsConstructor;
import org.example.backend.dto.InventoryDTO;
import org.example.backend.entity.Inventory;
import org.example.backend.entity.Product;
import org.example.backend.repository.InventoryRepository;
import org.example.backend.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;

    @Override
    public List<InventoryDTO> getAllInventories() {
        return inventoryRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<InventoryDTO> getInventoriesByProductId(Long productId) {
        return inventoryRepository.findByProductId(productId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public InventoryDTO getInventory(Long id) {
        return inventoryRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    @Override
    public InventoryDTO createInventory(InventoryDTO dto) {
        Product product = productRepository.findById(dto.getProductId()).orElse(null);
        if (product == null) {
            throw new RuntimeException("Product not found with id: " + dto.getProductId());
        }

        Inventory inventory = new Inventory();
        inventory.setQuantity(dto.getQuantity());
        inventory.setLocation(dto.getLocation());
        inventory.setProduct(product);

        return convertToDTO(inventoryRepository.save(inventory));
    }

    @Override
    public InventoryDTO updateInventory(Long id, InventoryDTO dto) {
        Inventory existing = inventoryRepository.findById(id).orElse(null);
        if (existing == null) return null;

        existing.setQuantity(dto.getQuantity());
        existing.setLocation(dto.getLocation());

        if (dto.getProductId() != null && !dto.getProductId().equals(existing.getProduct().getId())) {
            Product product = productRepository.findById(dto.getProductId()).orElse(null);
            if (product == null) {
                throw new RuntimeException("Product not found with id: " + dto.getProductId());
            }
            existing.setProduct(product);
        }

        return convertToDTO(inventoryRepository.save(existing));
    }

    @Override
    public void deleteInventory(Long id) {
        inventoryRepository.deleteById(id);
    }

    private InventoryDTO convertToDTO(Inventory inventory) {
        return new InventoryDTO(
                inventory.getId(),
                inventory.getQuantity(),
                inventory.getLocation(),
                inventory.getProduct().getId()
        );
    }
}