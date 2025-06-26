package org.example.backend.controller;

import lombok.RequiredArgsConstructor;
import org.example.backend.dto.ProductDTO;
import org.example.backend.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductService service;

    @GetMapping
    public List<ProductDTO> getAll() {
        return service.getAllProducts();
    }

    @GetMapping("/search")
    public List<ProductDTO> searchProducts(@RequestParam String query) {
        return service.searchProducts(query);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getOne(@PathVariable Long id) {
        ProductDTO product = service.getProduct(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> add(@RequestBody ProductDTO dto) {
        try {
            dto.setInventories(null);
            ProductDTO created = service.createProduct(dto);
            return ResponseEntity.ok(created);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable Long id, @RequestBody ProductDTO dto) {
        try {
            dto.setInventories(null);
            ProductDTO updated = service.updateProduct(id, dto);
            if (updated == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.getProduct(id) == null) {
            return ResponseEntity.notFound().build();
        }
        service.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}