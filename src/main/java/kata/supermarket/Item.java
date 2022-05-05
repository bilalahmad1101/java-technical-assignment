package kata.supermarket;

import java.math.BigDecimal;

public interface Item {
    BigDecimal getPrice(); //renamed for better naming convention

    int getDiscountCode(); // this will retrieve discount code of the products

    int getProductId(); // this will retrieve product ID of the products
}
