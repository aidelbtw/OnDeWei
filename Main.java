import java.util.Scanner;
public class Main {
    static City city = new City();
    static RiderManager riderManager = new RiderManager();
    static DataRetrieval dataRetrieval = new DataRetrieval();
    static DataManagementModule dm = new DataManagementModule();
    static UserLinkedList users = new UserLinkedList();
    static RestaurantLinkedList restaurants = new RestaurantLinkedList();
    static OrderProcessor orderProcessor = new OrderProcessor(city, dataRetrieval, riderManager);

    public static void loadSampleData() {

    // Users
    User u1 = new User("U001", "Ali", 0);
    User u2 = new User("U002", "Siti", 3);

    users.addUser(u1);
    dataRetrieval.registerUser(u1);
    users.addUser(u2);
    dataRetrieval.registerUser(u2);

    // Restaurants
    Restaurant r1 = new Restaurant("R001", "Mamak Corner", 5);
    r1.addFood(new FoodItem("Nasi Goreng", 8.50, "Main"));
    r1.addFood(new FoodItem("Teh Tarik", 2.50, "Drink"));

    Restaurant r2 = new Restaurant("R002", "Burger House", 4);
    r2.addFood(new FoodItem("Cheeseburger", 12.00, "Main"));
    r2.addFood(new FoodItem("Fries", 5.00, "Side"));

    restaurants.addRestaurant(r1);
    restaurants.addRestaurant(r2);

    // Riders
    riderManager.addRider(new Rider("RD001", "Ahmad", 1));
    riderManager.addRider(new Rider("RD002", "Jason", 6));
}

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        loadSampleData();

