package StockLedger;

import Deque.LinkedDeque;

/**
 * Holds Stock Purchase Objects for a single ticker symbol
 */
public class LedgerEntry {
    /** deque that holds each StockPurchase */
    private LinkedDeque<StockPurchase> purchase;
    /** the stock ticker symbol for this LedgerEntry */
    private String symbol;

    public LedgerEntry(String symbol) {
        if (symbol.isEmpty()) {
            throw new IllegalArgumentException("Must enter ticker symbol");
        }
        this.symbol = symbol;
    }

    public LedgerEntry(StockPurchase stockBuy) {
        purchase = new LinkedDeque<>();
        purchase.addToFront(stockBuy);
        symbol = purchase.getFront().getSymbol();
    }

    public String getSymbol() {
        return symbol;
    }

    public void addPurchaseFront(StockPurchase stockBuy)  {
        purchase.addToFront(stockBuy);
    }

    public void addPurchaseBack(StockPurchase stockBuy) {
        purchase.addToBack(stockBuy);
    }

    /**
     * This method determines if a ticker symbol matches a stock purchase
     * @return true if a ticker symbol matches the StockPurchase, false if not
     */
    public boolean matchesSymbol(String stockSymbol) {
        if (this.symbol.equals(stockSymbol)) {
            return true;
        } else {
            return false;
        }
    }
}
