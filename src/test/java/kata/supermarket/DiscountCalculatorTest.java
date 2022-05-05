package kata.supermarket;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiscountCalculatorTest {
    @Test
    public void discountCalculatedHasExpectedDiscount () { // This test will validate that overall the correct discount has been applied
        BigDecimal expectedDiscount = new BigDecimal("1.98");
        List<Item> basket = new ArrayList<Item>(); // The test list of products (the basket)
        basket.add(new Product(102, new BigDecimal("1.49"), 1).oneOf());
        basket.add(new Product(102, new BigDecimal("1.49"), 1).oneOf());
        basket.add(new Product(101, new BigDecimal("0.49"), 1).oneOf());
        basket.add(new Product(102, new BigDecimal("1.49"), 1).oneOf());
        basket.add(new Product(101, new BigDecimal("0.49"), 1).oneOf());
        DiscountCalculator discountCalculator = new DiscountCalculator(basket);
        assertEquals(expectedDiscount, discountCalculator.calculateAndRetrieveDiscount());
    }


    @Test
    public void calculateBuyOneGetOneFreeDiscountEvenTest(){ // This test will validate the BOGOF discount functionality works with an even basket
        BigDecimal expectedValue = new BigDecimal("0.98");
        List<Item> basket = new ArrayList<Item>();
        basket.add(new Product(102, new BigDecimal("0.49"), 1).oneOf());
        basket.add(new Product(102, new BigDecimal("0.49"), 1).oneOf());
        basket.add(new Product(102, new BigDecimal("0.49"), 1).oneOf());
        basket.add(new Product(102, new BigDecimal("0.49"), 1).oneOf());
        DiscountCalculator discountCalculator = new DiscountCalculator(basket);
        assertEquals(expectedValue, discountCalculator.calculateBuyOneGetOneFreeDiscount(new BigDecimal("0.49"), 4)); // the function will take price and occurrence of an item to calculate the BOGOF dicount
    }

    @Test
    public void calculateBuyOneGetOneFreeDiscountOddTest(){ // // This test will validate the BOGOF discount functionality works with an odd basket
        BigDecimal expectedValue = new BigDecimal("0.98");
        List<Item> basket = new ArrayList<Item>();
        basket.add(new Product(102, new BigDecimal("0.49"), 1).oneOf());
        basket.add(new Product(102, new BigDecimal("0.49"), 1).oneOf());
        basket.add(new Product(102, new BigDecimal("0.49"), 1).oneOf());
        basket.add(new Product(102, new BigDecimal("0.49"), 1).oneOf());
        basket.add(new Product(102, new BigDecimal("0.49"), 1).oneOf());
        DiscountCalculator discountCalculator = new DiscountCalculator(basket);
        assertEquals(expectedValue, discountCalculator.calculateBuyOneGetOneFreeDiscount(new BigDecimal("0.49"), 5));
    }
}
