package com.husnasari.emporium_backend.service;

import com.husnasari.emporium_backend.model.Cart;
import com.husnasari.emporium_backend.model.CartItem;
import com.husnasari.emporium_backend.model.Product;
import com.husnasari.emporium_backend.model.User;

public interface CartService {

    public CartItem addCartItem(
            User user,
            Product Product,
            String Size,
            int quantity);

    public Cart findUserCart(User user);

}
