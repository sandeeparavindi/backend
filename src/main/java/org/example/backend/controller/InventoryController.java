package org.example.backend.controller;

import lombok.RequiredArgsConstructor;
import org.example.backend.dto.InventoryDTO;
import org.example.backend.service.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventories")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping
    public List<InventoryDTO> getAllInventories() {
        return inventoryService.getAllInventories();
    }

    @GetMapping("/product/{productId}")
    public List<InventoryDTO> getInventoriesByProduct(@PathVariable Long productId) {
        return inventoryService.getInventoriesByProductId(productId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryDTO> getInventory(@PathVariable Long id) {
        InventoryDTO inventory = inventoryService.getInventory(id);
        if (inventory == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(inventory);
    }

    @PostMapping
    public ResponseEntity<InventoryDTO> createInventory(@RequestBody InventoryDTO dto) {
        try {
            InventoryDTO created = inventoryService.createInventory(dto);
            return ResponseEntity.ok(created);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventoryDTO> updateInventory(@PathVariable Long id, @RequestBody InventoryDTO dto) {
        try {
            InventoryDTO updated = inventoryService.updateInventory(id, dto);
            if (updated == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventory(@PathVariable Long id) {
        if (inventoryService.getInventory(id) == null) {
            return ResponseEntity.notFound().build();
        }
        inventoryService.deleteInventory(id);
        return ResponseEntity.noContent().build();
    }
}