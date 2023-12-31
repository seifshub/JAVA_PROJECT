//This class represents a credit card
public class CreditCardPayment implements PaymentStrategy{
    private String cardHolderName;
    private String cardNumber;
    private String expirationDate;
    private String cvv;
    private String country;

    public CreditCardPayment(String cardHolderName, String cardNumber, String expirationDate, String cvv, String country) {
        this.cardHolderName = cardHolderName;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
        this.country = country;
    }

    // this method processes the payment
    public void pay(double amount)
    {
        System.out.println("Your Credit Card payment of "+amount+"$ has been successfully processed.");
    }

}
