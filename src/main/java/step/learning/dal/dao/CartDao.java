package step.learning.dal.dao;

import step.learning.dal.dto.CartItem;

import java.util.UUID;

public class CartDao {
    public CartItem[] getCart() {
        return new CartItem[] {
                new CartItem(UUID.randomUUID(), UUID.randomUUID(), 1),
                new CartItem(UUID.randomUUID(), UUID.randomUUID(), 2),
                new CartItem(UUID.randomUUID(), UUID.randomUUID(), 3),
        };
    }
}
