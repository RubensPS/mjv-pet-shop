package com.school.mjvpetshop.model.cart;

import com.school.mjvpetshop.model.cartItem.CartItem;

import java.math.BigDecimal;
import java.util.Set;

public class CartEntity {
    private Long id;
    private Set<CartItem> items;
    private BigDecimal totalShopValue;

}
