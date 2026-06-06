import java.util.ArrayList;

public class OrderProcessor {
    private ArrayList<Order> pendingOrdersQueue;
    private ArrayList<OrderItem> cartDraftStack;
    private City city;

    public OrderProcessor(City city) {
        this.pendingOrdersQueue = new ArrayList<>();
        this.cartDraftStack = new ArrayList<>();
        this.city = city;
    }

    public void addItemToCart(String foodName, double price) {
        OrderItem item = new OrderItem(foodName, price);
        cartDraftStack.add(item);
        System.out.println("Added to cart: " + item);
    }

    public void undoLastItem() {
        if (!cartDraftStack.isEmpty()) {
            OrderItem removed = cartDraftStack.remove(cartDraftStack.size() - 1);
            System.out.println("Undone: Removed \"" + removed.getFoodName() + "\"");
        } else {
            System.out.println("Cart is empty!");
        }
    }

    public void viewCart() {
        if (cartDraftStack.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }
        System.out.println("--- Current Cart ---");
        for (int i = cartDraftStack.size() - 1; i >= 0; i--) {
            System.out.println("- " + cartDraftStack.get(i));
        }
    }

    public void confirmAndPlaceOrder(String customerName, int restaurantLocId, int customerLocId) {
        if (cartDraftStack.isEmpty()) {
            System.out.println("Cannot place an empty order!");
            return;
        }

        Order newOrder = new Order(customerName, restaurantLocId, customerLocId, cartDraftStack);
        pendingOrdersQueue.add(newOrder);
        cartDraftStack.clear();
        
        System.out.println("Order " + newOrder.getOrderId() + " placed successfully.");
    }

    public void processNextOrder() {
        if (pendingOrdersQueue.isEmpty()) {
            System.out.println("No pending orders inside the queue.");
            return;
        }

        Order currentOrder = pendingOrdersQueue.remove(0);

        System.out.println("\n=========================================");
        System.out.println("Processing Order ID: " + currentOrder.getOrderId());
        System.out.println("Customer: " + currentOrder.getCustomerName());
        System.out.printf("Total Amount: RM %.2f%n", currentOrder.getTotalAmount());
        System.out.println("-----------------------------------------");
        
        city.dijkstra(currentOrder.getRestaurantLocId(), currentOrder.getCustomerLocId());
        System.out.println("=========================================");
    }

    public void displayPendingOrders() {
        if (pendingOrdersQueue.isEmpty()) {
            System.out.println("No pending orders.");
            return;
        }
        System.out.println("\n--- Pending Orders Queue ---");
        for (Order order : pendingOrdersQueue) {
            System.out.println("[" + order.getOrderId() + "] " + order.getCustomerName() + " (" + order.getItems().size() + " items)");
        }
    }
}
