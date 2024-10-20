package StockLedger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * StockLedger holds a record of bought and sold stocks.
 * @author Sandra Gran
 * @version 10-15-24
 */
public class StockLedger implements StockLedgerInterface {
    /** holds a record of each stock's current stock holdings */
    private ArrayList<LedgerEntry> stocks;
    /** total capital gains made from all stock sales*/
    private double totalCapitalGains;

    /**
     * Creates a new empty stock ledger
     */
    public StockLedger() {
        stocks = new ArrayList<>();
        totalCapitalGains = 0.0;
    }


    /**
     * Records a stock purchase in this ledger.
     * Time Complexity: O(n) where n = sharesBought
     *
     * @param stockSymbol   The stock's symbol.
     * @param sharesBought  The number of shares purchased.
     * @param pricePerShare The price per share.
     */
    @Override
    public void buy(String stockSymbol, int sharesBought, double pricePerShare) {
        StockPurchase tempBuy = new StockPurchase(stockSymbol, pricePerShare);
        LedgerEntry tempLedger = getEntry(stockSymbol);
        stocks.remove(tempLedger);
        for (int i = 0; i < sharesBought; i++) {
            tempLedger.addPurchaseBack(tempBuy);
        }
        stocks.add(tempLedger);
    }

    /**
     * Removes from this ledger any shares of a particular stock that were sold and computes the capital gain or loss.
     * Time Complexity: O(n) where n = sharesSold
     *
     * @param stockSymbol   The stock's symbol.
     * @param sharesSold    The number of shares sold.
     * @param pricePerShare The price per share.
     * @return The capital gain (loss).
     */
    @Override
    public double sell(String stockSymbol, int sharesSold, double pricePerShare) {
        LedgerEntry ledger = getEntry(stockSymbol);
        int ledgerSize = ledger.size();

        if (ledgerSize == 0) {
            stocks.remove(ledger);
            throw new IllegalArgumentException("You do not have any shares of " + stockSymbol + ".");
        } else if (sharesSold > ledgerSize) {
            throw new IllegalArgumentException("You only have " + ledgerSize + " " + stockSymbol + " share(s).");
        }

        double buyTotal = 0;
        double capitalGain = 0;

        stocks.remove(ledger);
        StockPurchase tempPurchase;

        for (int i = 0; i < sharesSold; i++) {
            tempPurchase = ledger.removePurchaseFront();
            buyTotal += tempPurchase.getCost();
        }

        stocks.add(ledger);

        capitalGain = sharesSold * pricePerShare - buyTotal;
        totalCapitalGains += capitalGain;

        return capitalGain;
    }

    /**
     * Removes from this ledger any shares of a particular stock that were sold and computes the capital gain or loss.
     * Contains additional logic to optimize each sale for highest capital gains
     *
     * Time Complexity: O(n) where n = sharesSold
     *
     * @param stockSymbol   The stock's symbol.
     * @param sharesSold    The number of shares sold.
     * @param pricePerShare The price per share.
     * @return The capital gain (loss).
     */
    public double sellOptimal(String stockSymbol, int sharesSold, double pricePerShare) {
        LedgerEntry ledger = getEntry(stockSymbol);
        int ledgerSize = ledger.size();

        if (ledgerSize == 0) {
            stocks.remove(ledger);
            throw new IllegalArgumentException("You do not have any shares of " + stockSymbol + ".");
        } else if (sharesSold > ledgerSize) {
            throw new IllegalArgumentException("You only have " + ledgerSize + " " + stockSymbol + " share(s).");
        }

        stocks.remove(ledger);
        StockPurchase tempPurchase;

        double buyTotal = 0;
        double capitalGain = 0;

        for (int i = 0; i < sharesSold; i++) {
            if (ledger.getFront().getCost() <= ledger.getBack().getCost()) {
                tempPurchase = ledger.removePurchaseFront();
            } else {
                tempPurchase = ledger.removePurchaseBack();
            }
            buyTotal += tempPurchase.getCost();
        }

        stocks.add(ledger);

        capitalGain = (sharesSold * pricePerShare) - buyTotal;
        totalCapitalGains += capitalGain;

        return capitalGain;
    }

    /**
     * Returns a boolean on whether the passed in stock symbol is contained in the ledger.
     * Time Complexity: O(n) where n = number of LedgerEntry in StockLedger
     *
     * @param stockSymbol The stock's symbol.
     * @return Boolean of if the stock exists in the ledger.
     */
    @Override
    public boolean contains(String stockSymbol) {
        for (LedgerEntry ledger : stocks) {
            if (ledger.matchesSymbol(stockSymbol)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a LedgerEntry object based on stock symbol.
     * Time Complexity: O(n) where n = number of LedgerEntry in StockLedger
     *
     * @param stockSymbol The stock's symbol.
     * @return LedgerEntry object of stock symbol.
     */
    @Override
    public LedgerEntry getEntry(String stockSymbol) {
        for (LedgerEntry ledger : stocks) {
            if (ledger.matchesSymbol(stockSymbol)) {
                return ledger; // return early with ledger
            }
        }

        // no existing ledger found, make new one and it to the Stock Ledger
        LedgerEntry newLedger = new LedgerEntry(stockSymbol);
        stocks.add(newLedger);
        return newLedger;
    }


    /**
     * Returns a string representation of the StockLedger.
     *
     * @return string of the StockLedger.
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("---- Stock Ledger ----\n");

        for (LedgerEntry ledger : stocks) {
            Iterator<StockPurchase> stockItr = ledger.getIterator();
            if (stockItr.hasNext()) {
                StockPurchase tempStock = stockItr.next();
                int currentShares = 1;
                double currentCost = tempStock.getCost();
                str.append(tempStock.getSymbol());
                str.append(": ");
                while (stockItr.hasNext()) {
                    tempStock = stockItr.next();
                    if (currentCost != tempStock.getCost()) {
                        str.append(stockString(currentCost, currentShares));
                        currentCost = tempStock.getCost();
                        currentShares = 1;
                    } else {
                        currentShares++;
                    }
                }


                //append shares of final share price to end
                str.append(stockString(currentCost, currentShares));
                int length = str.length();
                str.delete(length - 2, length - 1);
                str.append("\n");
            }
        }


        String stockLedgerString = str.toString();
        return stockLedgerString;
    }

    /**
     *  Private helper method of toString. Creates a string with current costs and shares with a specific format.
     *
     * @param currentCost cost of current stock being reviewed
     * @param currentShares number of shares of current share being reviewed
     * @return string with current cost and share of current stock
     */
    private String stockString(double currentCost, int currentShares) {
        StringBuilder str = new StringBuilder("");
        str.append(currentCost);
        str.append(" (");
        str.append(currentShares);
        str.append(" shares), ");
        String stockString = str.toString();
        return stockString;
    }


    /**
     * returns current total capital gains for all stock sells
     *
     * @return total capital gains
     */
    public double getCapitalGains() {
        return totalCapitalGains;
    }

}

