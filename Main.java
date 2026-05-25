import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int choice = -1;
        while (choice != 0){
            System.out.println("MAIN MENU");
            System.out.println("1. Customer Manager [Linked List]");
            System.out.println("2. Restaurant Manager [Linked List + BST]");
            System.out.println("3. Rider Manager [Priority Queue]");
            System.out.println("4. Place Order [Stack + Queue]");
            System.out.println("5. Process Next Order [Queue + Dijkstra]");
            System.out.println("6. Lookup Customer / Order [HashMap]");
            System.out.println("7. City Map & Route Finder [Graph + Dijkstra]");
            System.out.println("8. View Pending Order Queue");
            System.out.println("0. Exit");
            System.out.print("Choice: ");

            choice = readInt(input);
            switch (choice) {
                case 1: menuCustomer(); break;
                case 2: menuRestaurant(); break;
                case 3: menuRider(); break;
                case 4: break;
                case 5: break;
                case 6: break;
                case 7: break;
                case 8: break;
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

    static int readInt(Scanner input){
        try { return Integer.parseInt(input.nextLine().trim());
        } catch (NumberFormatException e) { return -1; }
    }
}