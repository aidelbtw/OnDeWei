import java.util.ArrayList;

public class Rider implements Comparable<Rider>{
    private String id;
    private String name;
    private int currentLocId;
    private double distanceToRestaurant;
    private boolean isAvailable;
    private String currentTask;
    private int destinationLocId = -1;
    
    public Rider(String id, String name, int currentLocation) {
        this.id = id;
        this.name = name;
        this.currentLocId = currentLocation;
        this.distanceToRestaurant = Double.MAX_VALUE;
        this.isAvailable = true;
        this.currentTask = "None";
    }
    
    public String getName() { return this.name; }
    public double getDistance() { return this.distanceToRestaurant; }
    public boolean isAvailable() { return this.isAvailable; }
    public String getId() { return this.id; }
    public int getDestinationLocId() { return this.destinationLocId;}

    public int getCurrentLocId() { 
        return currentLocId; 
    }

    public String getCurrentTask(){
        return currentTask;
    }
    
    public void setDistance(double calculatedDistance) {
        distanceToRestaurant = calculatedDistance;
    }
    
    public void onDelivery(int restaurantLocId, int customerLocId, City city) {
        isAvailable = false;
        this.destinationLocId = customerLocId;
         
        ArrayList<Integer> pathToRest = city.getShortestPath(currentLocId, restaurantLocId);
        ArrayList<Integer> pathToCust = city.getShortestPath(restaurantLocId, customerLocId);
        
        StringBuilder task = new StringBuilder();
        for (int i = 0; i < pathToRest.size(); i++) {
            if (i > 0) task.append(" -> ");
            task.append(city.getLocationName(pathToRest.get(i)));
        }
        for (int i = 1; i < pathToCust.size(); i++) {
            task.append(" -> ").append(city.getLocationName(pathToCust.get(i)));
        }
        this.currentTask = task.toString();

        double riderToRestaurant = 0;
        for (int i = 0; i < pathToRest.size() - 1; i++) {
            riderToRestaurant += city.roads[pathToRest.get(i)][pathToRest.get(i + 1)];
        }

        double restaurantToCustomer = 0;
        for (int i = 0; i < pathToCust.size() - 1; i++) {
            restaurantToCustomer += city.roads[pathToCust.get(i)][pathToCust.get(i + 1)];
        }

        double totalDistance = riderToRestaurant + restaurantToCustomer;
        System.out.printf("Total delivery distance: %.2f km%n", totalDistance);
    }
    
    public void completeDelivery() {
        currentLocId = destinationLocId;
        destinationLocId = -1;
        distanceToRestaurant = Double.MAX_VALUE;
        isAvailable = true;
        currentTask = "None";
        System.out.println(name + " completed delivery and is now at location: " + 
                           DataManagementModule.city.getLocationName(currentLocId) + " [" + currentLocId + "]");
    }
    
    @Override
    public int compareTo(Rider o) {
        return Double.compare(distanceToRestaurant, o.distanceToRestaurant);
    }
}
