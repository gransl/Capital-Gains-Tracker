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
        if (symbol == null || symbol.isEmpty()) {
            throw new IllegalArgumentException("Must enter ticker symbol");
        }
        this.symbol = symbol;
        purchase = new LinkedDeque<>();
    }

    /**
     * Retrieves the ticker symbol associated with this LedgerEntry
     *
     * @return ticker symbol
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * adds StockPurchase to the front of the LedgerEntry
     *
     * @param stock stock being added
     * @throws IllegalArgumentException if the ticker symbol on the stock doesn't match the symbol of the ledger
     */
    public void addPurchaseFront(StockPurchase stock)  {
        if (!stock.getSymbol().equals(symbol)) {
            throw new IllegalArgumentException("Stock doesn't match Ledger.");
        }
        purchase.addToFront(stock);
    }

    /**
     * adds StockPurchase to the back of the LedgerEntry
     *
     * @param stock stock being added
     */
    public void addPurchaseBack(StockPurchase stock) {
        if (!stock.getSymbol().equals(symbol)) {
            throw new IllegalArgumentException("Stock doesn't match Ledger.");
        }
        purchase.addToBack(stock);
    }


    /**
     * Removes and returns a StockPurchase from front of LedgerEntry.
     *
     * @return StockPurchase at back of LedgerEntry
     */
    public StockPurchase removePurchaseFront() {
        return purchase.removeFront();
    }

    /**
     * Removes and returns a StockPurchase from back of LedgerEntry.
     *
     * @return StockPurchase at back of LedgerEntry
     */
    public StockPurchase removePurchaseBack() {
        return purchase.removeBack();
    }

    /**
     * Retrieves StockPurchase at front of LedgerEntry (without removing).
     *
     * @return StockPurchase at front of LedgerEntry
     */
    public StockPurchase getFront() {
        return purchase.getFront();
    }

    /**
     * Retrieves StockPurchase at back of LedgerEntry (without removing).
     *
     * @return StockPurchase at back of LedgerEntry
     */
    public StockPurchase getBack() {
        return purchase.getBack();
    }

    /**
     * Determines if LedgerEntry is empty.
     *
     * @return true if LedgerEntry is empty, false otherwise
     */
    public boolean isEmpty() {
        return purchase.isEmpty();
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
     * Retrieves an iterator to iterate through LedgerEntry.
     *
     * @return Returns an iterator for use.
     */
    public Iterator<StockPurchase> getIterator() {
        return purchase.getIterator();
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