        int choice = -1;
        while (choice != 0){
            System.out.println("\n------- MAIN MENU -------");
            System.out.println("1. Customer Manager [Linked List]");
            System.out.println("2. Restaurant Manager [Linked List + BST]");
            System.out.println("3. Rider Manager [Priority Queue]");
            System.out.println("4. Place Order [Stack + Queue]");
            System.out.println("5. Process Next Order [Queue + Dijkstra]");
            System.out.println("6. Search Customer / Order [HashMap]");
            System.out.println("7. City Map & Route Finder [Graph + Dijkstra]");
            System.out.println("8. View Pending Order Queue");
            System.out.println("0. Exit");
            System.out.print("Choice: ");

            choice = readInt(input);
            switch (choice) {
                case 1: menuCustomer(); break;
                case 2: menuRestaurant(); break;
                case 3: menuRider(); break;
                case 4: menuPlaceOrder(input); break;
                case 5: orderProcessor.processNextOrder(); break;
                case 6: menuSearch(); break;
                case 7: menuMap(); break;
                case 8: orderProcessor.displayPendingOrders(); break;
                case 0: System.out.println("System Shutdown, Byeeeee! <3");break;      
                default: System.out.println("Invalid Choice, Please try again");
            }
        }
        input.close();
    }

    public static void menuCustomer(){
        Scanner input = new Scanner(System.in);
        System.out.println("\n------- Customer Management -------");
        System.out.println("1. View all customers");
        System.out.println("2. Add new customer");
        System.out.println("3. Remove customer");
        System.out.println("4. Back");
        System.out.print("Choice: ");

        int choice = readInt(input);
        switch (choice){
            case 1: users.displayUsers(); 
                break;

            case 2: 
                System.out.print("Enter Customer ID: ");
                String id = input.nextLine();
                if(dataRetrieval.searchUserById(id) != null){
                    System.out.println("Customer ID already exists");
                    break;
                }
                System.out.print("Enter Customer Name: ");
                String name = input.nextLine();
                city.displayMap();
                System.out.print("Enter Customer Location: ");
                int location = readInt(input);
                if (location < 0 || location >= city.N){
                    System.out.println("Invalid location");
                    return;
                }
                User user = new User(id, name, location);
                users.addUser(user);
                dataRetrieval.registerUser(user);
                break;

            case 3: 
                System.out.print("Customer ID to remove: ");
                String remove = input.nextLine();
                
                users.removeUserById(remove);
                dataRetrieval.removeUser(remove);
                break;

            case 4: break;
            default: System.out.println("Invalid Option");
        }
    }

    public static void menuRestaurant(){
        Scanner input = new Scanner(System.in);
        System.out.println("\n------- Restaurant Management -------");
        System.out.println("1. View all restaurants");
        System.out.println("2. View a restaurant's menu");
        System.out.println("3. Search food in a restaurant");
        System.out.println("4. Add new restaurant");
        System.out.println("5. Add food item to restaurant");
        System.out.println("6. Remove a restaurant");
        System.out.println("7. Remove food item");
        System.out.println("8. Back");
        System.out.print("Choice: ");

        int choice = readInt(input);
        switch (choice){
            case 1: restaurants.displayRestaurants();
                break;

            case 2: 
                System.out.print("Enter Restaurant ID: ");
                String rid = input.nextLine();
                Restaurant r = restaurants.findRestaurant(rid);
                if (r == null){
                    System.out.println("Restaurant not found");
                    break;}
                System.out.println(" ----- " + r.getName() + " -----");
                r.displayMenu();
                break;

            case 3: 
                System.out.print("Enter Restaurant ID: ");
                String resID = input.nextLine();
                Restaurant rest = restaurants.findRestaurant(resID);

                if(rest == null){
                    System.out.println("Restaurant not found");
                    break;
                }
                System.out.println("Searching in " + rest.getName());
                System.out.print("Enter Food Name: ");
                String food = input.nextLine();

                FoodItem item = rest.searchFood(food);  
                if(item != null){
                    System.out.println(item);
                } else
                    System.out.println("Food not found");
                break;

            case 4: 
                System.out.print("Enter Restaurant ID: ");
                String id = input.nextLine();
                if(restaurants.findRestaurant(id) != null){
                    System.out.println("Restaurant ID already exists");
                    break;
                }
                System.out.print("Enter Restaurant Name: ");
                String name = input.nextLine();
                city.displayMap();
                System.out.print("Enter Location ID: ");
                int loc = readInt(input);
                if (loc < 0 || loc >= city.N){
                    System.out.println("Invalid Location");
                    break;
                }

                Restaurant restaurant = new Restaurant(id, name, loc);
                restaurants.addRestaurant(restaurant);
                break;

            case 5: 
                System.out.print("Enter Restaurant ID: ");
                String idToAdd = input.nextLine(); 
                
                Restaurant restoran = restaurants.findRestaurant(idToAdd);
                if (restoran == null){
                    System.out.println("\nRestaurant not Found");
                    break;
                }
                System.out.println("\n----- Adding item to " + restoran.getName() + " ------");
                System.out.print("Food Name: ");
                String foodName = input.nextLine();

                System.out.print("Price: ");
                double price = readDouble(input);

                System.out.print("Category: ");
                String category = input.nextLine();

                restoran.addFood(new FoodItem(foodName, price, category));
                System.out.println("Food added successfully");
                break;

            case 6: 
                System.out.print("Enter Restaurant ID to remove: ");
                String remove = input.nextLine();
                if(restaurants.removeRestaurantById(remove)){
                    System.out.println("Restaurant removed");
                } else {
                    System.out.println("Restaurant was not found");
                }
                System.out.println("");break;

            case 7: 
                System.out.print("Enter Restaurant ID to remove item from: "); 
                String removeFrom = input.nextLine();

                Restaurant resta = restaurants.findRestaurant(removeFrom);

                if(resta == null){
                    System.out.println("Restaurant was not found");
                    break;
                }

                System.out.print("Item Name to Remove: ");
                String removeItem = input.nextLine();

                if(resta.removeFood(removeItem)){
                    System.out.println("\nItem removed");
                }
                else{
                    System.out.println("\nError: Item was not found");
                }break;
            case 8: break;
            default: System.out.println("Invalid Option");
        }
    }

    public static void menuRider(){
        Scanner input = new Scanner(System.in);
        System.out.println("\n------- Rider Management -------");
        System.out.println("1. View all riders");
        System.out.println("2. Add new rider");
        System.out.println("3. Simulate: show priority order to a restaurant");
        System.out.println("4. Remove a rider");
        System.out.print("Choice: ");

        int choice = readInt(input);
        switch (choice){
            case 1: 
                riderManager.displayRiders(); 
                break;
                
            case 2: 
                System.out.print("Enter Rider ID: ");
                String riderID = input.nextLine();
                if(riderManager.findRider(riderID) != null){
                    System.out.println("Rider ID already exists");
                    break;
                }
                System.out.print("Enter Rider Name: ");
                String riderName = input.nextLine();
                city.displayMap();
                System.out.println("Current Location ID: ");
                int location = readInt(input);
                if (location < 0 || location >= city.N){
                    System.out.println("Rider Location Invalid");
                    break;
                }
                System.out.println("Rider Succesfully registered!");
                
                riderManager.addRider(new Rider(riderID, riderName, location));
                break;

            case 3: break;
            case 4: 
                System.out.print("Enter Rider ID to be removed: ");
                String id = input.nextLine();
                if(riderManager.removeRider(id))
                    System.out.println("Rider removed");
                else
                    System.out.println("Rider not found");
                break;
            default: System.out.println("Invalid Option");
        }
    }
    
    public static void menuPlaceOrder(Scanner input) {
            System.out.println("\n------- PLACE AN ORDER -------");
            System.out.print("Enter Customer ID: ");
            String customerId = input.nextLine();

            User customer = dataRetrieval.searchUserById(customerId);

            if (customer == null){
                System.out.println("Customer not found");
                System.out.println("1. Register New Customer");
                System.out.println("2. Cancel");
                int option = readInt(input);
                if(option == 1){
                    System.out.print("Enter Customer Name: ");
                    String name = input.nextLine();

                    city.displayMap();
                    System.out.print("Enter Location ID: ");
                    int location = readInt(input);
                    if (location < 0 || location >= city.N){
                        System.out.println("Invalid location");
                        return;
                    }

                    customer = new User(customerId, name, location);
                    users.addUser(customer);
                    dataRetrieval.registerUser(customer);
                    System.out.println("Customer registered");
                    
                } else {
                    return;
                }
            }

            System.out.println("\nAvailable Restaurants: ");
            restaurants.displayRestaurants();

            System.out.print("Enter Restaurant ID: ");
            String restID = input.nextLine().trim();

            Restaurant restaurant = restaurants.findRestaurant(restID);
            if (restaurant == null){
                System.out.println("Restaurant ID was not found");
                return;
            }
    
            boolean shopping = true;
            while (shopping) {
                System.out.println("\n--- Shopping Cart Workspace (LIFO) ---");
                System.out.println("1. Add Item to Cart");
                System.out.println("2. Undo Last Item (Stack Pop)");
                System.out.println("3. View Current Cart");
                System.out.println("4. Confirm & Checkout (Push to Queue)");
                System.out.println("5. Remove Item");
                System.out.println("6. Cancel Order");
                System.out.print("Action: ");
                
                int cartChoice = readInt(input);
                switch (cartChoice) {
                    case 1:
                        System.out.println(" ----- " + restaurant.getName() + " Menu -----" );
                        restaurant.displayMenu();
                        System.out.print("Enter item Name: ");
                        String foodName = input.nextLine();
                        FoodItem food = restaurant.searchFood(foodName);
                        if(food == null){
                            System.out.println("Item not found");
                            break;
                        }
                        orderProcessor.addItemToCart(food.getName(), food.getPrice());
                        break;
                        
                    case 2:
                        orderProcessor.undoLastItem();
                        break;
                        
                    case 3:
                        orderProcessor.viewCart();
                        break;
                        
                    case 4:
                        orderProcessor.confirmAndPlaceOrder(customer.getName(), restaurant.getLocation(), customer.getLocation());
                        shopping = false;
                        break;

                    case 5:
                        System.out.println("Food name to remove: ");
                        String removedFood = input.nextLine();
                        
                        orderProcessor.removeItem(removedFood);
                        break;


                    case 6:
                        System.out.println("Shopping cart abandoned.");
                        shopping = false;
                        break;
                        
                    default:
                        System.out.println("Invalid workspace option.");
                        break;
                }
            }
        }

