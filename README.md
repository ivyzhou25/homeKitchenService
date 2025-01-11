# homeKitchenService

This is a Java / Maven / Spring Boot restful web service that custom can register user, create business, upload menu dished and take customer order for their dishes. 

# How to run this service
1. Clone this repository
2. Open it in IDE such as eclipse
3. Make sure you are using Java 17 and Maven 3.3.x
4. Build and run this web service through IDE, e.g eclipse

# About the service
This web serivce provides different APIs for user to 
- BusinessController: 
	* /addBusiness: Post a json body with schema of Business class, create a new business
	* /getBizByName?name=<businessName>: get business by its name
	* /getBizById?id=<businessId>: get business by biz Id
	* /getBizByUser?id=<userId>: get business of a specified userId

- UserController:
	* /addUser: Post a json body with schema of User class, create a new user
	* /getUserById?id=<userId>: get a user by its id
	* /getUserByEmail?email=<email>: get a user by its email
	* /getUserByPhone?phone=<phoneNumber>: get a user byt its phone number

- DishController:
	* /addDish: Post a json body with schema of Dish class, upload a new dish for a home kitchen business
	* /getDishById?id=<dishId>: get a dish by its id
	* /getDishByName?name=<dishName>: get a dish by its name
	* /getDishByBiz?bizId=<businessId>: get all dishes of a home kitchen business

- UserOrderController:
	* /order/add: post a json body with schema of UserOrder class, add a new order
	* /order/{id}/items: get all detailed items of an order with specified id
	* /order/user/{id}: get all orders for a specified user id
	* /order/{id}: get order details for a specific order id





