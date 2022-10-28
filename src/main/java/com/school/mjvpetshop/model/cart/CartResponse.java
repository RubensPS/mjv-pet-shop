package com.school.mjvpetshop.model.cart;

import com.school.mjvpetshop.model.cartItem.CartItemEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@NoArgsConstructor
@Setter
@Getter
public class CartResponse {

    private Long id;
    private Set<CartItemEntity> items;
    private BigDecimal totalShopValue;

    public CartResponse(Long id, Set<CartItemEntity> items, BigDecimal totalShopValue) {
        this.id = id;
        this.items = items;
        this.totalShopValue = totalShopValue;
    }


}
