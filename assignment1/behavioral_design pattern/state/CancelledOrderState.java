package state;

import common.Logger;

public class CancelledOrderState implements OrderState {
    @Override
    public void next(OrderContext context) {
        Logger.logInfo("Cancelled orders cannot transition to another state.");
    }

    @Override
    public void cancel(OrderContext context) {
        Logger.logInfo("Order is already cancelled.");
    }

    @Override
    public String getStatus() {
        return "Cancelled";
    }
}
