import java.util.HashMap;

public class DataRetrieval {

    // HashMap for user profiles - key: userId, value: User object
    private HashMap<String, User> userMap;

    // HashMap for order history - key: orderId, value: order summary string
    private HashMap<String, String> orderMap;

    public DataRetrieval() {
        this.userMap = new HashMap<>();
        this.orderMap = new HashMap<>();
    }

    // USER METHODS 

    public void registerUser(User user) {
        userMap.put(user.getUserId(), user);
    }

    public void removeUser(String userId) {
        if (userMap.containsKey(userId)) {
            userMap.remove(userId);
            System.out.println("\nUser " + userId + " removed from records.");
        } else {
            System.out.println("\nUser not found.");
        }
    }

    // O(1) lookup
    public User searchUserById(String userId) {
        return userMap.get(userId);
    }

    public void displayUserResult(String userId) {
        User u = searchUserById(userId);
        if (u != null) {
            System.out.println("\n  --- Customer Found ---");
            System.out.println("ID: " + u.getUserId() + " | Name: " +u.getName() + "| Location: " +
                               DataManagementModule.city.getLocationName(u.getLocation()) + " [" + u.getLocation() + "]");
        } else {
            System.out.println("No customer found with ID: " + userId);
        }
    }

    //  ORDER METHODS 

    // Saves a formatted order summary string keyed by orderId
    public void saveOrder(String orderId, String orderSummary) {
        orderMap.put(orderId, orderSummary);
    }

    public void removeOrder(String orderId) {
        if (orderMap.containsKey(orderId)) {
            orderMap.remove(orderId);
            System.out.println("\nOrder " + orderId + " removed.");
        } else {
            System.out.println("\nOrder not found.");
        }
    }

    // O(1) lookup
    public String searchOrderById(String orderId) {
        return orderMap.get(orderId);
    }

    public void displayOrderResult(String orderId) {
        String summary = searchOrderById(orderId);
        if (summary != null) {
            System.out.println("\n  --- Order Found ---");
            System.out.println(summary);
        } else {
            System.out.println("\nNo order found with ID: " + orderId);
        }
    }

    public HashMap<String, User> getUserMap() {
        return userMap;
    }

    public HashMap<String, String> getOrderMap() {
        return orderMap;
    }
}
