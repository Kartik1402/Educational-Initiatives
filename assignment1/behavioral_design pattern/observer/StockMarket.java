package observer;

import common.Logger;
import java.util.ArrayList;
import java.util.List;

public class StockMarket implements Subject {
    private final List<Observer> observers = new ArrayList<>();

    public void attach(Observer observer) {
        if (observer == null) {
            Logger.logError("Null observer cannot be attached.");
            return;
        }
        observers.add(observer);
        Logger.logInfo("Observer attached successfully.");
    }

    public void detach(Observer observer) {
        observers.remove(observer);
        Logger.logInfo("Observer detached successfully.");
    }

    public void notifyObservers(String stockSymbol, double price) {
        for (Observer observer : observers) {
            try {
                observer.update(stockSymbol, price);
            } catch (Exception ex) {
                Logger.logError("Error notifying observer: " + ex.getMessage());
            }
        }
    }

    public void updateStock(String stockSymbol, double price) {
        Logger.logInfo("Stock update: " + stockSymbol + " is now " + price);
        notifyObservers(stockSymbol, price);
    }
}
