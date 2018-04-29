# Online-Marketplace





----------------------------------------------------
Aim:
To implement the Front Controller pattern as part of our existing Marketplace application along with partial implementation of the logic for the Login requirement. The function should be included for both Administrators as well as Customers with distinct views along with the use of Command and Abstract Factory patterns.

----------------------------------------------------
Requirements:
- Implementation of Front Controller Pattern
- Implementation of Command and Abstract Factory patterns
- Display two distinct view for both user types (Administrators, Customers)

----------------------------------------------------
Design Patterns

Here, I have made use of the following patterns:
- Front Controller Pattern
- Command Pattern
- Abstract Factory Pattern

Front Controller Pattern:
It consists of 3 entities: Front Controller, Dispatcher and View. In my implementation, the MarketplaceFrontController is the entry point of the application. It handle’s all the client request coming into the application. Request in our case is the login, user input’s his login credentials into the application and the front controller uses a dispatcher object by creating an instance of the MarketplaceDispatcher class which then dispatches the request to the model for authentication.

Command Pattern:
In command pattern, invoker plays an important role. The invoker handles the command and manages execution of the command. The main question asked in command pattern is what is to be done? In my implementation, I have tried to specify the same question what? In this assignment the question is what view should be displayed. For this purpose, I have created an Invoker class that has commands to display the views for customer and administrator. The Invoker class commands to call for the view based on the user type.

Abstract Factory Pattern:
According to the Gang of Four book the abstract factory pattern provides an interface for creating families of related or dependent objects without specifying their concrete classes. In my implementation, the AbstractFactory defines an interface UserPage that all the concrete factories need to implement. The concrete factories in my case are: CustomerFactory and AdminFactory, both the class have implemented the UserPage interface, creating two separate families. The AbstractFactory has two abstract methods which are implemented in the MarketplaceDispatcher. The MarketplaceDispatcher acts as the factory producer and thus, on implementing this we achieve to get two distinct views for each of the users.

-----------------------------------------------------
Content:<br>
1. <b>MarketplaceServer.java</b> - The server class file
2. <b>MarketplaceModel.java</b> - The model class file
3. <b>MarketplaceFrontController.java</b> - The entry point class
4. <b>MarketplaceView.java</b> - The view class file
5. <b>Marketplace.java</b> - The remote interface which extends java RMI Remote method
6. <b>MarketplaceDispatcher.java</b> - The controller class file 
7. <b>AbstractFactory</b> - The abstract class to implement the abstract factory pattern
8. <b>AdminFactory</b> - The factory class to call the admin view
9. <b>CustomerFactory</b> - The factory class to call the customer view
10. <b>Invoker</b> - The class that has methods to invoke command to display views based on usertype
11. <b>UserPage</b> - The interface for abstract factory
12. <b>Makefile</b> - The Makefile
13. <b>policy</b> - policy file for granting permissions 
14. <b> README.md </b><br>

----------------------------------------------------
Compilation and Execution:

1. Clone or download the folder, place the contents into your directory on Tesla.
2. Open an instance of putty, Run the RMI Registry in the background - this will run at the set port(1400):<br>
<b>command: rmiregistry 1400&</b> <br>
3. Run the makefile <br>
<b>command: make</b> <br>
4. Run the MarketplaceServer<br>
<b>command: java -Djava.security.policy=policy MarketplaceServer</b><br>
5. Open a second instance of putty and run the MarketplaceFrontController<br>
<b>command: java -Djava.security.policy=policy MarketplaceFrontController</b><br>
6. To clean up i.e. clear the port number by killing the rmiregistry <br>
<b>command: fg</b><br>

---------------------------------------------------
On the client side: <br>
<br>
It will prompt for username and password<br>
The username and passwords are as follows:<br>
For Admin:<br>
username: admin password: admin<br>
For Customer:<br>
username: customer password: customer<br>
Depending on the usertype it will display the contents

---------------------------------------------------
