package org.example.backend.service;

import org.example.backend.dto.InventoryDTO;

import java.util.List;

public interface InventoryService {
    List<InventoryDTO> getAllInventories();

    List<InventoryDTO> getInventoriesByProductId(Long productId);

    InventoryDTO getInventory(Long id);

    InventoryDTO createInventory(InventoryDTO dto);

    InventoryDTO updateInventory(Long id, InventoryDTO dto);

    void deleteInventory(Long id);
}