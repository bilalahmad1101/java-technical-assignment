package kata.supermarket;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductTest {

    @Test
    void singleItemHasExpectedUnitPriceFromProduct() {
        final BigDecimal price = new BigDecimal("2.49");
        assertEquals(price, new Product(124, price,0).oneOf().getPrice());
    }

    @Test
    void singleItemHasExpectedUnitProductIdFromProduct() { // validates the expected product ID is retrieved
        int productId = 124;
        assertEquals(productId, new Product(productId, new BigDecimal("2.49"),0).oneOf().getProductId());
    }

    @Test
    void singleItemHasExpectedUnitDiscountCodeFromProduct() { //validates the expected discount code is retrieved
        int discountCode = 0;
        assertEquals(discountCode, new Product(124, new BigDecimal("2.49"),discountCode).oneOf().getDiscountCode());
    }

}