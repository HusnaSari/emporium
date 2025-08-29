package com.husnasari.emporium_backend.controller;

import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.husnasari.emporium_backend.domain.PaymentMethod;
import com.husnasari.emporium_backend.model.Address;
import com.husnasari.emporium_backend.model.Cart;
import com.husnasari.emporium_backend.model.Order;
import com.husnasari.emporium_backend.model.OrderItem;
import com.husnasari.emporium_backend.model.PaymentOrder;
import com.husnasari.emporium_backend.model.Seller;
import com.husnasari.emporium_backend.model.SellerReport;
import com.husnasari.emporium_backend.model.User;
import com.husnasari.emporium_backend.response.PaymentLinkResponse;
import com.husnasari.emporium_backend.service.CartService;
import com.husnasari.emporium_backend.service.OrderService;
import com.husnasari.emporium_backend.service.SellerReportService;
import com.husnasari.emporium_backend.service.SellerService;
import com.husnasari.emporium_backend.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;
    private final CartService cartService;
    private final SellerService sellerService;
    private final SellerReportService sellerReportService;

    @PostMapping()
    public ResponseEntity<PaymentLinkResponse> createOrderHandler(
            @RequestBody Address shippingAddress,
            @RequestParam PaymentMethod paymentMethod,
            @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserByJwtToken(jwt);
        Cart cart = cartService.findUserCart(user);

        Set<Order> orders = orderService.createOrder(user, shippingAddress, cart);

        // PaymentOrder paymentOrder = paymentService.createOrder(user, orders);

        PaymentLinkResponse res = new PaymentLinkResponse();

        // if (paymentMethod.equals(PaymentMethod.RAZORPAY)) {
        // PaymentLink payment = paymentService.createRazorpayPaymentLink(user,
        // paymentOrder.getAmount(),
        // paymentOrder.getId());
        // String paymentUrl = payment.get("short_url");
        // String paymentUrlId = payment.get("id");

        // res.setPaymentLinkUrl(paymentUrl);
        // paymentOrder.setPaymentLinkId(paymentUrlId);
        // paymentOrderRepository.save(paymentOrder);
        // }
        // else {
        // String paymentUrl = paymentService.createStripePaymentLink(user,
        // paymentOrder.getAmount(),
        // paymentOrder.getId());
        // res.setPaymentLinkUrl(paymentUrl);
        // }
        return new ResponseEntity<>(res, HttpStatus.OK);

    }

    @GetMapping("/user")
    public ResponseEntity<List<Order>> usersOrderHistoryHandler(
            @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserByJwtToken(jwt);
        List<Order> orders = orderService.usersOrderHistory(user.getId());
        return new ResponseEntity<>(orders, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long orderId,
            @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserByJwtToken(jwt);
        Order order = orderService.findOrderByld(orderId);
        return new ResponseEntity<>(order, HttpStatus.ACCEPTED);
    }

    @GetMapping("/item/{orderItemId}")
    public ResponseEntity<OrderItem> getOrderItemById(
            @PathVariable Long orderItemId,
            @RequestHeader("Authorization") String jwt) throws Exception {

        System.out.println("------- controller -------");
        User user = userService.findUserByJwtToken(jwt);
        OrderItem orderItem = orderService.getOrderItemById(orderItemId);
        return new ResponseEntity<>(orderItem, HttpStatus.ACCEPTED);
    }

    @PutMapping("/{orderId}/cancel")
    public ResponseEntity<Order> cancelOrder(
            @PathVariable Long orderId,
            @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserByJwtToken(jwt);
        Order order = orderService.cancelOrder(orderId, user);

        Seller seller = sellerService.getSellerById(order.getSellerId());
        SellerReport report = sellerReportService.getSellerReport(seller);

        report.setCanceledOrders(report.getCanceledOrders() + 1);
        report.setTotalRefunds(report.getTotalRefunds() +
                order.getTotalSellingPrice());
        sellerReportService.updateSellerReport(report);

        return ResponseEntity.ok(order);
    }
}