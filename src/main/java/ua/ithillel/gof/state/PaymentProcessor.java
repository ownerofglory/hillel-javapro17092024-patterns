package ua.ithillel.gof.state;

import java.time.LocalDateTime;

public class PaymentProcessor implements OrderProcessor {
    @Override
    public void process(Order order) {
       if (order.getState() == OrderState.CREATED) {
           Double sum = order.getProducts()
                   .stream()
                   .reduce(0.0, (acc, product) -> acc + product.getPrice(), Double::sum);

           System.out.println("Executing payment " + sum  + " $");

           order.setDatePayed(LocalDateTime.now());

           order.setState(OrderState.PAYED);
       }

    }
}
