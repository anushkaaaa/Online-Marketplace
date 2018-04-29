# Online-Marketplace




----------------------------------------------------
Note:
Read the report for detailed information about the project

----------------------------------------------------

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
20.	<b>DatabaseMapper.java</b> - The data mapper class bewteen the model and database
21.	<b>Makefile</b> - The Makefile
22.	<b>policy</b> - policy file for granting permissions 
23.	<b>Report</b> - Report 
24. <b>mysql-connector-java-5.1.46-bin.jar</b> - mysql connector jar file
25. <b>README.md </b><br>

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
