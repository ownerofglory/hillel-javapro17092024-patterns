package ua.ithillel.gof.state;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private List<Product> products;
    private LocalDateTime datePayed;
    private String deliveryAddress;

    private OrderState state = OrderState.CREATED;

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public LocalDateTime getDatePayed() {
        return datePayed;
    }

    public void setDatePayed(LocalDateTime datePayed) {
        this.datePayed = datePayed;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }
}
