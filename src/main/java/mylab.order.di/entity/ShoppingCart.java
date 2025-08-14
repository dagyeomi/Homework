package mylab.order.di.entity;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<Product> products = new ArrayList<>();

    // DI ¿ë setter
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

    public int totalPrice() {
        int sum = 0;
        for (Product p : products) sum += p.getPrice();
        return sum;
    }
}
