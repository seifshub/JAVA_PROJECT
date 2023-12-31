import java.util.*;

//This class represents a customer

public class Customer {
    private static long id=0;
    private long userID;
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private ShoppingCart cart;  // This is the shopping cart of the customer
    private List<Order> orders; // This list contains all the orders made by the customer
    private PaymentStrategy paymentMethod;  // This is the payment method of the customer
    private static Map<String,Customer> CustomerDataBase = new HashMap<>(); // This map contains all the customers the key is the username and the value is the customer

    public Customer(String username, String password, String email, String firstName, String lastName, String address, String phoneNumber) {
        id++;
        this.userID = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        cart= new ShoppingCart();
        orders=new ArrayList<>();
        CustomerDataBase.put(username,this);
    }
    public String getUsername() {return username;}
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}
    public String getAddress() {return address;}
    public void setAddress(String address) {this.address = address;}
    public String getPhoneNumber() {return phoneNumber;}
    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}
    public long getUserID() {return userID;}
    public ShoppingCart getCart() {return cart;}
    public List<Order> getOrders() {return orders;}
    public void setP(PaymentStrategy paymentMethod) {this.paymentMethod = paymentMethod;}
    public PaymentStrategy getP() {return paymentMethod;}
    public static Map<String, Customer> getUserDataBase() {
        return CustomerDataBase;
    }

    // This method checks if the username exists in the database
    public static boolean UsernameExists(String username)
    {
        return CustomerDataBase.containsKey(username);
    }

    // This method authenticate the user by checking if the username and password are correct and in that case it returns the customer instance otherwise it returns null
    public static Customer authenticateUser(String username, String password)
    {
        if(!CustomerDataBase.containsKey(username))
        {
            System.out.println("Username not found. Please try again");
            return null;
        }
        else if(!CustomerDataBase.get(username).getPassword().equals(password))
        {
            System.out.println("Incorrect password. Please try again");
            return null;
        }
        return CustomerDataBase.get(username);
    }
    @Override
    public String toString()
    {
        return "User ID: "+this.getUserID()+
                " | Username: "+this.getUsername()+
                " | Email: "+this.getEmail()+
                " | First Name: "+this.getFirstName()+
                " | Last Name: "+this.getLastName()+
                " | Address: "+this.getAddress()+
                " | Phone Number: "+this.getPhoneNumber();
    }

    // This method calculates the total of returning customers ie customers who made more than one order
    public static long totalReturningUsers()
    {
        long total=0;
        for(Customer i:CustomerDataBase.values())
        {
            if(i.getOrders().size()>1)
            {
                total++;
            }
        }
        return total;
    }
}
