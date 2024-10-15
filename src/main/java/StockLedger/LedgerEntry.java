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
        purchase = new LinkedDeque<>();
    }


    public void addPurchaseFront(StockPurchase stock)  {
        purchase.addToFront(stock);
    }

    public void addPurchaseBack(StockPurchase stock) {
        purchase.addToBack(stock);
    }

    public StockPurchase removePurchaseFront() {
        return purchase.removeFront();
    }

    public StockPurchase removePurchaseBack() {
        return purchase.removeBack();
    }

    public int size() {
        return purchase.size();
    }

    /**
     * This method determines if a ticker symbol matches a LedgerEntry
     * @return true if a ticker symbol matches LedgerEntry, false if not
     */
    public boolean matchesSymbol(String stockSymbol) {
        if (this.symbol.equals(stockSymbol)) {
            return true;
        } else {
            return false;
        }
    }
}
