
# **Project Overview**

This Java-based console application replicates real-world e-commerce functionalities, incorporating features such as user authentication, product management, shopping cart operations, order processing, inventory management, dynamic product search, payment processing, user reviews, and shipping options.

---

## **Business-to-Customer (B2C) Application**

Designed for a specific company to showcase its products online, the application serves both administrators and customers.

---

## **`Customer` Class**

Customers are represented by the class `Customer` where there’s all of their personal information.

The class `Customer` also contains:

- A static map that contains all the customers that signed up to the application
- A shopping cart associated with each customer
- A list of orders that track the history of orders of each customer

---

## **Admin Authentication**

I decided not to include a class for the admins because I think it is unnecessary

I don’t need to preserve information for each employee who has access to the application(The staff database is managed elsewhere). So if an employee has access he will only need a code to enter.

---

## **Login/Logout System**

- New customers are required to sign in first to browse the menu
- Once the new customer has created his account he will be directly logged in to the application.
- Customers having an account can log in and log out of their account anytime they desire.
- Admins are required to input the confidential company code (which is `1234`) to log in, ensuring secure access that is limited to company staff.
- Usernames must be unique.
- Passwords require a minimum length of 8 characters for enhanced security.

---

## **Role-Specific Menus**

### **Admin Menu**

- List a new product to the inventory
- View Inventory
- Search a product
- Filter a product by category
- Update a product’s quantity
- Update a product's price
- View all customers
- View general analytics
- View customer complaints

### **Customer Menu**

- View all products
- View available products
- Review a product
- View reviews of a product
- Add product to shopping cart
- Search a product
- Filter products by category
- View shopping cart
- Remove a product from the shopping cart
- Update the quantity of an item in the shopping cart
- Checkout
- View all orders
- View account information
- Modify account information
- Submit a complaint

---

## **`Product` Class**

The `Product` class represents the product to sell.

It contains all of the information regarding the product as well as a list of reviews on the product itself and its average rating.

I decided not to declare the `Product` class as abstract for the simple reason that I don’t think it has any use. The different categories of products don’t have unique attributes or behaviors. They all share the same class members.

The category of the product is simply stored in a variable inside the class and any specific information related to a certain category will be in the description part of the product.

In addition to that, having a single class `Product` is more simple to manage and easier to scale: adding a new category doesn’t require adding a new class.

---

## **`InventoryItem` Class**

Represents an item in the inventory, characterized by a product and a quantity.

---

The `Product` class is responsible for defining the different properties of a product whereas the `InventoryItem` class is responsible for managing the inventory-related details

That’s why I decided to separate these two classes. Moreover, this separation allows for better modularity, maintainability, and scalability of the system. 

For example, a new `Supplier` class can interact with the product class without being concerned with the inventory details.

---

## **`Inventory` Class**

Manages the inventory, tracks products, and handles display operations.

---

## **Shopping Cart Classes**

- **`CartItem`**: Represents shopping cart items.
- **`ShoppingCart`**: Tracks cart items, allows CRUD operations, and handles checkout.

The checkout method allows the user to finalize his purchase and continue to the order processing stage.

The quantities of the cart items are verified just before the checkout to check whether the demanded quantities are still available in the inventory.

The cart is automatically cleared after the checkout 

---

## **Strategy Pattern in Payment Processing**

The payment processing system uses the Strategy pattern, offering PayPal and Credit Card options.

---

## **`Order` Class**

Represents an order made by a customer, including order details and a static map acting as a database for order tracking.

---

## **`Review` Class**

Represents a review made by a customer on a product, including customer details, review text, and a rating from 1 to 5.

---

## **Complaint Feature**

To enhance customer satisfaction and overall user experience I integrated a complaint feature.

This feature provides customers with a direct channel to voice their concerns or report issues about any aspect of the shopping experience.

The admins can subsequently review these complaints and work to meet users’ expectations better.

---

## **Shipping Options**

The application offers three shipping options with varying durations and associated fees.

---

## **`UserActions` Class**

Encapsulates all menu operations, including inventory management and customer interactions.

---

## **`Main` Class**

Contains an instance of the **`UserActions`** class, initiating the application by executing the **`mainMenu`** method.