package step.learning.models;

import step.learning.dal.dto.CartItem;
import step.learning.dal.dto.Product;

import java.util.List;

public class CartPageModel {
    private List<Product> products;
    private List<CartItem> cartItems;
    public List<Product> getProducts() {
        return products;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public CartPageModel(List<Product> products, List<CartItem> cartItems) {
        this.products = products;
        this.cartItems = cartItems;
    }
}
