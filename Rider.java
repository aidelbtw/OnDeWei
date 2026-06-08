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

         String start = city.getLocationName(currentLocId);
         String destination = city.getLocationName(restaurantLocId);
         String customer = city.getLocationName(customerLocId);

         this.currentTask = "Picking up : " + start + " -> " + destination + " | Delivering to: " + customer;  
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
