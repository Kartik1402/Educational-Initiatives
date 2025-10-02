package state;

import common.Logger;

public class NewOrderState implements OrderState {
    @Override
    public void next(OrderContext context) {
        Logger.logInfo("Order moved from New to Paid.");
        context.setState(new PaidOrderState());
    }

    @Override
    public void cancel(OrderContext context) {
        Logger.logInfo("Order cancelled from New state.");
        context.setState(new CancelledOrderState());
    }

    @Override
    public String getStatus() {
        return "New";
    }
}
