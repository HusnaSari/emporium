package com.husnasari.emporium_backend.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.husnasari.emporium_backend.domain.OrderStatus;
import com.husnasari.emporium_backend.domain.PaymentStatus;
import com.husnasari.emporium_backend.model.Address;
import com.husnasari.emporium_backend.model.Cart;
import com.husnasari.emporium_backend.model.CartItem;
import com.husnasari.emporium_backend.model.Order;
import com.husnasari.emporium_backend.model.OrderItem;
import com.husnasari.emporium_backend.model.User;
import com.husnasari.emporium_backend.repository.AddressRepository;
import com.husnasari.emporium_backend.repository.OrderItemRepository;
import com.husnasari.emporium_backend.repository.OrderRepository;
import com.husnasari.emporium_backend.service.OrderService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final AddressRepository addressRepository;
    private final OrderItemRepository orderItemRepository;

    @Override
    public Set<Order> createOrder(User user, Address shippingAddress, Cart cart) {

        if (!user.getAddresses().contains(shippingAddress)) {
            user.getAddresses().add(shippingAddress);
        }

        Address address = addressRepository.save(shippingAddress);

        // marka 1 => 4 tişört
        // marka 2 => 3 pantolon
        // brand 1 => 1 saat

        Map<Long, List<CartItem>> itemsBySeller = cart.getCartItems().stream()
                .collect(Collectors.groupingBy(item -> item.getProduct().getSeller().getId()));

        Set<Order> orders = new HashSet<>();

        for (Map.Entry<Long, List<CartItem>> entry : itemsBySeller.entrySet()) {
            Long sellerId = entry.getKey();
            List<CartItem> items = entry.getValue();

            int totalOrderPrice = items.stream().mapToInt(CartItem::getSellingPrice).sum();

            int totalItems = items.stream().mapToInt(CartItem::getQuantity).sum();

            Order createdOrder = new Order();
            createdOrder.setUser(user);
            createdOrder.setSellerId(sellerId);
            createdOrder.setTotalMrpPrice(totalOrderPrice);
            createdOrder.setTotalItem(totalItems);
            createdOrder.setShippingAddress(address);
            createdOrder.setOrderStatus(OrderStatus.PENDING);
            createdOrder.getPaymentDetails().setStatus(PaymentStatus.PENDING);

            Order savedOrder = orderRepository.save(createdOrder);
            orders.add(savedOrder);

            List<OrderItem> orderItems = new ArrayList<>();

            for (CartItem item : items) {
                OrderItem orderItem = new OrderItem();
                orderItem.setOrder(savedOrder);
                orderItem.setMrpPrice(item.getMrpPrice());
                orderItem.setProduct(item.getProduct());
                orderItem.setQuantity(item.getQuantity());
                orderItem.setSize(item.getSize());
                orderItem.setUserId(item.getUserId());
                orderItem.setSellingPrice(item.getSellingPrice());

                savedOrder.getOrderItems().add(orderItem);

                OrderItem savOrderItem = orderItemRepository.save(orderItem);
                orderItems.add(savOrderItem);
            }
        }

        return orders;
    }

    @Override
    public Order findOrderByld(Long id) throws Exception {

        return orderRepository.findById(id).orElseThrow(() -> new Exception("order not found"));
    }

    @Override
    public List<Order> usersOrderHistory(Long userId) {

        return orderRepository.findByUserId(userId);
    }

    @Override
    public List<Order> sellersOrder(Long sellerId) {

        return orderRepository.findBySellerId(sellerId);
    }

    @Override
    public Order updateOrderStatus(Long orderId, OrderStatus orderStatus) throws Exception {

        Order order = findOrderByld(orderId);
        order.setOrderStatus(orderStatus);
        return orderRepository.save(order);
    }

    @Override
    public Order cancelOrder(Long orderId, User user) throws Exception {

        Order order = findOrderByld(orderId);
        if (user.getId().equals(order.getUser().getId())) {
            throw new Exception("you don't have access to this order");
        }
        order.setOrderStatus(OrderStatus.CANCELLED);
        return orderRepository.save(order);
    }

    @Override
    public OrderItem getOrderItemById(Long id) throws Exception {

        return orderItemRepository.findById(id).orElseThrow(() -> new Exception("order item not exist ..."));
    }

}
