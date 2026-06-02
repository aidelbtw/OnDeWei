// Order.java
import java.util.ArrayList;

public class Order {
    private static int idCounter = 1000;
    private String orderId;
    private String customerName;
    private int restaurantLocId;
    private int customerLocId;
    private ArrayList<OrderItem> items;

    public Order(String customerName, int restaurantLocId, int customerLocId, ArrayList<OrderItem> items) {
        this.orderId = "ORD" + (++idCounter);
        this.customerName = customerName;
        this.restaurantLocId = restaurantLocId;
        this.customerLocId = customerLocId;
        this.items = new ArrayList<>(items);
    }

    public String getOrderId() { return orderId; }
    public String getCustomerName() { return customerName; }
    public int getRestaurantLocId() { return restaurantLocId; }
    public int getCustomerLocId() { return customerLocId; }
    public ArrayList<OrderItem> getItems() { return items; }

    public double getTotalAmount() {
        double total = 0;
        for (OrderItem item : items) {
            total += item.getPrice();
        }
        return total;
    }
}