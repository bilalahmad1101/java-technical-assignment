package kata.supermarket;

import java.math.BigDecimal;

public class ItemByUnit implements Item {

    private final Product product;

    public ItemByUnit(final Product product) {
        this.product = product;
    }

    public BigDecimal getPrice() {
        return product.pricePerUnit();
    }

    public int getDiscountCode(){return product.getDiscountCode();}

    public int getProductId(){return product.getProductId();}
}
