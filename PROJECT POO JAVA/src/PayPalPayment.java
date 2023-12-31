// this class represents the paypal account of the customer
public class PayPalPayment implements PaymentStrategy{
    private String email;
    private String password;

    public PayPalPayment(String email, String password) {
        this.email = email;
        this.password = password;
    }
    public void pay(double amount)
    {
        System.out.println("Your PayPal payment of "+amount+"$ has been successfully processed.");
    }
}
