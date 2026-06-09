import java.util.PriorityQueue;
import java.util.ArrayList;

public class RiderManager {
    private ArrayList<Rider> allRidersList;
    
    public RiderManager() {
        this.allRidersList = new ArrayList<>();
    }
    
    public void addRider(Rider rider) {
        allRidersList.add(rider);
    }
    
    public ArrayList<Rider> getAllRiders() {
        return this.allRidersList;
    }

    public void displayRiders(){
        if (allRidersList.isEmpty()){
            System.out.println("No riders registered");
            return;
        }

        System.out.println("\n----- Riders");
        for (Rider rider : allRidersList){
            String status = rider.isAvailable() ? "Available" : "On delivery" ;
            System.out.println("ID: " + rider.getId() + " | Name: " + rider.getName() + 
                               " | Location: " + DataManagementModule.city.getLocationName(rider.getCurrentLocId()) +
                               " [" + rider.getCurrentLocId() + "] | Status: " + status + " | Task: " +rider.getCurrentTask());

        }
    }

    public ArrayList<Rider> getBusyRiders(){
    ArrayList<Rider> busy = new ArrayList<>();
    for(Rider rider : allRidersList){
        if(!rider.isAvailable()){
            busy.add(rider);
        }
    }
    return busy;
    }

    public void showPriorityQueue(){
    PriorityQueue<Rider> pq = new PriorityQueue<>();
    for(Rider rider : allRidersList){
        if(rider.isAvailable()){
            pq.add(rider);
        }
    }
    int rank = 1;
    while(!pq.isEmpty()){
        Rider r = pq.poll();
        System.out.println(rank + ". " + r.getName() + " | Distance: "
                           + String.format("%.2f", r.getDistance())+ " km" );
        rank++;
    }
    }

    public boolean removeRider(String riderId) {
        for (int i = 0; i < allRidersList.size(); i++) {
            if (allRidersList.get(i).getId().equals(riderId)) {
                allRidersList.remove(i);
                return true;
            }
        }
        return false;
    }

    public Rider findRider(String riderID){
        for(Rider rider : allRidersList){
            if(rider.getId().equalsIgnoreCase(riderID)){
                return rider;
            }
        }
        return null;
    }

    public void displayBusyRiders() {
        boolean found = false;
        System.out.println("\n----- Busy Riders -----");
        for(Rider rider : allRidersList) {
            if(!rider.isAvailable()) {
                System.out.println("ID: " + rider.getId() + " | Name: " + rider.getName()
                                   + " | Task: " + rider.getCurrentTask());
                found = true;
            }
        }
        if(!found) {
            System.out.println("No riders currently busy.");
        }
    }

    public void refreshDistances(City city, int restaurantLocId) {
        for (Rider rider : allRidersList) {
            if (rider.isAvailable()) {
                int locId = rider.getCurrentLocId();
                rider.setDistance(city.getShortestDistance(locId, restaurantLocId));
            }
        }
    }
    
    public Rider assignBestRider(ArrayList<Rider> riders){
        PriorityQueue<Rider> assignmentQueue = new PriorityQueue<>();
        for (Rider rider : riders){
            if (rider.isAvailable()) {
                assignmentQueue.add(rider);
            }
        }
        
        if (assignmentQueue.isEmpty()){
            System.out.println("No riders available for delivery");
            return null;
        }
        Rider bestRider = assignmentQueue.poll();
        System.out.printf("---RIDER FOUND!---\nDispatching: %s (Distance to Restaurant: %.2f km)%n", 
                          bestRider.getName() ,bestRider.getDistance());
        return bestRider;
    }
}