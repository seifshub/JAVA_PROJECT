import java.util.*;

//This class represents the shopping cart of the customer
public class ShoppingCart{
    private List<CartItem> items;   // all the items in the shopping cart
    Scanner sc=new Scanner(System.in);

    public ShoppingCart() {
        items=new ArrayList<>();
    }

    // This method checks if the shopping cart is empty
    public boolean isEmpty()
    {
        return items.isEmpty();
    }
    public List<CartItem> getItems() {
        return items;
    }

    // This method checks if an item exists in the shopping cart
    public boolean existInCart(InventoryItem item)
    {
        for(CartItem i:items)
        {
            if(i.getItem()==item)
            {
                return true;
            }
        }
        return false;
    }

    // This method adds an item to the shopping cart
    public void addProductToCart(InventoryItem item,int quantity)
    {
        if(!this.existInCart(item)) {
            items.add(new CartItem(item, quantity));
        }
        else{   // If the item already exists in the shopping cart, we update its quantity
            System.out.println("This product is already in your shopping cart. You can update its quantity if you wish to!");
        }
    }

    // This method displays the shopping cart
    public void viewCart()
    {
        System.out.println();
        for(int i =1;i<=items.size();i++)
        {
            System.out.println(i+"| Product Name: "+(items.get(i-1)).getItem().getProduct().getName()+" - Quantity: "+(items.get(i-1)).getQuantity()+" |");
            System.out.println("---------------------------------------------------------------------------------------------");
        }
        System.out.println();
    }

    // This method updates the quantity of an item in the shopping cart
    public void updateQuantityInCart(int newQuantity,int i)
    {
        if(newQuantity<1)
        {
            items.remove(i);
            return;
        }
        items.get(i).setQuantity(newQuantity);
    }


    // This method removes an item from the shopping cart
    public void removeItemFromCart()
    {
        this.viewCart();
        System.out.println("Please enter the Index of the product you wish to remove from the cart: ");
        System.out.println();
        int i,x;
        i=sc.nextInt();
        while(i<1 || i>items.size())
        {
            System.out.println("There's no Item corresponding to the index you entered, please try again: ");
            i=sc.nextInt();
        }
        System.out.println();
        System.out.println("Do you wish to proceed with removing the item "+items.get(i-1).getItem().getProduct().getName()+" from the cart: ");
        System.out.println("1.Yes\n2.No");
        System.out.println();
        x=sc.nextInt();
        if(x==1)
        {
            items.remove(i-1);
            System.out.println("Item removed successfully.");
        }
    }

    // This method clears the shopping cart
    public void clearCart()
    {
        items.clear();
    }

    // This method returns the total cost of the items in the shopping cart
    public double totalCost()
    {
        double total=0;
        for(CartItem i:items)
        {
            total+=i.getSubTotal();
        }
        return total;
    }

    // This method checks if the quantities of the items in the shopping cart are available, it is invoked just before the checkout
    public boolean checkQuantities()
    {
        for(CartItem i:items)
        {
            if(i.getItem().getQuantity()<1)
            {
                System.out.println("Sorry, "+i.getItem().getProduct().getName()+" is out of stock for now. Please try again later.");
                return false;
            }
            if(i.getQuantity()>i.getItem().getQuantity())
            {
                System.out.println("Sorry, the quantity of "+i.getItem().getProduct().getName()+" you requested is not available. Please try again.");
                return false;
            }
        }
        return true;
    }

    // This method simulate the checkout process ( it updates the quantities of the items in the inventory and clears the shopping cart)
    public void checkout(PaymentStrategy p,int x)
    {
        for(CartItem i:items)
        {
            i.getItem().setQuantity( (i.getItem().getQuantity()) - i.getQuantity());
        }
        p.pay(this.totalCost()+x);
        this.clearCart();
    }

}
