package state;

import common.Logger;

public class ShippedOrderState implements OrderState {
    @Override
    public void next(OrderContext context) {
        Logger.logInfo("Order moved from Shipped to Delivered.");
        context.setState(new DeliveredOrderState());
    }

    @Override
    public void cancel(OrderContext context) {
        Logger.logInfo("Cannot cancel an order that has already shipped.");
    }

    @Override
    public String getStatus() {
        return "Shipped";
    }
}
