package kata.supermarket;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiscountCalculatorTest {
    @Test
    public void discountCalculatedHasExpectedDiscount () { // This test will validate that overall the correct discount has been applied
        BigDecimal expectedDiscount = new BigDecimal("3.33");
        List<Item> list = new ArrayList<Item>();// The test list of products (the basket)
        list.add(new Product(102, new BigDecimal("1.49"), 1).oneOf()); //discount code 1 - BOGOF
        list.add(new Product(102, new BigDecimal("1.49"), 1).oneOf());
        list.add(new Product(101, new BigDecimal("0.49"), 1).oneOf());
        list.add(new Product(102, new BigDecimal("1.49"), 1).oneOf());
        list.add(new Product(101, new BigDecimal("0.49"), 1).oneOf());
        list.add(new Product(103, new BigDecimal("0.80"), 2).oneOf()); //discount code 2 - B2F1
        list.add(new Product(105, new BigDecimal("0.90"), 2).oneOf());
        list.add(new Product(104, new BigDecimal("0.85"), 2).oneOf());
        list.add(new Product(103, new BigDecimal("0.80"), 2).oneOf());
        DiscountCalculator discountCalculator = new DiscountCalculator(list);
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

    @Test
    public void calculateBuyTwoItemsForOnePoundDiscountEvenTest(){ // This test will validate the B2F1 discount functionality works with an even basket
        BigDecimal expectedValue = new BigDecimal("1.35");
        List<Item> list = new ArrayList<Item>();
        //basket items are different items as this pricing model can apply to multiple items
        list.add(new Product(115, new BigDecimal("0.70"), 2).oneOf());
        list.add(new Product(115, new BigDecimal("0.70"), 2).oneOf());
        list.add(new Product(115, new BigDecimal("0.70"), 2).oneOf());
        list.add(new Product(103, new BigDecimal("0.80"), 2).oneOf());
        list.add(new Product(102, new BigDecimal("0.75"), 2).oneOf());
        list.add(new Product(104, new BigDecimal("0.70"), 2).oneOf());
        List<BigDecimal> priceList = new ArrayList<>();
        for(Item item: list){
            priceList.add(item.getPrice());
        }
        DiscountCalculator discountCalculator = new DiscountCalculator(list);
        assertEquals(expectedValue, discountCalculator.calculateBuyTwoItemsForOnePoundDiscount(priceList));
    }

    @Test
    public void calculateBuyTwoItemsForOnePoundDiscountOddTest(){ // This test will validate the B2F1 discount functionality works with an odd basket
        BigDecimal expectedValue = new BigDecimal("1.35");
        List<Item> list = new ArrayList<Item>();
        list.add(new Product(115, new BigDecimal("0.70"), 2).oneOf());
        list.add(new Product(115, new BigDecimal("0.70"), 2).oneOf());
        list.add(new Product(115, new BigDecimal("0.70"), 2).oneOf());
        list.add(new Product(103, new BigDecimal("0.80"), 2).oneOf());
        list.add(new Product(102, new BigDecimal("0.75"), 2).oneOf());
        list.add(new Product(150, new BigDecimal("0.99"), 2).oneOf());
        list.add(new Product(104, new BigDecimal("0.70"), 2).oneOf());
        List<BigDecimal> priceList = new ArrayList<>();
        for(Item item: list){
            priceList.add(item.getPrice());
        }
        DiscountCalculator discountCalculator = new DiscountCalculator(list);
        assertEquals(expectedValue, discountCalculator.calculateBuyTwoItemsForOnePoundDiscount(priceList));
    }
}
