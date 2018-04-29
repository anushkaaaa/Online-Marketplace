# Online-Marketplace





----------------------------------------------------
Aim:
To implement previously unimplemented functionality in our system as well as to explore the consequences of concurrency in our Marketplace Application.

-----------------------------------------------------
Discussion:

A thread is an execution context which can run together with other threads in the same environment. Threads in Java play an important role for the internal working of Java RMI. In previous assignment I noticed that the system still works well even when multiple clients request the server to execute their queries at same time. This is because every time when a RMI call comes in, the server- thread will generate a new thread which handles the request. Thus, this Java threading mechanism in Java RMI makes it possible for one remote object to be concurrently invoked multiple times by different threads.

![alt text](https://github.com/anushkaaaa/Online-Marketplace/blob/Version-03/rmi.png)

In figure above, we notice this concurrent behavior of Java RMI wherein, customer1 and customer both try to purchase the item whose quantity is 1 concurrently. Since, customer1 purchases it slightly prior to customer the item is purchased by customer1 and for customer it displays item out of stock. Thus, through this test I have observed that RMI is multi-threaded, allowing the server to exploit Java threads for better concurrent processing of client requests
The only concern here is that the state of the remote object will soon be invalid if not provided with the guard access to the remote method. In scenario, when I ran the application on two physically different desktops and made an attempt to purchase item whose quantity is 1 at same time, both customers were able to purchase the item as it couldn’t synchronize the request. Thus, doesn’t ensure thread safety.
This can be achieved by the synchronized keyword in Java. We will discuss about this in more detail in this assignment.

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
18.	<b>AuthorizationInvocationHandler.java</b> - The file for implementing role based access control
19.	<b>items.txt</b> - The item list text file
20.	<b>Makefile</b> - The Makefile
21.	<b>policy</b> - policy file for granting permissions 
22.	<b>README.md </b><br>

----------------------------------------------------
Compilation and Execution:

1. Clone or download the folder:<br>
Server is running on : in-csci-rrpc01.cs.iupui.edu 10.234.136.55
2. Open an instance of putty, Run the RMI Registry in the background - this will run at the set port(1400):<br>
<b>command: rmiregistry 1400&</b> <br>
3. Run the makefile <br>
<b>command: make</b> <br>
4. Run the MarketplaceServer<br>
<b>command: java -Djava.security.policy=policy MarketplaceServer</b><br>
5. Open instances of putty on the above machines and run the MarketplaceClient<br>
• in-csci-rrpc02.cs.iupui.edu - 10.234.136.56<br>
• in-csci-rrpc03.cs.iupui.edu - 10.234.136.57<br>
• in-csci-rrpc04.cs.iupui.edu - 10.234.136.58<br>
• in-csci-rrpc05.cs.iupui.edu - 10.234.136.59<br>
• in-csci-rrpc06.cs.iupui.edu - 10.234.136.60<br>
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
Depending on the usertype it will display the contents

---------------------------------------------------
