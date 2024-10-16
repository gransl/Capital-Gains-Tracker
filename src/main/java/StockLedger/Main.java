package StockLedger;

public class Main {
    public static void main(String[] args) {
        StockLedger myLedger = new StockLedger();

        myLedger.buy("AAPL", 20, 45.0);
        myLedger.buy("AAPL", 20, 75.0);
        myLedger.buy("MSFT", 20, 95.0);
        System.out.println(myLedger);
        myLedger.sell("AAPL", 30, 65);
        System.out.println(myLedger);
        myLedger.sell("AAPL", 10, 65);
        System.out.println(myLedger);
        myLedger.buy("AAPL", 100, 20.0);
        myLedger.buy("AAPL", 20, 24.0);
        myLedger.buy("TSLA", 200,36.0);
        System.out.println(myLedger);
        myLedger.sell("AAPL", 10, 65);
        System.out.println(myLedger);
        myLedger.sell("TSLA", 150, 30);
        System.out.println(myLedger);
        myLedger.buy("MSFT", 5, 60);
        myLedger.buy("MSFT", 5, 70);
        System.out.println(myLedger);
        myLedger.sell("MSFT", 4, 30);
        System.out.println(myLedger);
        myLedger.sell("MSFT", 2, 30);
        System.out.println(myLedger);






    }
}