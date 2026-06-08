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
        System.out.println("---RIDER FOUND!---\n Dispatching: " + bestRider.getName() + " (Distance to Restaurant: " + bestRider.getDistance() + " km");
        return bestRider;
    }
}