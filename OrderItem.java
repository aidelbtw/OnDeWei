public class OrderItem {
    private String foodName;
    private double price;
    private int quantity;

    public OrderItem(String foodName, double price, int quantity) {
        this.foodName = foodName;
        this.price = price;
        this.quantity = quantity;
    }

    public String getFoodName() { return foodName; }
    public double getPrice() { return price; }
    public int getQuantity() {return quantity;}

    public double getSubtotal(){
        return price * quantity;
    }

    @Override
    public String toString() {
        return String.format("%s x%d (RM %.2f)", foodName, quantity, getSubtotal());
    }
}
