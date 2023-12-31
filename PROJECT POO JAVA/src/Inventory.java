import java.util.*;

//This class represents the inventory of the store
public class Inventory {
    List <InventoryItem>items;
    public Inventory() {
        this.items = new ArrayList<>();
    }
    public List<InventoryItem> getInventory() {
        return items;
    }

    // This method checks if a product exists in the inventory by its ID
    public boolean exists(long productId){
        for (InventoryItem item : items) {
            if (item.getProduct().getProductId() == (productId)) {
                return true;
            }
        }
        return false;
    }

    //This method returns the InventoryItem object of a product by its ID
    public InventoryItem getItem(int productId){
        for (InventoryItem item : items) {
            if (item.getProduct().getProductId() == (productId)) {
                return item;
            }
        }
        return null;    // If the product is not found, return null
    }

    //This method returns the InventoryItem object of a product by its name
    public InventoryItem getItem(String name){
        for (InventoryItem item : items) {
            if (item.getProduct().getName().equals(name)) {
                return item;
            }
        }
        return null;
    }

    // This method adds an Item(Product+Quantity) to the inventory
    public void addItem(InventoryItem item){
        if(!exists(item.getProduct().getProductId())){
            items.add(item);
        }
        else{
            System.out.println("Item already exists");
        }
    }

    // This method updates the qunatity if an item in the inventory
    public void updateItem(int productId, int quantity){
        if(exists(productId)){
            getItem(productId).setQuantity(quantity);
            System.out.println("Item updated successfully");
        }
        else{
            System.out.println("Item not found");
        }
    }

    // Display the inventory with masking the quantity (Customers don't need to see the exact quantity of each product
    public void viewInventoryCustomer(){
        if(items.isEmpty()){
            System.out.println("Inventory is empty");
            return;
        }
        for (int i = 0; i < items.size(); i++) {
            (items.get(i)).displayForCustomers(i+1);
            System.out.println("-----------------------------------------------------------------------------------------------");
        }
    }

    // Display the inventory with the quantity shown (Admins need to see the exact quantity of each product)
    public void viewInventoryAdmin(){
        if(items.isEmpty()){
            System.out.println("Inventory is empty");
            return;
        }
        for (InventoryItem item : items) {
            item.displayForAdmin();
            System.out.println("-----------------------------------------------------------------------------------------------");
        }

    }

    // This method is used to search for a product by its name
    public void searchItemCustomer(String name){
        InventoryItem item=getItem(name);
        if (item!=null) {
            item.displayForCustomers(1);
        }
        else{
            System.out.println("Item not found");
        }
    }

    public void searchItemAdmin(String name){
        InventoryItem item=getItem(name);
        if (item!=null) {
            item.displayForAdmin();
        }
        else{
            System.out.println("Item not found");
        }
    }

    //Filter the inventory by category
    public void filterByCategory(String category){
        if(items.isEmpty()){
            System.out.println("No products of this category for now");
            return;
        }
        System.out.println("============================================="+category+"=============================================");
        for(int i=0;i<items.size();i++){
            if(((items.get(i)).getProduct()).getCategory().equals(category)){
                (items.get(i)).displayForCustomers(i+1);
                System.out.println("-----------------------------------------------------------------------------------------------");

            }
        }
    }

    // Display the available products(products with quantity > 0)
    public void viewAvailableProducts() {
        if(items.isEmpty()){
            System.out.println("No available products for now");
            return;
        }
        for (int i = 0; i < items.size(); i++) {
            if ((items.get(i)).getQuantity() > 0) {
                (items.get(i)).displayForCustomers(i+1);
                System.out.println("-----------------------------------------------------------------------------------------------");
            }
        }

    }
}
