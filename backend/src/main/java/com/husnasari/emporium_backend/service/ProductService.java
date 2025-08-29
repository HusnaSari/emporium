package com.husnasari.emporium_backend.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.husnasari.emporium_backend.exception.ProductException;
import com.husnasari.emporium_backend.model.Product;
import com.husnasari.emporium_backend.model.Seller;
import com.husnasari.emporium_backend.request.CreateProductRequest;

public interface ProductService {

    public Product createProduct(CreateProductRequest req, Seller seller);

    public void deleteProduct(Long productId) throws ProductException;

    public Product updateProduct(Long productId, Product product) throws ProductException;

    Product findProductById(Long productId) throws ProductException;

    List<Product> searchProducts(String query);

    public Page<Product> getAllProducts(
            String category,
            String brand,
            String colors,
            String sizes,
            Integer minPrice,
            Integer maxPrice,
            Integer minDiscount,
            String sort,
            String stock,
            Integer pageNumber);

    List<Product> getProductsBySellerId(Long sellerId);

}
