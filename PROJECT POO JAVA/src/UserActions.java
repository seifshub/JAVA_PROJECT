import java.util.*;

public class UserActions {
    Scanner sc=new Scanner(System.in);
    final List<String> categories=new ArrayList<>(
            Arrays.asList("Electronics",
                    "Clothing",
                    "Home",
                    "Sports",
                    "Toys",
                    "Beauty")
    );
    Inventory inventory=new Inventory();    // This is the inventory of the store
    boolean userLoggedIn=false; //checks if there's a user logged in or not
    Customer currentUser=null;  // This is the current user logged in

    public void mainMenu()
    {
        int choice;
        System.out.println("=============================================Welcome!=============================================");
        System.out.println("1-Sign up\n2-Login\n0-Exit");
        choice=sc.nextInt();
        while(choice<0 || choice>2)
        {
            System.out.println("Invalid option, please retry");
            choice=sc.nextInt();
        }
        if(choice==1)
        {
            signUp();
        }
        else if(choice==2)
        {
            logIn();
        }
        else{
            System.exit(0);
        }
    }
    public void signUp()
    {
        String username;
        String password;
        String email;
        String firstName;
        String lastName;
        String address;
        String phoneNumber;
        System.out.println("------Please enter the following information------");
        System.out.print("First Name:\t");
        firstName=sc.nextLine();
        firstName=sc.nextLine();
        System.out.print("Last Name:\t");
        lastName= sc.nextLine();
        System.out.print("Actual address:\t");
        address=sc.nextLine();
        System.out.print("Phone Number:\t");
        phoneNumber=sc.nextLine();
        System.out.print("Email address:\t");
        email=sc.nextLine();
        System.out.print("Username:\t");
        username=sc.nextLine();
        //insures the uniqueness of the usernames
        while(Customer.UsernameExists(username))
        {
            System.out.print("Username already taken. Please enter a new one:");
            username=sc.nextLine();
        }
        System.out.print("Password\t");
        password=sc.nextLine();
        //insures a strong password
        while(password.length()<8)
        {
            System.out.print("Password must be at least 8 characters. Please enter a new one:");
            password=sc.nextLine();
        }
        currentUser= new Customer(username,password,email,firstName,lastName,address,phoneNumber);
        System.out.println("Welcome to our E-Commerce Application. You are now logged in. Feel free to navigate through the menu below.");
        userLoggedIn=true;
        this.customerMenu();
    }

    // method to enter credit card payment info returns a CreditCardPayment object
    private CreditCardPayment enterCreditCardPayment() {
        String cardHolderName;
        String cardNumber;
        String expirationDate;
        String cvv;
        String country;
        System.out.println("----Enter the following Credit Card info----");
        System.out.print("Card holder name:\t");
        cardHolderName=sc.nextLine();
        cardHolderName=sc.nextLine();
        System.out.print("Card Number\t");
        cardNumber= sc.nextLine();
        System.out.print("Expiration year:\t");
        expirationDate=sc.nextLine();
        System.out.print("CVV:\t");
        cvv=sc.nextLine();
        System.out.print("Country:\t");
        country=sc.nextLine();
        return new CreditCardPayment(cardHolderName,cardNumber,expirationDate,cvv,country);
    }

    // method to enter PayPal payment info returns a PayPalPayment object
    private PayPalPayment enterPayPalPayment() {
        String paypalEmail;
        String paypalPassword;
        System.out.println("----Enter the following PayPal info----");
        System.out.print("Email:\t");
        paypalEmail = sc.nextLine();
        paypalEmail = sc.nextLine();
        System.out.print("Password:\t");
        paypalPassword = sc.nextLine();
        return new PayPalPayment(paypalEmail, paypalPassword);
    }

