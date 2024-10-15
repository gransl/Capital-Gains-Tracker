package StockLedger;

import java.util.ArrayList;

public class StockLedger implements StockLedgerInterface {
    private ArrayList<LedgerEntry> stocks;
    private double totalExpenditures;
    private double totalRevenue;
    private double totalCapitalGains;

    public StockLedger() {
        stocks = new ArrayList<>();
        totalExpenditures = 0.0;
        totalRevenue = 0.0;
        totalCapitalGains = 0.0;
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
        LedgerEntry tempLedger = getEntry(stockSymbol);
        stocks.remove(tempLedger);
        for (int i = 0; i < sharesBought; i++) {
            tempLedger.addPurchaseFront(tempBuy);
        }
        stocks.add(tempLedger);
        totalExpenditures += sharesBought * pricePerShare;
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

        totalRevenue = sharesSold * pricePerShare;
        capitalGain = sharesSold * pricePerShare - buyTotal;
        totalCapitalGains += capitalGain;

        return capitalGain;
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

        // no existing ledger found, make new one and it to the Stock Ledger
        LedgerEntry newLedger = new LedgerEntry(stockSymbol);
        stocks.add(newLedger);
        return newLedger;
    }

    /**
     * returns current total profit across all stock purchases and sells
     *
     * @return total profit
     */
    public double getProfit() {
        return totalRevenue - totalExpenditures;
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

