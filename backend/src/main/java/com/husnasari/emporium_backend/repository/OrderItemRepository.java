package com.husnasari.emporium_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.husnasari.emporium_backend.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