    public void logIn()
    {
        if(!userLoggedIn) {
            int choice1;
            int choice2,choice3;
            System.out.println("=============================================Login=============================================");
            System.out.println("1-Login as an admin\n2-Login as a customer\n0-Return to previous menu");
            choice1 = sc.nextInt();
            while (choice1!=0 && choice1 != 1 && choice1 != 2)
            {
                System.out.println("Invalid choice, please retry");
                choice1 = sc.nextInt();
            }
            if(choice1==0)
            {
                mainMenu();
            }
            else if (choice1 == 1) {
                String companyCode;
                System.out.println("Please enter the company code:");
                companyCode = sc.nextLine();
                companyCode = sc.nextLine();
                if (!Objects.equals(companyCode, Main.companyCode)) {
                    System.out.println("---Incorrect code---\n1-Retry\n0-Return to the Main menu");
                    choice2 = sc.nextInt();
                    while (choice2!=0 && choice2 != 1) {
                        System.out.println("Invalid choice, please retry");
                        choice2 = sc.nextInt();
                    }
                    if (choice2 == 1) {
                        logIn();
                    } else {
                        mainMenu();
                    }
                }
                else{
                    System.out.println("Admin logged in successfully.");
                    userLoggedIn=true;
                    this.adminMenu();
                }
            }
            else{
                String username;
                String password;
                System.out.println("Please enter your username:");
                username=sc.nextLine();
                username=sc.nextLine();
                System.out.println("Please enter your password:");
                password=sc.nextLine();
                currentUser=Customer.authenticateUser(username,password);
                if(currentUser==null)
                {
                    System.out.println("1-Retry\n0-Return to the Main menu");
                    choice3 = sc.nextInt();
                    while (choice3 !=0 && choice3 !=1) {
                        System.out.println("Invalid choice, please try again");
                        choice3 = sc.nextInt();
                    }
                    if (choice3 == 1) {
                        logIn();
                    } else {
                        mainMenu();
                    }
                }
                else{
                    System.out.println("Welcome to our E-Commerce Application. You are now logged in. Feel free to navigate through the menu below.");
                    userLoggedIn=true;
                    this.customerMenu();
                }
            }
        }
    }

    //methods in common
    public void filterByCategory () {
        System.out.println("=============================================Filter by Category=============================================");
        System.out.print("Please enter the category you want to filter by:\t");
        int category;
        for (int i=1;i<=categories.size();i++) {
            System.out.println(i+"- " + categories.get(i-1));
        }
        category = sc.nextInt();
        while (category < 1 || category > categories.size()) {
            System.out.println("Invalid category. Please retry");
            category = sc.nextInt();
        }
        inventory.filterByCategory(categories.get(category-1));
    }

