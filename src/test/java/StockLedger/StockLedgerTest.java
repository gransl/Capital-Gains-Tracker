package StockLedger;

import org.junit.jupiter.api.BeforeAll;
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
    }

    @Test
    void sell() {
        StockLedger ledger1 = new StockLedger();
        ledger1.buy("PLAY", 10, 30.0);
        ledger1.buy("CAKE", 20, 50.0);
        ledger1.buy("HOG", 30, 60);
        assertEquals(100, ledger1.sell("PLAY", 5, 50));
        assertEquals(-200, ledger1.sell("CAKE", 20, 40.0));
        assertThrows(IllegalArgumentException.class, () -> ledger1.sell("HOG", 40, 70));
        assertThrows(IllegalArgumentException.class, () -> ledger1.sell("AAPL", 15, 36));
    }
}