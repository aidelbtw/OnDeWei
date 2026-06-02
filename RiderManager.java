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
        bestRider.onDelivery();
        System.out.println("---RIDER FOUND!---\n Dispatching: " + bestRider.getName() + " (Distance to Restaurant: " + bestRider.getDistance() + " km");
        return bestRider;
    }
}
