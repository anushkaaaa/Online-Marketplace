import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
 /**
 * Honor Pledge:
 * I pledge that I have neither given nor received any help on this assignment.
 * ahpatil
 *
 */
public class MarketplaceDispatcher extends UnicastRemoteObject  {
	String name;
	Marketplace myMarketplace;
	AbstractFactory view;
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
				// Create a new instance of a AdminInvoker
				Invoker invoker = new Invoker(view,"admin");
				invoker.displayContent();
			}
			else if(myMarketplace.AuthenticateCust(username,password).equals("customer")){
				// Create a new instance of a CustomerInvoker
				Invoker invoker = new Invoker(view,"customer");
				invoker.displayContent();
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