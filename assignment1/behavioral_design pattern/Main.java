import observer.*;
import state.*;
import common.Logger;

public class Main {
    public static void main(String[] args) {
        Logger.logInfo("=== OBSERVER PATTERN DEMO ===");
        StockMarket market = new StockMarket();
        Investor investor1 = new Investor("Alice");
        Investor investor2 = new Investor("Bob");

        market.attach(investor1);
        market.attach(investor2);

        market.updateStock("AAPL", 150.25);
        market.updateStock("GOOG", 2800.75);

        market.detach(investor1);
        market.updateStock("MSFT", 299.99);

        Logger.logInfo("=== STATE PATTERN DEMO ===");
        OrderContext order = new OrderContext();
        Logger.logInfo("Current Status: " + order.getStatus());

        order.next();
        Logger.logInfo("Current Status: " + order.getStatus());

        order.next();
        Logger.logInfo("Current Status: " + order.getStatus());

        order.cancel();
        Logger.logInfo("Current Status: " + order.getStatus());
    }
}
