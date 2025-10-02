package observer;

import common.Logger;

public class Investor implements Observer {
    private final String name;

    public Investor(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Investor name cannot be null or empty.");
        }
        this.name = name;
    }

    public void update(String stockSymbol, double price) {
        Logger.logInfo("Investor " + name + " notified: " + stockSymbol + " is now " + price);
    }
}
