import java.util.*;

//This class represents a complaint by the customer
public class Complaint {
    private static long id=0;
    private long complaintID;
    private String complaint;   // The complaint itself
    private Customer customer;  // The customer who made the complaint
    private static List<Complaint> complaints = new ArrayList<>();  // This list contains all the complaints made by the customers
    public Complaint(String complaint, Customer customer) {
        id++;
        this.complaintID = id;
        this.complaint = complaint;
        this.customer = customer;
    }
    public static List<Complaint> getComplaints() {
        return complaints;
    }

    // returns the string representation of the complaint
    @Override
    public String toString()
    {
        return "Complaint ID: "+complaintID+" -> | "+complaint+" | -- Customer: "+customer.getUsername();
    }

}
