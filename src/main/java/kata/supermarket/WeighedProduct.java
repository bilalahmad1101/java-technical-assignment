package kata.supermarket;

import java.math.BigDecimal;

public class WeighedProduct {

    private final int productId;
    private final BigDecimal pricePerKilo;
    private final int discountCode;

    public WeighedProduct(final int productId, final BigDecimal pricePerKilo, final int discountCode) {
        this.productId = productId;
        this.pricePerKilo = pricePerKilo;
        this.discountCode = discountCode;
    }

    public int getProductId(){
        return this.productId;
    } // will provide item classes with product ID

    public BigDecimal pricePerKilo() { //had missing public keyword - even though all classes are in the same package (no need for non-public)
        return pricePerKilo;
    }

    public int getDiscountCode(){
        return this.discountCode;
    } // will provide item classes with discount code

    public Item weighing(final BigDecimal kilos) {
        return new ItemByWeight(this, kilos);
    }
}
