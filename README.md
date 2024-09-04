# ZIGATO_food_order_system

ZIGATO_food_order_system is a comprehensive solution for managing food orders in a restaurant. This project is designed to streamline the ordering process, handle customer data, and manage menu items efficiently. It provides a robust backend using Java and Spring Boot, with a MySQL database for data management. The system also includes JWT-based authentication for secure access, detailed API documentation via Swagger, and effective monitoring and logging.

## Technologies Used

- **Backend**: Java, Spring Boot, JPA, 
- **Database**: MySQL
- **Authentication**: JWT
- **API Documentation**: Swagger
- **Logging**: SLF4J
- **Monitoring**: Spring Boot Actuator
  
## Features

### Authentication
- **Sign Up**: Register a new user.
- **Sign In**: Authenticate and obtain a JWT token for secured API access.

### Cart Management
- **Add Item to Cart**: Add a specific food item to the user's cart.
- **Update Item Quantity**: Change the quantity of an item in the cart.
- **Remove Item from Cart**: Delete an item from the cart.
- **Clear Cart**: Empty the entire cart.
- **Get User Cart**: Retrieve the current cart details of the user.

### Order Management
- **Create Order**: Place a new order with selected items and delivery address.
- **Get Order History**: Retrieve the list of past orders for the user.
- **Update Order Status**: Change the status of an order (e.g., COMPLETED, DELIVERED).

### Restaurant Management
- **Create Restaurant**: Add a new restaurant to the system.
- **Update Restaurant**: Modify the details of an existing restaurant.
- **Delete Restaurant**: Remove a restaurant from the system.
- **Update Restaurant Status**: Change the operational status of a restaurant (e.g., OPEN, CLOSED).
- **Get All Restaurants**: List all restaurants available.
- **Find Restaurant by ID**: Retrieve details of a specific restaurant.
- **Search Restaurants**: Find restaurants based on a search keyword.
- **Add to Favorites**: Add a restaurant to the user's list of favorite restaurants.

### Food Management
- **Create Food Item**: Add a new food item to a restaurant’s menu.
- **Update Food Availability**: Modify the availability status of a food item.
- **Delete Food Item**: Remove a food item from the menu.
- **Search Food**: Find food items based on name.
- **Get Food by Restaurant**: List food items available at a specific restaurant.

### Ingredient Management
- **Create Ingredient Item**: Add a new ingredient item.
- **Update Ingredient Stock**: Adjust the stock quantity of an ingredient.
- **Create Ingredient Category**: Define a new category for ingredients.
- **Get Restaurant Ingredients**: List all ingredients for a specific restaurant.
- **Get Restaurant Ingredient Categories**: List ingredient categories for a specific restaurant.

### Category Management
- **Create Category**: Add a new category.
- **Get Category by ID**: Retrieve details of a specific category.
- **Get Categories for Restaurant**: List all categories associated with a specific restaurant.

### User Management
- **Get User Profile**: Retrieve user profile information using a JWT token.

### Responses
- **Successful Operation**: Each endpoint provides a success response with the relevant data.
- **Error Handling**: Appropriate error messages are returned for failed operations.



## Swagger API Documentation UI Screenshots

<img src="https://imgur.com/wVQ9kik.png">
<img src="https://imgur.com/1zFrrko.png">
<img src="https://imgur.com/psaID1W.png">
<img src="https://imgur.com/d5PNfHE.png">
<img src="https://imgur.com/UG6iHqX.png">
<img src="https://imgur.com/uUhaiKD.png">



## Prerequisites

- **Java**: JDK 8 or later
- **Maven**: 3.6 or later
- **MySQL**: 5.7 or later

## Getting Started

1. **Clone the repository**:
    ```bash
    git clone https://github.com/HiteshChandra001/ZIGATO_food_order_system.git
    cd backend
    ```

2. **Configure the MySQL Database**:

    Create a database named `ziagto_db` and update the `application.properties` file in the `src/main/resources` directory with your database credentials:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/ziagto_db
    spring.datasource.username=yourusername
    spring.datasource.password=yourpassword
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    ```

3. **Build and Run the Backend**:
    ```bash
    mvn clean install
    mvn spring-boot:run
    ```
    
    The backend server will run on `http://localhost:8080`.


3. **API Documentation**:

    Access the Swagger UI for detailed API documentation at:

    ```bash
    `http://localhost:8080/swagger-ui/index.html`
    ```


## Authentication

- **Register**: Use the `/auth/signup` endpoint to create a new user.
- **Login**: Use the `/auth/signin` endpoint to authenticate and receive a JWT token.
- **Secure API Calls**: Include the JWT token in the `Authorization` header as a Bearer token for protected endpoints.
