import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
 /**
 * Honor Pledge:
 * I pledge that I have neither given nor received any help on this assignment.
 * ahpatil
 *
 */
 // This is the dispatcher class
public class MarketplaceDispatcher extends UnicastRemoteObject  {
	private String name;
	private Marketplace myMarketplace;
	private AbstractFactory view;
	private Session session;
	
	public MarketplaceDispatcher(String name) throws RemoteException{
		try{
			this.name=name;
			view = new MarketplaceView();
			myMarketplace = (Marketplace) Naming.lookup(name);
		} catch(Exception e){
			System.out.println("MarketplaceDispatcher Exception: " +e.getMessage());
			e.printStackTrace();
		}
	}		
 	//It will take the inputs from view and on authentication will decide which control which view the user sees
 	public void check(String username, String password){
 		try{
			UserPage myView = view.getCustomerview();
			if(myMarketplace.AuthenticateAdmin(username,password).equals("admin")){
				//Creating session for admin
				session = myMarketplace.create("admin");
				// Create a new instance of a AdminInvoker
				Invoker invoker = new Invoker(view,"admin");
				// gets choice for options
				int choice = invoker.displayContent();
				// Create a new instance of a ItemController
				ItemController iController = new ItemController(name,session);
				// Sends choice and invoker to perform action
				iController.getChoice(choice,invoker);
			}
			else if(myMarketplace.AuthenticateCust(username,password).equals("customer")){
				//Creating session for customer
				session = myMarketplace.create("customer");
				// Create a new instance of a CustomerInvoker
				Invoker invoker = new Invoker(view,"customer");
				// gets choice for options
				int choice = invoker.displayContent();
				// Create a new instance of a ItemController
				ItemController iController = new ItemController(name,session);
				// Sends choice and invoker to perform action
				iController.getChoice(choice,invoker);
			}		
			else{
				myView.message("Incorrect username and password, Please try again");
			}
		}catch(Exception e){
			System.out.println("MarketplaceDispatcher Exception::"+e.getMessage());
			e.printStackTrace();
		}
	}
 }