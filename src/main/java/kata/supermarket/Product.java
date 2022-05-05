package kata.supermarket;

import java.math.BigDecimal;

public class Product {

    private final int productId;
    private final BigDecimal pricePerUnit;
    private final int discountCode;

    public Product(final int productId, final BigDecimal pricePerUnit, final int discountCode) {
        this.productId = productId;
        this.pricePerUnit = pricePerUnit;
        this.discountCode = discountCode;
    }

    public BigDecimal pricePerUnit() { //had missing public keyword - even though all classes are in the same package (no need for non-public)
        return pricePerUnit;
    }

    public int getProductId(){
        return this.productId;
    } // will provide item classes with product ID

    public int getDiscountCode(){
        return this.discountCode;
    } // will provide item classes with discount code

    public Item oneOf() {
        return new ItemByUnit(this);
    }
}
