package state;

import common.Logger;

public class PaidOrderState implements OrderState {
    @Override
    public void next(OrderContext context) {
        Logger.logInfo("Order moved from Paid to Shipped.");
        context.setState(new ShippedOrderState());
    }

    @Override
    public void cancel(OrderContext context) {
        Logger.logInfo("Order cancelled from Paid state.");
        context.setState(new CancelledOrderState());
    }

    @Override
    public String getStatus() {
        return "Paid";
    }
}
