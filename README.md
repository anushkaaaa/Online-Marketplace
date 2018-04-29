# Online-Marketplace





----------------------------------------------------
Aim:
To examine the impact that the concept that Synchronization has on our Marketplace Application.

----------------------------------------------------
Design Patterns

, the main objective was to observe the synchronization in the application. For this purpose, I have made use of the following machines:
-	in-csci-rrpc01.cs.iupui.edu - 10.234.136.55 (Server and MySQL Database)
-	in-csci-rrpc02.cs.iupui.edu - 10.234.136.56
-	in-csci-rrpc03.cs.iupui.edu - 10.234.136.57
-	in-csci-rrpc04.cs.iupui.edu - 10.234.136.58
-	in-csci-rrpc05.cs.iupui.edu - 10.234.136.59
-	in-csci-rrpc06.cs.iupui.edu - 10.234.136.60
The server runs on the 10.234.136.55 machine.
In this assignment, I’ll be discussing the following patterns
-	Monitor Object Pattern
-	Future Pattern
-	Guarded Suspension Pattern
-	Scoped Locking Pattern
-	Thread-Safe Interface Pattern
In our application, the main problem is when multiple customers try to access the same object or same resource from the server and in return the server is not able distinguish between the multiple service requests which results in error result. So, it is very important to ensure synchronization in the application so that all requests are serviced by the server accurately additionally, providing concurrency in the system. Thus, it is very important to ensure that only one thread can access the resource at a given instance of time. 
This can be achieved using "synchronized" keyword in Java. For this purpose, I have made use of synchronized blocks. Synchronized blocks provide a way by which only one thread can attempt to enter the synchronized block and until it exits the block no other thread is permitted to enter the synchronized block. Using this we can achieve both concurrency and thread safety in our system. Following is the code snippet from our marketplace application source code:

//To purchase the items
@Override
public synchronized String purchase(Session session, int id) throws 
RemoteException{
   ResultSet resultSet;
   int quantity=0;
   // Synchronized block
   synchronized (this){
           try {
               resultSet = databaseConnection.getItem(id);
           while(resultSet.next()){
               quantity = Integer.parseInt(resultSet.getString("itemQuantity"));
               }
           resultSet.beforeFirst();
           if(quantity>0) {
               quantity--;
               databaseConnection.updateQuantity(quantity, id);
               return "Item Purchased!";
               }
           } catch (SQLException e) {
               System.out.println("Purchase:: Result set error");
           }
       }
       return "Item out of stock!";
}

Thus, using this synchronized block only one thread can access the share variable and other shared resource at a given time

Monitor Object Pattern: This pattern helps in the execution of multi-threaded applications. It ensures that only one thread can access the shared resource at a given time, all the other threads attempting to access the same resource are blocked until the time thread is accessing the resource. It consists of three components: Monitor Object, Monitor Condition and Monitor Lock. The monitor uses the lock to ensure that only a single thread is active in the monitor at a given time, it does this using the Monitor Lock acquire() and releases the lock when thread completes it’s execution using the release(). While, the monitor object is locked it prevents other threads from accessing the lock by using the Monitor Conditions: wait(), notify() and notifyAll(). 
In this assignment, I have achieved this by using the synchronized keyword by implementing the synchronized blocks and methods at places where I think the lock acquire and release mechanism is needed. The synchronized keyword by Java does this for us.

Future Pattern: In a single threaded application when we call a method it returns only after the computation is successfully done. At times, this call takes arbitrary long time to compute and thus increases the latency. If a we get a way to do other independent processing while the called method is being computed it might reduce the overall latency. This can be achieved using Future Pattern. Using this pattern, you don’t need to wait for the results of the called method rather you get a virtual object which promises to be available in future and can be kept as reference until we are able retrieve it. In simple terms, Future is a proxy for an object that is not yet there.
In this assignment, I have achieved this by using the synchronized keyword by implementing the at places where I found the need of future object. This keyword informs java whether it needs to synchronize the method or block and provides a virtual object for referencing and notifies the user when on availability of result. The synchronized keyword by Java does all of this for us