    //admin methods
    public void adminMenu() {
        int choice;
        while (userLoggedIn) {
            System.out.println("=============================================Admin Menu=============================================");
            System.out.println(
                    """
                            1-List a new product to the inventory
                            2-View inventory
                            3-Search a product
                            4-Filter products by category
                            5-Update a product's quantity
                            6-Update a product's price
                            7-View all orders
                            8-View all customers
                            9-View general analytics
                            10-View customers complaints
                            0-Logout""");
            System.out.println("===============================================================================================");
            System.out.println("Please enter your choice:");
            choice = sc.nextInt();
            while (choice < 0 || choice > 10) {
                System.out.println("Invalid input, please try again");
                choice = sc.nextInt();
            }
            if (choice == 0) {
                currentUser = null;
                userLoggedIn = false;
                mainMenu();
            } else if (choice == 1) {
                listNewProduct();
            }
            else if (choice == 2) {
                viewInventory();
            }
            else if (choice == 3) {
                searchProductAdmin();
            }
            else if (choice == 4) {
                filterByCategory();
            }
            else if (choice == 5) {
                updateQuantityStock();
            }
            else if(choice==6)
            {
                updatePrice();
            }
            else if (choice == 7) {
                viewAllOrders();
            }
            else if (choice == 8) {
                viewAccounts();
            }
            else if (choice == 9) {
                viewAnalytics();
            }
            else{
                viewComplaints();
            }
        }
    }
    public void listNewProduct()
    {
        System.out.println("=============================================Adding a Product=============================================");
        System.out.println("Please enter the following information:");
        String name;
        double price;
        String description;
        String manufacturer;
        int quantity;
        System.out.print("Name:\t");
        name = sc.nextLine();
        name = sc.nextLine();
        System.out.print("Price:\t");
        price = sc.nextDouble();
        System.out.print("Description:\t");
        description = sc.nextLine();
        description = sc.nextLine();
        System.out.print("Manufacturer:\t");
        manufacturer = sc.nextLine();
        System.out.print("Quantity:\t");
        quantity = sc.nextInt();
        while(quantity<=0)
        {
            System.out.println("Invalid quantity. Please retry");
            quantity = sc.nextInt();
        }
        System.out.println("Which category does the product belongs to:");
        for (int i=1;i<=categories.size();i++) {
            System.out.println(i+"- " + categories.get(i-1));
        }
        int category = sc.nextInt();
        while (category < 1 || category > categories.size()) {
            System.out.println("Invalid category. Please retry");
            category = sc.nextInt();
        }
        Product product = new Product(name, price, description, manufacturer, categories.get(category-1));
        InventoryItem item = new InventoryItem(product, quantity);
        inventory.addItem(item);
        System.out.println("Product added successfully");
    }
    public void viewInventory()
    {
        System.out.println("=============================================Inventory=============================================");
        inventory.viewInventoryAdmin();
    }
    public void searchProductAdmin()
    {
        if(inventory.getInventory().isEmpty())
        {
            System.out.println("There are no products in the inventory yet.");
            return;
        }
        System.out.println("=============================================Search a Product=============================================");
        System.out.println("Please enter the name of the product you want to search for:");
        String name = sc.nextLine();
        name = sc.nextLine();
        inventory.searchItemAdmin(name);
    }
    public void updateQuantityStock()
    {
        System.out.println("=============================================Update a Products Quantity=============================================");
        inventory.viewInventoryAdmin();
        if(inventory.getInventory().isEmpty())
        {
            return;
        }
        System.out.println("Please enter the ID of the product you want to update:");
        int id = sc.nextInt();
        if(inventory.getItem(id)==null)
        {
            System.out.println("Product not found.\n1-Retry\n0-Cancel");
            int choice2=sc.nextInt();
            while(choice2!=0 && choice2!=1)
            {
                System.out.println("Invalid choice, please retry");
                choice2=sc.nextInt();
            }
            if(choice2==1)
            {
                updateQuantityStock();
            }
        }
        else {
            System.out.println("Please enter the new quantity:");
            int quantity = sc.nextInt();
            inventory.updateItem(id, quantity);
        }
    }
    public void updatePrice()
    {
        System.out.println("=============================================Update a Products Price=============================================");
        inventory.viewInventoryAdmin();
        if(inventory.getInventory().isEmpty())
        {
            return;
        }
        System.out.println("Please enter the ID of the product you want to update:");
        int id = sc.nextInt();
        if(inventory.getItem(id)==null)
        {
            System.out.println("Product not found.\n1-Retry\n0-Cancel");
            int choice2=sc.nextInt();
            while(choice2!=0 && choice2!=1)
            {
                System.out.println("Invalid choice, please retry");
                choice2=sc.nextInt();
            }
            if(choice2==1)
            {
                updatePrice();
            }
        }
        else {
            System.out.println("Please enter the new price:");
            double price = sc.nextDouble();
            inventory.getItem(id).getProduct().setPrice(price);
        }
    }
    public void viewAllOrders()
    {
        System.out.println("=============================================Orders=============================================");
        if(Order.getOrders().isEmpty())
        {
            System.out.println("There are no orders yet.");
            return;
        }
        Order.getOrders().forEach (
                (k,v) -> System.out.println(v.toString())
        );
    }
    public void viewAccounts()
    {
        System.out.println("=============================================Customer Accounts=============================================");
        if(Customer.getUserDataBase().isEmpty())
        {
            System.out.println("There are no customers registered yet.");
            return;
        }
        for (Customer u : Customer.getUserDataBase().values()) {
            System.out.println(u.toString());
        }
    }
    public void viewAnalytics()
    {
        System.out.println("=============================================General Analytics=============================================");
        System.out.println("Total number of products in the inventory: " + inventory.getInventory().size());
        System.out.println("Total number of customer accounts: " + Customer.getUserDataBase().size());
        System.out.println("Total number of orders: " + Order.getOrders().size());
        System.out.println("Total sales: " + Order.totalSales());
        System.out.println("Average order value: " + Order.totalSales() / Order.getOrders().size());
        System.out.println("Total number of returning customers: " + Customer.totalReturningUsers());
    }
    public void viewComplaints()
    {
        System.out.println("=============================================Complaints=============================================");
        if(Complaint.getComplaints().isEmpty())
        {
            System.out.println("There are no complaints for now.");
            return;
        }
        for(Complaint c:Complaint.getComplaints())
        {
            System.out.println(c.toString());
            System.out.println("------------------------------------------------------------------------------------------------");
        }
    }

