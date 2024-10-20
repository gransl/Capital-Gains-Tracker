package StockLedger;

/**
 * StockPurchase objects holds a single stock purchase
 *
 * @author Sandra Gran
 * @version 10-15-2024
 */
public class StockPurchase {
    /** Ticker symbol for stock being purchased, final because this shouldn't be altered */
    private final String symbol;
    /** Stock price, final because this shouldn't be altered  */
    private final double cost;


    /**
     * Full Constructor: creates a StockPurchase Object
     *
     * @param symbol ticker symbol, must not be empty or null
     * @param cost stock price, must be greater than 0
     */
    public StockPurchase(String symbol, double cost) {
       if (symbol == null || symbol.isEmpty()) {
           throw new IllegalArgumentException("Must enter ticker symbol");
       }
       this.symbol = symbol;
       if (cost <= 0) {
           throw new IllegalArgumentException("Stock price must be greater than 0");
       }
       this.cost = cost;
    }


    /**
     * retrieves the ticker symbol for the stock
     *
     * @return ticker symbol for stock
     */
    public String getSymbol() {
        return symbol;
    }


    /**
     * retrieves the stock price for this purchase
     *
     * @return the stock price
     */
    public double getCost() {
        return cost;
    }
}
