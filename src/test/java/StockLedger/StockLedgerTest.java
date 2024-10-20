package StockLedger;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StockLedgerTest {

    @Test
    void buyAndContainsAndGetEntry() {
        StockLedger ledger1 = new StockLedger();
        ledger1.buy("PLAY", 10, 30.0);
        ledger1.buy("CAKE", 20, 50.0);
        assertTrue(ledger1.contains("PLAY"));
        assertTrue(ledger1.contains("CAKE"));
        assertFalse(ledger1.contains("AAPL"));
        assertThrows(IllegalArgumentException.class, () -> ledger1.buy("",10,30.0));
        assertThrows(IllegalArgumentException.class, () -> ledger1.buy(null,30,10.0));
        assertThrows(IllegalArgumentException.class, () -> ledger1.buy("HOG",10,-50.0));
    }

    @Test
    void sellAndGetCapitalGains() {
        StockLedger ledger1 = new StockLedger();
        ledger1.buy("PLAY", 10, 30.0);
        ledger1.buy("CAKE", 20, 50.0);
        ledger1.buy("HOG", 30, 60);
        assertEquals(100, ledger1.sell("PLAY", 5, 50));
        assertEquals(-200, ledger1.sell("CAKE", 20, 40.0));
        assertEquals(-100, ledger1.getCapitalGains());
        assertThrows(IllegalArgumentException.class, () -> ledger1.sell("HOG", 40, 70));
        assertThrows(IllegalArgumentException.class, () -> ledger1.sell("AAPL", 15, 36));
    }

    @Test
    void sellOptimal() {
        StockLedger ledger1 = new StockLedger();
        ledger1.buy("CAKE", 20, 50.0);
        ledger1.buy("CAKE", 20, 70.0);
        ledger1.buy("CAKE", 20, 20.0);
        ledger1.buy("HOG", 30, 60);
        assertEquals(600, ledger1.sellOptimal("CAKE", 20, 50));
        assertEquals(200, ledger1.sellOptimal("CAKE", 20, 60.0));
        assertThrows(IllegalArgumentException.class, () -> ledger1.sellOptimal("HOG", 40, 70));
        assertThrows(IllegalArgumentException.class, () -> ledger1.sellOptimal("AAPL", 15, 36));
    }

    @Test
    void testToString() {
        StockLedger ledger2 = new StockLedger();
        StockLedger emptyLedger = new StockLedger();
        ledger2.buy("PLAY", 20, 45.0);
        ledger2.buy("PLAY", 20, 75.0);
        ledger2.buy("CAKE", 20, 95.0);
        assertEquals("""
                ---- Stock Ledger ----
                PLAY: 45.0 (20 shares), 75.0 (20 shares)\s
                CAKE: 95.0 (20 shares)\s
                """, ledger2.toString());
        ledger2.sell("PLAY", 30, 65);
        assertEquals("""
                ---- Stock Ledger ----
                CAKE: 95.0 (20 shares)\s
                PLAY: 75.0 (10 shares)\s
                """, ledger2.toString());
        ledger2.sell("PLAY", 10, 65);
        assertEquals("""
                ---- Stock Ledger ----
                CAKE: 95.0 (20 shares)\s
                """, ledger2.toString());
        assertEquals("---- Stock Ledger ----\n", emptyLedger.toString());
    }

    @Test
    void extraLedgerEntryTests() {
        LedgerEntry entry1 = new LedgerEntry("CAKE");
        assertTrue(entry1.isEmpty());
        assertEquals("CAKE", entry1.getSymbol());
        entry1.addPurchaseFront(new StockPurchase("CAKE", 22.0));
        assertEquals(22.0, entry1.getFront().getCost());
        StockPurchase badPurchase = new StockPurchase("HOG", 22.0);
        assertThrows(IllegalArgumentException.class, () -> new LedgerEntry(null));
        assertThrows(IllegalArgumentException.class, () -> new LedgerEntry(""));
        assertThrows(IllegalArgumentException.class, () -> entry1.addPurchaseFront(badPurchase));
        assertThrows(IllegalArgumentException.class, () -> entry1.addPurchaseBack(badPurchase));
    }

}