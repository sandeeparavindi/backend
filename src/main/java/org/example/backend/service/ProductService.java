package org.example.backend.service;

import org.example.backend.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getAllProducts();

    ProductDTO getProduct(Long id);

    ProductDTO createProduct(ProductDTO dto);

    ProductDTO updateProduct(Long id, ProductDTO dto);

    void deleteProduct(Long id);

    List<ProductDTO> searchProducts(String query);
}