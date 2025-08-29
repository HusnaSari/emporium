package com.husnasari.emporium_backend.service;

import java.util.List;
import java.util.Set;

import com.husnasari.emporium_backend.domain.OrderStatus;
import com.husnasari.emporium_backend.model.Address;
import com.husnasari.emporium_backend.model.Cart;
import com.husnasari.emporium_backend.model.Order;
import com.husnasari.emporium_backend.model.OrderItem;
import com.husnasari.emporium_backend.model.User;

public interface OrderService {
    Set<Order> createOrder(User user, Address shippingAddress, Cart cart);

    Order findOrderByld(Long id) throws Exception;

    List<Order> usersOrderHistory(Long userId);

    List<Order> sellersOrder(Long sellerId);

    Order updateOrderStatus(Long orderId, OrderStatus orderStatus) throws Exception;

    Order cancelOrder(Long orderId, User user) throws Exception;

    OrderItem getOrderItemById(Long id) throws Exception;
}
