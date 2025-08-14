package mylab.order.di;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import mylab.order.di.entity.ShoppingCart;
import mylab.order.di.service.OrderService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:mylab-order-di.xml")
public class OrderSpringTest {

    @Autowired
    private ShoppingCart shoppingCart;

    @Autowired
    private OrderService orderService;

    @Test
    public void testShoppingCartBean() {
        assertNotNull(shoppingCart);
        assertNotNull(shoppingCart.getProducts());
        assertEquals(2, shoppingCart.getProducts().size());

        assertEquals("노트북", shoppingCart.getProducts().get(0).getName());
        assertEquals("스마트폰", shoppingCart.getProducts().get(1).getName());
    }

    @Test
    public void testOrderServiceBean() {
        assertNotNull(orderService);
        assertNotNull(orderService.getShoppingCart());
        // 합계: 150,000 + 800,000 = 950,000
        assertEquals(950000, orderService.placeOrder());
    }
}
