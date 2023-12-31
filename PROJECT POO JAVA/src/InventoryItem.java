// This class represents an item in the inventory
// It is composed of a product and a quantity
public class InventoryItem {
    Product product;
    int quantity;
    public InventoryItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
    public Product getProduct() {
        return product;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        if(quantity<=0)
            this.quantity=0;
        else
            this.quantity = quantity;
    }

    // This method displays the product information without showing the quantity
    public void displayForCustomers(int i){
        String s=quantity>0?"In Stock":"Out of Stock";
        System.out.println(i +
                "- " + this.product.getName()+" |"+
                " price: " + this.product.getPrice()+"$"+
                "| "+s+" |"+
                "\n---" + this.product.getDescription() + "---\n" +
                "brand: " + this.product.getManufacturer() +
                ", category: " + this.product.getCategory() +
                ", Average Rating: "+this.product.getAverageRating()
        );
    }

    // This method displays the product information
    public void displayForAdmin()
    {
        System.out.println("ID: " + this.product.getProductId() +
                " | " + this.product.getName()+" |"+
                " price: " + this.product.getPrice()+"$ "+
                "| Quantity in stock: "+this.quantity+" | "+
                "\n---" + this.product.getDescription() + "---\n" +
                "brand: " + this.product.getManufacturer() +
                ", category: " + this.product.getCategory() +
                ", Average Rating: "+this.product.getAverageRating()
                );
    }

}
