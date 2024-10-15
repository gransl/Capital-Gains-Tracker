package StockLedger;

import java.util.ArrayList;

public class StockLedger implements StockLedgerInterface {
    private ArrayList<LedgerEntry> stocks;
    private double expenditures;
    private double revenue;
    private double profit;

    public StockLedger() {
        stocks = new ArrayList<>();
        expenditures = 0.0;
        revenue = 0.0;
        profit = 0.0;
    }

    public StockLedger(LedgerEntry stockBuy) {
        stocks = new ArrayList<>();
        expenditures = 0.0;
        revenue = 0.0;
        profit = 0.0;
    }

    /**
     * Records a stock purchase in this ledger.
     *
     * @param stockSymbol   The stock's symbol.
     * @param sharesBought  The number of shares purchased.
     * @param pricePerShare The price per share.
     */
    @Override
    public void buy(String stockSymbol, int sharesBought, double pricePerShare) {
        StockPurchase tempBuy = new StockPurchase(stockSymbol, pricePerShare);
        for (int i = 0; i < sharesBought; i++) {
            getEntry(stockSymbol).addPurchaseFront(tempBuy);
        }
        expenditures += sharesBought * pricePerShare;
    }

    /**
     * Removes from this ledger any shares of a particular stock
     * that were sold and computes the capital gain or loss.
     *
     * @param stockSymbol   The stock's symbol.
     * @param sharesSold    The number of shares sold.
     * @param pricePerShare The price per share.
     * @return The capital gain (loss).
     */
    @Override
    public double sell(String stockSymbol, int sharesSold, double pricePerShare) {
        return 0;
    }

    /**
     * Returns a boolean on whether the passed in stock symbol is contained in the ledger.
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

        // no existing ledger found, make new one
        LedgerEntry newLedger = new LedgerEntry(stockSymbol);
        return newLedger;
    }

}

