package model;

import enum1.OrderStatus;
import enum1.FoodCategory;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private String orderId;
    private Customer customer;
    private List<OrderItem> items;
    private OrderStatus status;
    private LocalDate orderDate;

    // Static Nested Class (W11-12)
    public static class OrderItem {
        private final MenuItem menuItem;
        private final int quantity;

        public OrderItem(MenuItem menuItem, int quantity) {
            this.menuItem = menuItem;
            this.quantity = quantity;
        }

        public double getTotal() {
            return menuItem.getPrice() * quantity;
        }

        @Override
        public String toString() {
            return String.format("  • %s x%d = Rp%.0f",
                    menuItem.getName(), quantity, getTotal());
        }
    }

    private static final DateTimeFormatter DF = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final double DISCOUNT_LIMIT = 50000;

    public Order(String orderId, Customer customer) {
        this.orderId = orderId.trim().toUpperCase();
        this.customer = customer;
        this.items = new ArrayList<>();
        this.status = OrderStatus.PENDING;
        this.orderDate = LocalDate.now();
    }

    public void addItem(MenuItem item, int qty) {
        if (status == OrderStatus.PAID) {
            throw new IllegalStateException("PAID order tidak bisa diubah!");
        }
        items.add(new OrderItem(item, qty));
    }

    public double calculateTotal() {
        double subtotal = items.stream().mapToDouble(OrderItem::getTotal).sum();

        // Category discount
        double catDiscount = items.stream()
                .mapToDouble(item -> item.menuItem.getCategory().getDiscount() * item.getTotal())
                .sum();

        // Volume discount > 50k
        double volDiscount = subtotal > DISCOUNT_LIMIT ? subtotal * 0.1 : 0;

        return subtotal - catDiscount - volDiscount;
    }

    public void setPaid() {
        this.status = OrderStatus.PAID;
    }

    // DateTime API (W9-10)
    public String getOrderDateStr() {
        return orderDate.format(DF);
    }

    public long daysOld() {
        return ChronoUnit.DAYS.between(orderDate, LocalDate.now());
    }

    // Getters
    public String getOrderId() { return orderId; }
    public Customer getCustomer() { return customer; }
    public List<OrderItem> getItems() { return items; }
    public OrderStatus getStatus() { return status; }

    @Override
    public String toString() {
        return String.format("📋 Order #%s | %s | %s | Umur: %d hari",
                orderId, customer.getName(), status, daysOld());
    }
}