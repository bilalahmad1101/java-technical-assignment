package kata.supermarket;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class DiscountCalculator {

    /***
     * Used hashmaps here to avoid repeated exhaustive search and reduce time complexity.
     * The sizes of these maps will be the same so space complexity isnt increased
     * this could be refactored but given time constraints I will stick with this.
     * the three hashmaps will store the following:
     * itemCountMap -> key: the productID (e.g. 101) Value: how many are in the basket (e.g.30)
     * itemDiscountMap -> key: the productID (e.g. 101) Value: The product Dicount Code (e.g.1)
     * itemPriceMap -> key: the productID (e.g. 101) Value: price of the product (e.g.0.49)
     */
    private final HashMap<Integer, Integer> itemCountMap;
    private final HashMap<Integer, Integer> itemDiscountMap;
    private final HashMap<Integer, BigDecimal> itemPriceMap;
    private final List<Item> basket;

    public DiscountCalculator(List<Item> basket){
        //initialises all attributes of class then indexes item Metadata for the maps
        this.basket = basket;
        this.itemCountMap = new HashMap<Integer, Integer>();
        this.itemDiscountMap = new HashMap<Integer, Integer>();
        this.itemPriceMap = new HashMap<Integer, BigDecimal>();
        this.indexItemMetadataInMap();
    }


    public BigDecimal calculateAndRetrieveDiscount() { // calculates the total discount to apply
        BigDecimal discount = new BigDecimal("0");
        Set<Integer> productIds = getItemCountMap().keySet(); // retrieve list of all DISTINCT items
        for(Integer productId: productIds){ // traverses all distinct products
            //for maintainability, each set of metadata is retrieved from the 3 maps (for the DISTINCT product) and stored in visually readable variables
            BigDecimal itemPrice = getItemPriceMap().get(productId);
            int itemCount = getItemCountMap().get(productId);
            int itemDiscountCode = getItemDiscountMap().get(productId);
            switch(itemDiscountCode){ // each Distinct products discount will be checked and the relevant calculations will occur
                case 1:{ // discount code 1 - Buy One Get One Free
                    discount = discount.add(calculateBuyOneGetOneFreeDiscount(itemPrice, itemCount)); //BOGOF discount calculated and added to overall discount
                    break;
                }
            }
        }
        return discount;
    }


    public BigDecimal calculateBuyOneGetOneFreeDiscount(BigDecimal price, int count) { // calculates the BOGOF discount
        BigDecimal sum;
        if(count % 2 == 0){ // checks if the number of items are even
            sum = price.multiply(new BigDecimal(count)); // uses the entire amount of items
        } else{
            sum = price.multiply(new BigDecimal(count - 1)); // uses one item less as another must be picked up for a further discount
        }
        sum = sum.divide(new BigDecimal(2), 2, RoundingMode.CEILING); //halves the total sum - this is how much discount will need to applied
        return sum;
    }

    public void indexItemMetadataInMap(){ // This function will take all the items in the basket and map the correct metadata to the maps and counts the amount of each item
        for(Item item: getBasket()){ // loops through all items in bucket
            if(getItemCountMap().get(item.getProductId()) == null && getItemDiscountMap().get(item.getDiscountCode()) == null){ // checks if product is not already in maps
                getItemCountMap().put(item.getProductId(), 1); //assigns initial count to itemCountmap
                getItemDiscountMap().put(item.getProductId(), item.getDiscountCode()); // assigns price to itemDiscountMap for the product ID
                getItemPriceMap().put(item.getProductId(), item.getPrice()); //assigns price to itemPriceMap for the product ID
            }
            else{
                getItemCountMap().put(item.getProductId(), getItemCountMap().get(item.getProductId()) + 1); //increases count for the product Id in the itemCountMap
            }
        }
    }

    public HashMap<Integer,Integer> getItemDiscountMap(){
        return this.itemDiscountMap;
    }

    public List<Item> getBasket() {
        return basket;
    }

    public HashMap<Integer, Integer> getItemCountMap() {
        return itemCountMap;
    }

    public HashMap<Integer, BigDecimal> getItemPriceMap() {
        return itemPriceMap;
    }
}
