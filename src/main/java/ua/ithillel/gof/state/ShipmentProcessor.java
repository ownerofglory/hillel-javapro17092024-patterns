package ua.ithillel.gof.state;

public class ShipmentProcessor implements OrderProcessor {
    @Override
    public void process(Order order) {
        switch (order.getState()) {
            case PAYED -> {
                System.out.println("Order processed: " + order);
                System.out.println("delivery to " + order.getDeliveryAddress());
                order.setState(OrderState.IN_DELIVERY);
            }
            case DELIVERED -> {
                System.out.println("Alredy delivered: " + order);
            }
            case CREATED -> {
                System.out.println("No payment; cannot deliver");
            }
        }
    }
}
