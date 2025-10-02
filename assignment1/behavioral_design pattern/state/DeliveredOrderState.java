package state;

import common.Logger;

public class DeliveredOrderState implements OrderState {
    @Override
    public void next(OrderContext context) {
        Logger.logInfo("Order already delivered. No further transitions.");
    }

    @Override
    public void cancel(OrderContext context) {
        Logger.logInfo("Cannot cancel an order that has been delivered.");
    }

    @Override
    public String getStatus() {
        return "Delivered";
    }
}
