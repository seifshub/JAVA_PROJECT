//This class represents a review for a product by a customer
// it consists of the review and the rating given by the customer
public class Review {
    private Customer reviewer;
    private String review;
    private double rating;

    public Review(Customer reviewer, String review, double rating) {
        this.reviewer = reviewer;
        this.review = review;
        this.rating = rating;
    }


    public double getRating() {
        return rating;
    }

    @Override
    public String toString()
    {
        return "Reviewer: "+reviewer.getUsername()+" --  "+review+" -- Rating: "+rating;
    }
}
