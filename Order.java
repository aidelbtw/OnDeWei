import java.util.ArrayList;

public class Order {
    private static int idCounter = 1000;
    private String orderId;
    private String customerName;
    private int restaurantLocId;
    private int customerLocId;
    private ArrayList<OrderItem> items;

    private Rider assignedRider;
    private String status;

    public Order(String customerName, int restaurantLocId, int customerLocId, ArrayList<OrderItem> items) {
        this.orderId = "ORD" + (++idCounter);
        this.customerName = customerName;
        this.restaurantLocId = restaurantLocId;
        this.customerLocId = customerLocId;
        this.items = new ArrayList<>(items);
        this.status = "Pending"; 
        this.assignedRider = null;
    }

    public String getOrderId() { return orderId; }
    public String getCustomerName() { return customerName; }
    public int getRestaurantLocId() { return restaurantLocId; }
    public int getCustomerLocId() { return customerLocId; }
    public ArrayList<OrderItem> getItems() { return items; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Rider getAssignedRider() { return assignedRider; }
    public void setAssignedRider(Rider rider) { this.assignedRider = rider; }

    public double getTotalAmount() {
        double total = 0;
        for (OrderItem item : items) {
            total += item.getSubtotal();
        }
        return total;
    }

    public String generateSummary() {
        City city = new City();
        String summary = "Order ID: " + orderId + "\n";
        
        summary += "Customer: " + customerName + " (" + city.getLocationName(customerLocId) + ")\n";
        summary += "Restaurant: " + city.getLocationName(restaurantLocId) + "\n";
        
        String riderName = (assignedRider != null) ? assignedRider.getName() : "None";
        summary += "Rider Assigned: " + riderName + "\n";
        
        summary += "Items Ordered:\n";
        
        for (OrderItem item : items) {
            summary += "  - " + item.toString() + "\n";
        }
        
        summary += "Total Amount: RM " + String.format("%.2f", getTotalAmount());
        
        return summary;
    }
}
