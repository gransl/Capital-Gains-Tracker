package StockLedger;

import Deque.LinkedDeque;

import java.util.Iterator;

/**
 * Holds Stock Purchase Objects for a single ticker symbol
 * @author Sandra Gran
 * @version 10-15-2024
 */
public class LedgerEntry {
    /** deque that holds each StockPurchase */
    private LinkedDeque<StockPurchase> purchase;
    /** the stock ticker symbol for this LedgerEntry */
    private String symbol;

    /**
     * Constructor for LedgerEntry object.
     *
     * @param symbol ticker symbol for stock, must not be empty or null.
     */
    public LedgerEntry(String symbol) {
        if (symbol.isEmpty() || symbol == null) {
            throw new IllegalArgumentException("Must enter ticker symbol");
        }
        this.symbol = symbol;
        purchase = new LinkedDeque<>();
    }

    /**
     * adds StockPurchase to the front of the LedgerEntry
     *
     * @param stock stock being added
     */
    public void addPurchaseFront(StockPurchase stock)  {
        purchase.addToFront(stock);
    }

    /**
     * adds StockPurchase to the back of the LedgerEntry
     *
     * @param stock stock being added
     */
    public void addPurchaseBack(StockPurchase stock) {
        purchase.addToBack(stock);
    }


    /**
     * removes and returns a StockPurchase from front of LedgerEntry
     *
     * @return StockPurchase
     */
    public StockPurchase removePurchaseFront() {
        return purchase.removeFront();
    }

    /**
     * removes and returns a StockPurchase from back of LedgerEntry
     *
     * @return StockPurchase
     */
    public StockPurchase removePurchaseBack() {
        return purchase.removeBack();
    }

    /**
     * Retrieves an iterator to iterate through LedgerEntry.
     *
     * @return Returns an iterator for use.
     */
    public Iterator<StockPurchase> getIterator() {
        return purchase.getIterator();
    }


    /**
     * Retrieves the number of stocks currently in the LedgerEntry
     *
     * @return number of stocks in LedgerEntry
     */
    public int size() {
        return purchase.size();
    }

    /**
     * This method determines if a ticker symbol matches a LedgerEntry
     *
     * @param stockSymbol ticker symbol for stock
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
