# Online-Marketplace





----------------------------------------------------
Aim:
To expand the application to make use of the Authorization pattern through a role-based access control (RBAC) approach that will make use of Java Annotations. As part of this process we will also implement and explore further the Proxy pattern and the Reflection pattern

----------------------------------------------------
Requirements:
- Implementation of Authorization Pattern for role-based access control
- Implementation of Proxy and Reflection patterns

----------------------------------------------------
Design Patterns

Here, I have made use of the following patterns:
- Reflection Pattern
- Authorization Pattern
- Proxy Pattern

Reflection Pattern: Reflection pattern is an architectural pattern. It provides a mechanism for changing the structure and behavior of the system dynamically. In this assignment, I have tried to implement the reflection by creating a customized java annotation for role-based access control. The RequriesRole.java is the java file responsible for the customized java annotation.

Authorization Pattern: Authorization pattern is a structural pattern. In this assignment, I have tried to implement the authorization pattern for providing role-based access control. AuthorizationInvocationHandler.java file is responsible for implementing the role-based access control. AuthorizationException.java file is used to create a customized exception for invalid authorization. I have made use of Session.java to create the session and UserType.java to maintain the role (admin or customer) and implement session in the application, thus avoiding individual rights and setting associate rights based on the roles within the system.

Proxy Pattern: I have tried to implement this pattern by using the Java Dynamic Proxy along with the customized annotation. I have created an instance in MarketplaceServer.java which acts as a pass through to the real object based on the runtime implementations of Marketplace interface

-----------------------------------------------------
Improvements:

Following are the changes (improvements) made keeping future perspective in mind:
1) Separation of concerns:
Error: "This is not a Front Controller - this appears to be a Client that is wrapped up with a Front Controller. Here you are mixing the Front Controller with a RMI or Framework controller". I have created client class: MarketplaceClient to fix this.

-----------------------------------------------------
Content:<br>
1.	<b>MarketplaceServer.java</b> - The server class file
2.	<b>MarketplaceModel.java</b> - The model class file
3.	<b>MarketplaceClient.java</b> - The client class file
4.	<b>MarketplaceFrontController.java</b> - The entry point class
5.	<b>MarketplaceView.java</b> - The view class file
6.	<b>Marketplace.java</b> - The remote interface which extends java RMI Remote method
7.	<b>MarketplaceDispatcher.java</b> - The controller class file 
8.	<b>AbstractFactory.java</b> - The abstract class to implement the abstract factory pattern
9.	<b>AdminFactory.java</b> - The factory class to call the admin view
10.	<b>CustomerFactory.java</b> - The factory class to call the customer view
11.	<b>Invoker.java</b> - The class that implements methods to invoke command to display views based on usertype
12.	<b>ItemController.java</b> - The controller class for item
13.	<b>UserPage.java</b> - The interface for abstract factory
14.	<b>Session.java</b> - The session creator class
15.	<b>UserType.java</b> - For Session in the application
16.	<b>RequireRole.java</b> - The file for customized annotation
17.	<b>AuthorizationException.java</b> - The file for customized exception
18.	<b> AuthorizationInvocationHandler.java</b> - The file for implementing role based access control
19.	<b>Makefile</b> - The Makefile
20.	<b>policy</b> - policy file for granting permissions 
21.	<b> README.md </b><br>

----------------------------------------------------
Compilation and Execution:

1. Clone or download the folder, place the contents into your directory on Tesla.
2. Open an instance of putty, Run the RMI Registry in the background - this will run at the set port(1400):<br>
<b>command: rmiregistry 1400&</b> <br>
3. Run the makefile <br>
<b>command: make</b> <br>
4. Run the MarketplaceServer<br>
<b>command: java -Djava.security.policy=policy MarketplaceServer</b><br>
5. Open a second instance of putty and run the MarketplaceClient<br>
<b>command: java -Djava.security.policy=policy MarketplaceClient</b><br>
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
Depending on the usertype it will display the contents <br>
Admin has following access: Browse, Update, Add and Remove <br>
Customer has following access: Browse and Purchase <br>

---------------------------------------------------
