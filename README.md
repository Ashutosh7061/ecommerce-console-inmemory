# E-Commerce Console Application

A **Core Java-based E-Commerce Product & Order Management System** that simulates basic online shopping operations through a console interface. The project focuses on applying Java fundamentals and organizing code using a layered architecture.

## Features

- **Product Management:** Add, view, search, update, and delete products.
- **Customer Management:** Customer registration, login, and customer details management.
- **Shopping Cart:** Add and remove products, update quantities, and view cart items.
- **Order Management:** Place orders, view order history, and cancel orders.
- **Inventory Management:** Validate stock availability, reduce stock after successful orders, and restore stock on cancellation.
- **Product Search & Sorting:** Search by product ID, name, category, and brand; sort products using `Comparable` and `Comparator`.
- **Exception Handling:** Custom exceptions for handling invalid operations and application errors.

## Technologies & Concepts

- Core Java and Object-Oriented Programming (OOP)
- Java Collections Framework (`ArrayList`, `ConcurrentHashMap`)
- Stream API
- `Comparable` and `Comparator`
- Enums and Custom Exceptions
- Layered Architecture (Model, Repository, Service, Main)

## Project Structure

```text
src/
└── com.ashutosh.ecommerce/
    ├── model/
    ├── repository/
    ├── service/
    ├── exception/
    ├── comparator/
    └── main/
```

- **Model:** Represents products, customers, cart items, and orders.
- **Repository:** Manages in-memory application data.
- **Service:** Implements business logic and validations.
- **Exception:** Contains custom exception classes.
- **Comparator:** Defines custom product sorting rules.
- **Main:** Starts the application and provides console menus.

## How to Run

1. Clone or download this repository.
2. Import the project into your Java IDE.
3. Run `EcommerceApplication.java`.
4. Follow the console menu to access Admin and Customer features.

## Data Storage

The application uses Java Collections to store data in memory. No database or external persistence is used, so data is reset when the application terminates.

## Learning Outcomes

This project provided practical experience with Java Collections, Stream API, OOP, exception handling, sorting, and separation of responsibilities through a layered architecture.