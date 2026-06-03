import java.util.ArrayList;
public class DataManagementModule {
    
}

// 2. USER DATA BLUEPRINT
class User {
    private String userId;
    private String name;
    private String location;

    public User(String userId, String name, String location) {
        this.userId = userId;
        this.name = name;
        this.location = location;
    }

    public String getUserId() { return userId; }
    public String getName() { return name; }
    public String getLocation() { return location; }
}

// 3. RESTAURANT DATA BLUEPRINT
class Restaurant {
    private String restaurantId;
    private String name;
    private String location;

    public Restaurant(String restaurantId, String name, String location) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.location = location;
    }

    public String getRestaurantId() { return restaurantId; }
    public String getName() { return name; }
    public String getLocation() { return location; }
}

// 4. USER NODE
class UserNode {
    public User userData;
    public UserNode next;

    public UserNode(User user) {
        this.userData = user;
        this.next = null;
    }
}

// 5. RESTAURANT NODE
class RestaurantNode {
    public Restaurant restaurantData;
    public RestaurantNode next;

    public RestaurantNode(Restaurant restaurant) {
        this.restaurantData = restaurant;
        this.next = null;
    }
}

// 6. USER LINKED LIST
class UserLinkedList {
    private UserNode head;

    public void addUser(User user) {
        UserNode newNode = new UserNode(user);
        if (head == null) {
            head = newNode;
        } else {
            UserNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        System.out.println("User added successfully!");
    }

    public void removeUserById(String id) {
        if (head == null) return;
        if (head.userData.getUserId().equals(id)) {
            head = head.next;
            return;
        }
        UserNode current = head;
        UserNode previous = null;
        while (current != null && !current.userData.getUserId().equals(id)) {
            previous = current;
            current = current.next;
        }
        if (current != null) {
            previous.next = current.next;
        }
    }

    public void displayUsers() {
        UserNode current = head;
        while (current != null) {
            System.out.println("ID: " + current.userData.getUserId() + " | Name: " + current.userData.getName());
            current = current.next;
        }
    }
}

// 7. RESTAURANT LINKED LIST
class RestaurantLinkedList {
    private RestaurantNode head;

    public void addRestaurant(Restaurant restaurant) {
        RestaurantNode newNode = new RestaurantNode(restaurant);
        if (head == null) {
            head = newNode;
        } else {
            RestaurantNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        System.out.println("Restaurant added successfully!");
    }

    public void removeRestaurantById(String id) {
        if (head == null) return;
        if (head.restaurantData.getRestaurantId().equals(id)) {
            head = head.next;
            return;
        }
        RestaurantNode current = head;
        RestaurantNode previous = null;
        while (current != null && !current.restaurantData.getRestaurantId().equals(id)) {
            previous = current;
            current = current.next;
        }
        if (current != null) {
            previous.next = current.next;
        }
    }

    public void displayRestaurants() {
        RestaurantNode current = head;
        while (current != null) {
            System.out.println("ID: " + current.restaurantData.getRestaurantId() + " | Name: " + current.restaurantData.getName());
            current = current.next;
        }
    }
}