    //customer methods
    public void customerMenu() {
        int choice;
        while (userLoggedIn) {
            System.out.println("=============================================Customer Menu=============================================");
            System.out.println("""
                    1-View all Products
                    2-View available products
                    3-Review a product
                    4-View reviews of a product
                    5-Add product to shopping cart
                    6-Search a product
                    7-Filter products by category
                    8-View shopping cart
                    9-Remove product from shopping cart
                    10-Update quantity in shopping cart
                    11-Checkout
                    12-View all orders
                    13-View account information
                    14-Modify account information
                    15-Submit a complaint
                    0-Logout""");
            System.out.println("===============================================================================================");
            System.out.println("Please enter your choice:");
            choice = sc.nextInt();
            while (choice < 0 || choice > 15) {
                System.out.println("Invalid choice, please retry");
                choice = sc.nextInt();
            }
            if (choice == 0) {
                currentUser = null;
                userLoggedIn = false;
                mainMenu();
            } else if (choice == 1) {
                browseProducts();
            }
            else if (choice == 2) {
                browseAvailableProducts();
            }
            else if (choice == 3) {
                reviewProduct();
            }
            else if (choice == 4) {
                viewProductReviews();
            }
            else if (choice == 5) {
                addProductToCart();
            }
            else if (choice == 6) {
                searchProductCustomer();
            }
            else if (choice == 7) {
                this.filterByCategory();
            }
            else if (choice == 8) {
                viewShoppingCart();
            }
            else if (choice == 9) {
                removeFromCart();
            }
            else if (choice == 10) {
                updateQuantityCart();
            }
            else if (choice == 11) {
                checkout();
            }
            else if (choice == 12) {
                viewOrderHistory();
            }
            else if (choice == 13) {
                viewAccountInfo();
            }
            else if (choice == 14) {
                modifyAccountInformation();
            }
            else{
                complaintSubmission();
            }
        }
    }
    public void browseProducts()
    {
        System.out.println("=============================================All Products=============================================");
        inventory.viewInventoryCustomer();
    }
    public void browseAvailableProducts()
    {
        System.out.println("=============================================Available Products=============================================");
        inventory.viewAvailableProducts();
    }
    public void searchProductCustomer()
    {
        System.out.println("=============================================Search a Product=============================================");
        System.out.println("Please enter the name of the product you want to search for:");
        String name = sc.nextLine();
        name = sc.nextLine();
        inventory.searchItemCustomer(name);
    }
    public void reviewProduct(){
        System.out.println("=============================================Review a Product=============================================");
        System.out.println("Please enter the name of the product you want to review:");
        String name = sc.nextLine();
        name = sc.nextLine();
        InventoryItem item = inventory.getItem(name);
        if (item == null) {
            System.out.println("Product not found");
        } else {
            System.out.println("Is this the product you want to review: ");
            item.displayForCustomers(1);
            System.out.println("1.Yes\n2.No");
            int x = sc.nextInt();
            while (x != 1 && x != 2) {
                System.out.println("Invalid input, please try again: ");
                x = sc.nextInt();
            }
            if (x == 1) {
                System.out.println("Please enter your review:");
                String review = sc.nextLine();
                review=sc.nextLine();
                System.out.println("Please enter your rating 1-->5 :");
                double rating = sc.nextDouble();
                while (rating < 1 || rating > 5) {
                    System.out.println("Invalid rating, please try again: ");
                    rating = sc.nextDouble();
                }
                item.getProduct().addReview((new Review(currentUser, review, rating)));
                System.out.println("Thank you for reviewing this product :)");
            }
        }
    }
    public void checkout()
    {
        if(currentUser.getCart().isEmpty())
        {
            System.out.println("Your shopping cart is empty. Please add some products to it first.");
            return;
        }
        System.out.println("=============================================Checkout=============================================");
        boolean v=currentUser.getCart().checkQuantities();
        if(v) {
            System.out.println("Your total cost is: " + currentUser.getCart().totalCost());
            System.out.println("Do you wish to proceed with the checkout: ");
            System.out.println("1.Yes\n2.No");
            int x = sc.nextInt();
            while (x != 1 && x != 2) {
                System.out.println("Invalid input, please try again: ");
                x = sc.nextInt();
            }
            if (x == 1) {

                System.out.println("Choose which payment method you wish to use: ");
                System.out.println("1.PayPal\n2.Credit Card");
                int y = sc.nextInt();
                while (y != 1 && y != 2) {
                    System.out.println("Invalid input, please try again: ");
                    y = sc.nextInt();
                }
                if (y == 1) {
                    currentUser.setP(enterPayPalPayment());

                } else {
                    currentUser.setP(enterCreditCardPayment());
                }
                System.out.println("Choose the shipping method you wish to use: ");
                System.out.println("1.One-Day Shipping(20$)\n2.3-Day Shipping(10$)\n3.7-Day Shipping(Free)");
                int z = sc.nextInt();
                while (z != 1 && z != 2 && z != 3) {
                    System.out.println("Invalid input, please try again: ");
                    z = sc.nextInt();
                }
                int shippingCost;
                if (z == 1) {
                    shippingCost = 20;
                } else if (z == 2) {
                    shippingCost = 10;
                } else {
                    shippingCost = 0;
                }
                currentUser.getOrders().add(new Order(currentUser, currentUser.getCart()));
                currentUser.getCart().checkout(currentUser.getP(), shippingCost);
                System.out.println("Your order has been placed successfully.");
            }
        }
    }
    public void removeFromCart()
    {
        if(currentUser.getCart().isEmpty())
        {
            System.out.println("Your shopping cart is empty. Please add some products to it first.");
            return;
        }
        System.out.println("=============================================Remove Product from Shopping Cart=============================================");
        System.out.println("-----Shopping Cart-----");
        currentUser.getCart().removeItemFromCart();
    }
    public void updateQuantityCart()
    {
        if(currentUser.getCart().isEmpty())
        {
            System.out.println("Your shopping cart is empty. Please add some products to it first.");
            return;
        }
        System.out.println("=============================================Update Quantity in Shopping Cart=============================================");
        System.out.println("-----Shopping Cart-----");
        currentUser.getCart().viewCart();
        System.out.println("Please enter the Index of the product you wish to update its quantity: ");
        int i;
        i=sc.nextInt();
        while(i<1 || i>currentUser.getCart().getItems().size())
        {
            System.out.println("Invalid input, please try again: ");
        }
        System.out.println("Please enter the new quantity: ");
        int newQuantity;
        newQuantity=sc.nextInt();
        while(newQuantity>currentUser.getCart().getItems().get(i-1).getItem().getQuantity()) {
            System.out.println("Sorry, the quantity you entered is not available in stock. Please try again: ");
            newQuantity = sc.nextInt();
        }
        currentUser.getCart().updateQuantityInCart(newQuantity,i-1);
    }
    public void viewProductReviews()
    {
        System.out.println("=============================================View Reviews of a Product=============================================");
        inventory.viewInventoryCustomer();
        if(inventory.getInventory().isEmpty())
        {
            return;
        }
        System.out.println("Please enter the index of the product you want to view its reviews:");
        int index = sc.nextInt();
        while (index < 1 || index > inventory.getInventory().size()) {
            System.out.println("Invalid index, please try again: ");
            index = sc.nextInt();
        }
        inventory.getInventory().get(index - 1).getProduct().printReviewsRating();
    }

