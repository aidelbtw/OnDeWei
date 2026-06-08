public class DataManagementModule {
    static City city = new City();
}

// 2. USER DATA BLUEPRINT
class User {
    private String userId;
    private String name;
    private int location;

    public User(String userId, String name, int location) {
        this.userId = userId;
        this.name = name;
        this.location = location;
    }

    public String getUserId() { return userId; }
    public String getName() { return name; }
    public int getLocation() { return location; }
}

// 3. RESTAURANT DATA BLUEPRINT
class Restaurant {
    private String restaurantId;
    private String name;
    private int location;
    private MenuBST menu;    

    public Restaurant(String restaurantId, String name, int location) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.location = location;
        this.menu = new MenuBST();
    }

    public String getRestaurantId() { return restaurantId; }
    public String getName() { return name; }
    public int getLocation() { return location; }
    public MenuBST getMenu() { return menu;}

    public void addFood(FoodItem food){
        menu.insert(food);
    }

    public boolean removeFood(String foodName){
    return menu.delete(foodName);   
}

    public void displayMenu(){
        menu.displayMenuInOrder();
    }

    public FoodItem searchFood(String name){
        return menu.search(name);
    }
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
        System.out.println("\nUser added successfully!");
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
        if (current == null){
            System.out.println("\nThere are no registered customers");
        }
        while (current != null) {
            System.out.println("ID: " + current.userData.getUserId() + " | Name: " + current.userData.getName() + 
                                " | Location: " + DataManagementModule.city.getLocationName(current.userData.getLocation()) + 
                                " [" + current.userData.getLocation() + "]");
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
        System.out.println("\nRestaurant added successfully!");
    }

    public boolean removeRestaurantById(String id) {
        if (head == null) return false;
        if (head.restaurantData.getRestaurantId().equals(id)) {
            head = head.next;
            return true;
        }
        RestaurantNode current = head;
        RestaurantNode previous = null;
        while (current != null && !current.restaurantData.getRestaurantId().equals(id)) {
            previous = current;
            current = current.next;
        }

        if (current == null) return false;
        previous.next = current.next;
        return true;
    }

    public Restaurant findRestaurant(String id){
        RestaurantNode current = head;
        
        while (current != null){
            if (current.restaurantData.getRestaurantId().equals(id)){
                return current.restaurantData;
            }
            current = current.next;
        }
        return null;
    }

    public void displayRestaurants() {
        RestaurantNode current = head;
        if (current == null){
            System.out.println("\nNo Restaurants Registered");
            return;
        }
        System.out.println();
        while (current != null) {
            System.out.println("ID: " + current.restaurantData.getRestaurantId() + " | Name: " + current.restaurantData.getName() +
                               " | Location: " + DataManagementModule.city.getLocationName(current.restaurantData.getLocation()) +
                               " [" + current.restaurantData.getLocation()+ "]");
            current = current.next;
        }
    }
}
