package com.husnasari.emporium_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.husnasari.emporium_backend.model.Cart;
import com.husnasari.emporium_backend.model.CartItem;
import com.husnasari.emporium_backend.model.Product;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findByCartAndProductAndSize(Cart cart, Product product, String size);
}