    public void viewShoppingCart()
    {
        if(currentUser.getCart().isEmpty())
        {
            System.out.println("Your shopping cart is empty. Please add some products to it first.");
            return;
        }
        System.out.println("=============================================Shopping Cart=============================================");
        currentUser.getCart().viewCart();
    }
    public void addProductToCart(){
        System.out.println("=============================================Add Product to Shopping Cart=============================================");
        inventory.viewAvailableProducts();
        if(inventory.getInventory().isEmpty())
        {
            return;
        }
        System.out.println("Please enter the index of the product you want to add to your shopping cart:");
        int index = sc.nextInt();
        if (index < 1 || index > inventory.getInventory().size()) {
            System.out.println("Invalid index.\n1-try again\n0-Cancel");
            int choice = sc.nextInt();
            while (choice != 0 && choice != 1) {
                System.out.println("Invalid input, please try again: ");
                choice = sc.nextInt();
            }
            if (choice == 1) {
                addProductToCart();
            }
        }
        else {
            System.out.println("Please enter the quantity you want to add:");
            int quantity = sc.nextInt();
            while (quantity < 1) {
                System.out.println("Invalid Quantity, please try again: ");
                quantity = sc.nextInt();
            }
            currentUser.getCart().addProductToCart(inventory.getInventory().get(index - 1), quantity);
            System.out.println("Product added successfully.");
        }
    }

