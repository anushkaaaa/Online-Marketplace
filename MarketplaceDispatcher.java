import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

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
			// get the userType based on the authentication
			String role = myMarketplace.AuthenticateUser(username,password);
			if(role.equalsIgnoreCase("admin")){
				//Creating session for admin
				session = myMarketplace.create("admin",username);
				// Create a new instance of a AdminInvoker
				Invoker invoker = new Invoker(view,"admin");
				// get the product list
				List<String> itemList;
				itemList = myMarketplace.browse(session);
				// to display items for browsing
				int choice = invoker.displayContent(itemList);
				// Create a new instance of a ItemController
				ItemController iController = new ItemController(name,session);
				// Sends choice and invoker to perform action
				iController.getChoice(choice,invoker);
			}
			else if(role.equalsIgnoreCase("customer")){
				//Creating session for customer
				session = myMarketplace.create("customer",username);
				// Create a new instance of a CustomerInvoker
				Invoker invoker = new Invoker(view,"customer");
				// get the product list
				List<String> itemList;
				itemList = myMarketplace.browse(session);
				// to display items for browsing
				int choice = invoker.displayContent(itemList);
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
	//It will take the inputs from view and will register the user
    public void register(String username, String password, String userType) throws RemoteException {
		String s = myMarketplace.RegisterUser(username,password,userType);
		UserPage myView = view.getCustomerview();
		if(s.equalsIgnoreCase("successful")){
			session = myMarketplace.create(userType,username);
			// Create a new instance of a CustomerInvoker
			Invoker invoker = new Invoker(view,userType);
			invoker.displayMessage("Registration Successful");
			// get the product list
			List<String> itemList;
			itemList = myMarketplace.browse(session);
			// to display items for browsing
			int choice = invoker.displayContent(itemList);
			// Create a new instance of a ItemController
			ItemController iController = new ItemController(name,session);
			// Sends choice and invoker to perform action
			iController.getChoice(choice,invoker);
		}else if(s.equalsIgnoreCase("error")) {
			// when user already exist
			myView.message("User already exist, Please try again!");
		}else
			myView.message("Registration unsuccessful, Please try again!");
    }
}