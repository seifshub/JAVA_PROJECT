import java.util.*;
//This class represents a product
public class Product {
    static long n=0;    //This variable is used to give each product a unique ID
    private long productId;
    private String name;
    private double price;
    private String description;
    private String manufacturer;    // or brand
    private String category;
    private List<Review> reviews;   // This list contains all the reviews for this product
    private double averageRating;   // This variable is used to store the average rating of this product
    public Product(String name, double price, String description, String manufacturer,String category) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.manufacturer = manufacturer;
        this.category = category;
        this.reviews = new ArrayList<>();
        n++;
        productId = n;
    }

    public long getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
    public String getDescription() {
        return description;
    }
    public String getManufacturer() {
        return manufacturer;
    }
    public String getCategory()
    {
        return category;
    }
    public double getAverageRating() {
        return averageRating;
    }
    public void setPrice(double price) {
        if(price>0)
            this.price = price;
        else{
            System.out.println("Invalid price");
        }
    }

    @Override
    public String toString() {
        return "ID: " + productId +
                "| " + name +" |"+
                " price: " + price +"$"+
                "\n---" + description + "---\n" +
                "brand: " + manufacturer +
                ", category: " + category +"\n";
    }

    // This method is used to calculate the average rating of this product(it returns the sum of all ratings)
    public double sumRatings()
    {
        double sum=0;
        for(Review i:reviews)
        {
            sum+=i.getRating();
        }
        return sum;
    }

    // This method is used to add a review to this product
    public void addReview(Review review) {
        reviews.add(review);    // Add the review to the list
        averageRating = sumRatings() / reviews.size();  //updates the average rating with the new rating added to the list
    }

    // Display all the reviews for this product
    public void printReviewsRating()
    {
        if(reviews.isEmpty())
        {
            System.out.println("No reviews for "+this.getName());
        }
        else {
            System.out.println("=============================================" + this.getName() + "=============================================");
            System.out.println("Average Rating: " + this.averageRating +" ||| "+ reviews.size()+" Reviews for " + this.getName() + ":");
            for (Review i : reviews) {
                System.out.println(i.toString());
                System.out.println("---------------------------------------------------------");
            }
        }
    }

}
