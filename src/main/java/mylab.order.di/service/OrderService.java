package mylab.order.di.service;

import mylab.order.di.entity.ShoppingCart;

public class OrderService {
    private ShoppingCart shoppingCart; // ������

    // XML���� setter ����
    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public ShoppingCart getShoppingCart() { // �׽�Ʈ ���Ǹ� ���� getter
        return shoppingCart;
    }

    /** �ֹ� ó��: ���⼱ �հ踸 ��ȯ */
    public int placeOrder() {
        if (shoppingCart == null) throw new IllegalStateException("ShoppingCart�� ���Ե��� �ʾҽ��ϴ�.");
        return shoppingCart.totalPrice();
    }
}