    public void viewOrderHistory()
    {
        System.out.println("=============================================Order History=============================================");
        if(currentUser.getOrders().isEmpty())
        {
            System.out.println("You have no orders yet.");
            return;
        }
        for (Order o : currentUser.getOrders()) {
            System.out.println(o.toString());
            System.out.println("------------------------------------------------------------------------------------------------");
        }
    }
    public void viewAccountInfo()
    {
        System.out.println("=============================================Account Information=============================================");
        System.out.println(currentUser.toString());
    }
    public void modifyAccountInformation()
    {
        System.out.println("=============================================Modify Account Information=============================================");
        System.out.println("Please choose which information you want to modify:");
        System.out.println("""
                1-Change Password
                2-Change Email
                3-Change Address
                4-Change Phone Number
                0-Cancel""");
        int x = sc.nextInt();
        while (x < 0 || x > 4) {
            System.out.println("Invalid choice, please retry");
            x = sc.nextInt();
        }
        if (x == 1) {
            System.out.println("Please enter your new password:");
            String password = sc.nextLine();
            password = sc.nextLine();
            while (password.length() < 8) {
                System.out.println("Password must be at least 8 characters, please enter a new one:");
                password = sc.nextLine();
            }
            currentUser.setPassword(password);
            System.out.println("Password changed successfully.");
        }
        else if (x == 2) {
            System.out.println("Please enter your new email:");
            String email = sc.nextLine();
            email = sc.nextLine();
            currentUser.setEmail(email);
            System.out.println("Email changed successfully.");
        }
        else if (x == 3) {
            System.out.println("Please enter your new address:");
            String address = sc.nextLine();
            address = sc.nextLine();
            currentUser.setAddress(address);
            System.out.println("Address changed successfully.");
        }
        else if(x==4){
            System.out.println("Please enter your new phone number:");
            String phoneNumber = sc.nextLine();
            phoneNumber = sc.nextLine();
            currentUser.setPhoneNumber(phoneNumber);
            System.out.println("Phone number changed successfully.");
        }
    }
    public void complaintSubmission()
    {
        System.out.println("=============================================Submit a Complaint=============================================");
        System.out.println("Please enter your complaint:");
        String complaint=sc.nextLine();
        complaint=sc.nextLine();
        Complaint.getComplaints().add(new Complaint(complaint,currentUser));
        System.out.println("Complaint submitted successfully.");
    }
}
