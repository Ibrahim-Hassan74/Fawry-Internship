# Shopping App - Fawry Internship Challenge

This is a console-based Java application simulating an e-commerce shopping system, built for the Fawry Internship
challenge.

## 🔍 Description

The application allows a customer to log in or register, view and purchase products, and proceed to checkout with full
validation, shipping, and receipt generation.

This project goes beyond the base requirements by adding an interactive user experience and simulating real-world
e-commerce behaviors.

---

## ✅ Features

### 🔐 Authentication

* Register with username, password, and starting balance
* Login with username and password

### 💰 Balance Management

* Add funds to your account at any time

### 🛒 Cart & Checkout

* Add products to cart by ID and quantity
* View available products (skips out-of-stock)
* Cart validation before checkout:

    * Cart must not be empty
    * No expired products
    * Quantity must be available in stock
    * Sufficient balance
* After checkout:

    * Receipt is printed line by line
    * Products are shipped if shippable
    * Product quantities are reduced and saved to JSON

### 📦 Product Handling

* Products with quantity = 0 are ignored on load
* Products with quantity = 0 are removed when saving

### 🎯 User Flow

* After login, user chooses to:

    * Buy products
    * Add funds
    * Checkout
    * Exit
* After each operation, user is asked to continue or exit

---

## 📁 File Structure

```
src/
  data/
    products.json
    accounts.json
  entities/
    Customer.java
    Product.java
    Cart.java
    CartItem.java
    Account.java
  service/
    AuthService.java
    CheckoutService.java
    ProductService.java
    ShippingService.java
  serviceContracts/
    IAuthService.java
    ICheckoutService.java
    IProductService.java
    IShippingService.java
  utils/
    JsonHelper.java
  Main.java
  ShoppingApp.java
```

---

## 📌 Assumptions & Extra Features

The following additions were made beyond the base challenge for realism:

* Added user authentication to simulate real-world customer management
* Users can recharge their balance at any time
* After every action, users can return to the main menu
* Products are filtered based on stock both at load-time and during saving
* Product list and receipts are displayed line by line to simulate POS behavior

These additions make the app more complete and user-friendly.

---

## 🛠 Tech Stack

* Java 17+
* Gson for JSON parsing
* Console-based UI

---

## ✅ How to Run

1. Clone the repo
2. Ensure `accounts.json` and `products.json` are in `src/data/`
3. Run `Main.java` (which calls `ShoppingApp.run()`)

---

## 👨‍💻 Developed by

Ibrahim Hassan
Fawry Internship Candidate