Guarded Suspension Pattern: This pattern is implemented to allow only one thread to access the shared resource and blocks the other threads from accessing the same resource until it is being used the thread. This pattern requires both a lock to be acquired and a pre-condition to be satisfied unlike monitor object pattern. It is used when a method call will be suspended for a finite period. To assist the guarded suspension we have wait() and notify() methods.
In this assignment, I have achieved this by using the synchronized keyword by implementing the synchronized blocks and methods at places where I think the lock and pre-conditions are required to be satisfied by the thread. If the conditions are not satisfied the thread moves to the wait state and once the conditions are met the threads waiting are notified. The synchronized keyword by Java does this for us.

Scoped Locking Pattern: Imagine, a scenario if the programmer forgets to release the lock from a method or block. In this case the even after the thread finishes its execution the object will be locked, and no other thread will be able to access the resource. Thus, we require a mechanism using which we ensure that a lock should always be acquired and released properly when control enters and leaves critical sections. We can do this using the scope locking pattern, we define a guard class that acquires and releases a particular lock automatically within a method or block scope.
In this assignment, I have made use of synchronized block in Java at some places, using this the compiler generates the corresponding byte code that includes the instructions monitorenter and monitorexit. An exception handler is also created to handle those cases when unexpected termination occurs.

Thread-Safe Interface Pattern: This pattern minimizes locking overhead and ensures that intra-component method calls do not incur self- deadlock by trying to reacquire a lock that is held by the component already. In this assignment, I have made use of synchronized keyword in Java at top of all methods which ensures that there is no deadlock condition. The synchronized keyword by Java does provides thread safety for us.

I have applied this synchronized keyword on various places, which ensures that the system runs accurately providing thread synchronization and concurrency. Thus, this demonstrates the implementation of Monitor Object Pattern, Future Pattern, Guard Suspension Pattern, Scope Locking Pattern and Thread-Safe Interface Pattern using synchronized keyword.

-----------------------------------------------------
Updates:

I have implemented the register functionality which allows new users to register into the marketplace application. Additionally, I have included the update and remove functionalities. Update allows the admin to update the item from inventory and remove allows the admin to remove the selected item from the inventory. I have added the cart, which allows customer to add items into the cart and eventually purchase all items from the cart. The customer can even select an item from the browse list and purchase that selected item without adding it into the cart. Moreover, I have switched from using text file as storage to MySQL database for storage. The DatabaseConnection.java file is responsible for establishing the connection to the database.


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
19.	<b>DatabaseConnection.java</b> - The file for database connection
20.	<b>Makefile</b> - The Makefile
21.	<b>policy</b> - policy file for granting permissions 
22.	<b>mysql-connector-java-5.1.46-bin.jar</b> - mysql connector jar file
23. <b>README.md </b><br>

----------------------------------------------------
Compilation and Execution:

1. Clone or download the folder:<br>
<b>Server is running on : in-csci-rrpc01.cs.iupui.edu 10.234.136.55</b>
2. Open an instance of putty, Run the RMI Registry in the background - this will run at the set port(1400):<br>
<b>command: rmiregistry 1400&</b> <br>
3. Run the makefile <br>
<b>command: make</b> <br>
4. Run the MarketplaceServer<br>
<b>command: java -cp .:mysql-connector-java-5.1.46-bin.jar -Djava.security.policy=policy MarketplaceServer</b><br>
5. Open instances of putty on the above machines and run the MarketplaceClient<br>
• in-csci-rrpc02.cs.iupui.edu - 10.234.136.56<br>
• in-csci-rrpc03.cs.iupui.edu - 10.234.136.57<br>
• in-csci-rrpc04.cs.iupui.edu - 10.234.136.58<br>
• in-csci-rrpc05.cs.iupui.edu - 10.234.136.59<br>
• in-csci-rrpc06.cs.iupui.edu - 10.234.136.60<br>
<b>command: java -cp .:mysql-connector-java-5.1.46-bin.jar -Djava.security.policy=policy MarketplaceClient</b><br>
6. To clean up i.e. clear the port number by killing the rmiregistry <br>
<b>command: fg</b><br>

---------------------------------------------------
On the client side: <br>
<br>
It will prompt for username and password<br>
The username and passwords are as follows:<br>
For Admin:<br>
username: admin password: admin<br>
username: admin1 password: admin1<br>
For Customer:<br>
username: customer password: customer<br>
username: customer1 password: customer1<br>
Depending on the usertype it will display the contents

---------------------------------------------------
Database:  <br>
username: ahpatil  <br>
password: ahpatil  <br>

---------------------------------------------------
