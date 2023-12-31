import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.*;

//this class represents an order made by the customer
public class Order {
    private static long id=0;
    private long orderID;
    private Customer user;  // The customer who made the order
    private List<CartItem> items;   // The items in the order
    private double totalCost;   // The total cost of the order
    private LocalDateTime date; // The date of the order
    private static Map<Long,Order> orders = new HashMap<>();    // This map contains all the orders made by the customers the key is the orderID and the value is the order

    public Order(Customer user, ShoppingCart cart) {
        id++;
        this.orderID = id;
        this.user = user;
        this.items = cart.getItems();
        this.totalCost = cart.totalCost();
        this.date = LocalDateTime.now();
        orders.put(orderID,this);
    }
    public long getOrderID() {return orderID;}
    public double getTotalCost() {return totalCost;}
    public LocalDateTime getDate() {return date;}
    public static Map<Long, Order> getOrders() {return orders;}
    
    // This method returns the string representation of the order
    public String toString() {
        DateTimeFormatter dateformatter
                = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "Order ID: " + this.getOrderID() +
                " | User-> ID:" + user.getUserID()+" Name: "+user.getUsername() +
                " | Address: " + user.getAddress() +
                "\nTotal Cost: " + this.getTotalCost() +
                "\nDate of placement of the order: " + dateformatter.format(this.getDate());
    }

    // This method calculates the total sales of the store
    public static double totalSales()
    {
        double total=0;
        for(Order i:orders.values())
        {
            total+=i.getTotalCost();
        }
        return total;
    }

}
