package mylab.order.di.service;

import mylab.order.di.entity.ShoppingCart;

public class OrderService {
    private ShoppingCart shoppingCart; // 의존성

    // XML에서 setter 주입
    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public ShoppingCart getShoppingCart() { // 테스트 편의를 위한 getter
        return shoppingCart;
    }

    /** 주문 처리: 여기선 합계만 반환 */
    public int placeOrder() {
        if (shoppingCart == null) throw new IllegalStateException("ShoppingCart가 주입되지 않았습니다.");
        return shoppingCart.totalPrice();
    }
}
