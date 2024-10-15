package StockLedger;

/**
 * StockPurchase objects holds a single stock purchase
 *
 * @author Sandra Gran
 * @version 10-15-2024
 */
public class StockPurchase {

    /** Ticker symbol for stock being purchased */
    private String symbol;
    /** Stock price */
    private double cost;

    public StockPurchase(String symbol, double cost) {
       if (symbol.isEmpty()) {
           throw new IllegalArgumentException("Must enter ticker symbol");
       }
       this.symbol = symbol;
       if (cost <= 0) {
           throw new IllegalArgumentException("Stock price must be greater than $0");
       }
       this.cost = cost;
    }


    /** retrieves the ticker symbol for the stock
     * @return ticker symbol for stock
     */
    public String getSymbol() {
        return symbol;
    }

    /** retrieves the stock price for this purchase
     * @return the stock price
     */
    public double getCost() {
        return cost;
    }

}
