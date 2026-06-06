public class Rider implements Comparable<Rider>{
    private String id;
    private String name;
    private int currentLocId;
    private double distanceToRestaurant;
    private boolean isAvailable;
    
    public Rider(String id, String name, int currentLocation) {
        this.id= id;
        this.name = name;
        this.currentLocId = currentLocation;
        this.distanceToRestaurant = Double.MAX_VALUE;
        this.isAvailable = true;
    }
    
    public String getName() { return this.name; }
    public double getDistance() {return this.distanceToRestaurant;}
    public boolean isAvailable() {return this.isAvailable; }

    public int getCurrentLocId() { 
        return this.currentLocId; 
    }
    
    public void setDistance(double calculatedDistance) {
        this.distanceToRestaurant = calculatedDistance;
    }
    
    public void onDelivery() {
        this.isAvailable = false;
    }
    
    public void completeDelivery(int custLocId) {
        this.currentLocId = custLocId;
        this.distanceToRestaurant = Double.MAX_VALUE;
        this.isAvailable = true;
        
        System.out.println(this.name + " completed delivery and is now at location: " + this.currentLocId);
    }
    
    @Override
    public int compareTo(Rider o) {
        return Double.compare(this.distanceToRestaurant, o.distanceToRestaurant);
    }
}
