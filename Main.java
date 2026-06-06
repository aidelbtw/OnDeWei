import java.util.Scanner;
public class Main {
    static City city = new City();
    static RiderManager riderManager = new RiderManager();
    static DataRetrieval dataRetrieval = new DataRetrieval();
    static OrderProcessor orderProcessor = new OrderProcessor(city, dataRetrieval, riderManager);

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

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
            case 1: break;
            case 2: break;
            case 3: break;
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
        System.out.println("7. Back");
        System.out.print("Choice: ");

        int choice = readInt(input);
        switch (choice){
            case 1: break;
            case 2: break;
            case 3: break;
            case 4: break;
            case 5: break;
            case 6: break;
            case 7: break;
            default: System.out.println("Invalid Option");
        }
    }

    public static void menuRider(){
        Scanner input = new Scanner(System.in);
        System.out.println("\n------- Rider Management -------");
        System.out.println("1. View all riders");
        System.out.println("2. Add new rider");
        System.out.println("3. Simulate: show priority order to a restaurant");
        System.out.print("Choice: ");

        int choice = readInt(input);
        switch (choice){
            case 1: break;
            case 2: break;
            case 3: break;
            case 4: break;
            default: System.out.println("Invalid Option");
        }
    }
    
static void menuPlaceOrder(Scanner input) {
        System.out.println("\n------- PLACE AN ORDER -------");
        System.out.print("Enter Customer Name: ");
        String customerName = input.nextLine().trim();

        System.out.print("Enter Customer Location Node ID (0-" + (city.N - 1) + "): ");
        int custLoc = readInt(input);
        System.out.print("Enter Restaurant Location Node ID (0-" + (city.N - 1) + "): ");
        int restLoc = readInt(input);

        if (custLoc < 0 || custLoc >= city.N || restLoc < 0 || restLoc >= city.N) {
            System.out.println("Invalid location boundaries. Session canceled.");
            return;
        }

        boolean shopping = true;
        while (shopping) {
            System.out.println("\n--- Shopping Cart Workspace (LIFO) ---");
            System.out.println("1. Add Item to Cart");
            System.out.println("2. Undo Last Item (Stack Pop)");
            System.out.println("3. View Current Cart");
            System.out.println("4. Confirm & Checkout (Push to Queue)");
            System.out.println("5. Cancel Order");
            System.out.print("Action: ");
            
            int cartChoice = readInt(input);
            switch (cartChoice) {
                case 1:
                    System.out.print("Enter Food Name: ");
                    String foodName = input.nextLine().trim();
                    System.out.print("Enter Price (RM): ");
                    double price = readDouble(input);
                    if (price > 0) {
                        orderProcessor.addItemToCart(foodName, price);
                    } else {
                        System.out.println("Invalid price entry.");
                    }
                    break;
                    
                case 2:
                    orderProcessor.undoLastItem();
                    break;
                    
                case 3:
                    orderProcessor.viewCart();
                    break;
                    
                case 4:
                    orderProcessor.confirmAndPlaceOrder(customerName, restLoc, custLoc);
                    shopping = false;
                    break;
                    
                case 5:
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
