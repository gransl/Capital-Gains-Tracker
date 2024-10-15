public interface StockLedgerInterface {

    /** Records a stock purchase in this ledger.
     @param stockSymbol    The stock's symbol.
     @param sharesBought   The number of shares purchased.
     @param pricePerShare  The price per share. */
    public void buy(String stockSymbol, int sharesBought, double pricePerShare);

    /** Removes from this ledger any shares of a particular stock
     that were sold and computes the capital gain or loss.
     @param stockSymbol    The stock's symbol.
     @param sharesSold     The number of shares sold.
     @param pricePerShare  The price per share.
     @return  The capital gain (loss). */
    public double sell(String stockSymbol, int sharesSold, double pricePerShare);

    /** Returns a boolean on whether the passed in stock symbol is contained in the ledger.
     @param stockSymbol    The stock's symbol.
     @return  Boolean of if the stock exists in the ledger. */
    public boolean contains(String stockSymbol);

    /** Returns a LedgerEntry object based on stock symbol.
     @param stockSymbol    The stock's symbol.
     @return  LedgerEntry object of stock symbol. */
    public LedgerEntry getEntry(String stockSymbol);
}
