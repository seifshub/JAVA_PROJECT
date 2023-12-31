//This class represents an item in the shopping cart of a customer
// it is composed of an Inventory Item and a quantity
public class CartItem {
    private InventoryItem item;
    private int quantity;

    public CartItem(InventoryItem item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }
    public InventoryItem getItem() {return item;}
    public int getQuantity() {return quantity;}
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // This method returns the cost of this item ( price of the product multiplied by the quantity desired)
    public double getSubTotal()
    {
        return quantity*item.getProduct().getPrice();
    }

    @Override
    public String toString()
    {
        return item.getProduct().getName()+" - "+quantity;
    }


}
