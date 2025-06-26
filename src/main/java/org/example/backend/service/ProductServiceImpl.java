package org.example.backend.service;

import lombok.RequiredArgsConstructor;
import org.example.backend.dto.InventoryDTO;
import org.example.backend.dto.ProductDTO;
import org.example.backend.entity.Product;
import org.example.backend.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProduct(Long id) {
        return productRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    @Override
    public ProductDTO createProduct(ProductDTO dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setInventories(new ArrayList<>());

        return convertToDTO(productRepository.save(product));
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO dto) {
        Product existing = productRepository.findById(id).orElse(null);
        if (existing == null) return null;

        existing.setName(dto.getName());
        existing.setDescription(dto.getDescription());
        existing.setPrice(dto.getPrice());

        return convertToDTO(productRepository.save(existing));
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductDTO> searchProducts(String query) {
        String searchTerm = "%" + query.toLowerCase() + "%";
        return productRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(query, query)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private ProductDTO convertToDTO(Product product) {
        List<InventoryDTO> inventoryDTOs = new ArrayList<>();

        if (product.getInventories() != null && !product.getInventories().isEmpty()) {
            inventoryDTOs = product.getInventories().stream()
                    .map(inventory -> new InventoryDTO(
                            inventory.getId(),
                            inventory.getQuantity(),
                            inventory.getLocation(),
                            product.getId()
                    ))
                    .collect(Collectors.toList());
        }

        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                inventoryDTOs
        );
    }
}