// ---- SEARCH MENU (HashMap) ----
    public static void menuSearch() {
        Scanner input = new Scanner(System.in);
        System.out.println("\n ------- Search Menu -------");
        System.out.println("1. Search customer by ID");
        System.out.println("2. Search order by ID");
        System.out.println("3. Back");
        System.out.print("Choice: ");

        int choice = readInt(input);
        switch (choice) {
            case 1:
                System.out.print("Enter Customer ID: ");
                String cid = input.nextLine().trim();
                dataRetrieval.displayUserResult(cid);
                break;
            case 2:
                System.out.print("Enter Order ID: ");
                String oid = input.nextLine().trim();
                dataRetrieval.displayOrderResult(oid);
                break;
            case 3:
                break;
            default:
                System.out.println("Invalid Option");
        }
    }

    public static void menuMap(){
        Scanner input = new Scanner(System.in);
        System.out.println("\n ------- Map Menu -------");
        System.out.println("1. Display Map");
        System.out.println("2. Calculate Shortest Distance");
        System.out.println("3. Back");
        System.out.print("Choice: ");
        
        int choice = readInt(input);
        switch (choice){
            case 1:
                city.displayMap();
                break;
            case 2:
                city.displayMap();
                System.out.println("\nAvailable Roads:");
                for (int i = 0; i < city.N ; i++){
                    for (int j = i + 1; j < city.N ; j++){
                        if (city.roads[i][j] > 0){
                            System.out.print("[" + i + "] " + city.locs[i].name + " -> [" + j +"] " + city.locs[j].name +" ");
                            System.out.printf("%.1fKM %n", city.roads[i][j]);
                        }
                    }
                }

                System.out.println("\nFind the shortest path between 2 locations:");
                System.out.print("Start [0-" + (city.N-1) +"]: ");
                int start = readInt(input);
                System.out.print("End [0-" + (city.N-1) + "]: ");
                int end = readInt(input);

                if (start < 0 || start >= city.N || end < 0 || end >= city.N){
                    System.out.println("Invalid location");
                }
                city.dijkstra(start, end);
                break;
            case 3:
                break;
            default:
                System.out.println("Invalid Option");
                break;
        }
    }

    static int readInt(Scanner input){
        try { return Integer.parseInt(input.nextLine().trim());
        } catch (NumberFormatException e) { return -1; }
    }

    static double readDouble(Scanner input){
        try { return Double.parseDouble(input.nextLine().trim());
        } catch (NumberFormatException e) { return -1;}
    }
}
