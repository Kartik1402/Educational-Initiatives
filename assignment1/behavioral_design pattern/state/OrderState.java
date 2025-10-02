package state;

public interface OrderState {
    void next(OrderContext context);
    void cancel(OrderContext context);
    String getStatus();
}
