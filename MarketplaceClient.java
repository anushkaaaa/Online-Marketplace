import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
 /**
 * Honor Pledge:
 * I pledge that I have neither given nor received any help on this assignment.
 * ahpatil
 *
 */
 public class MarketplaceClient extends UnicastRemoteObject {
	public MarketplaceClient() throws RemoteException {
		super();
	}
	public static void main(String args[]){
		// RMI Security Manager
		System.setSecurityManager(new SecurityManager());
		try{
 		//Address where server is listening
			String name = "//in-csci-rrpc01.cs.iupui.edu:1400/MarketplaceServer";
			// Instance of view
			MarketplaceView view = new MarketplaceView();
			// It will prompt for username
			view.prompt();
			// Instance of FrontController
			MarketplaceFrontController FrontController = new MarketplaceFrontController(name);
			// It will authenticate the username and password
			FrontController.getData(view.getUsername(), view.getPassword());
		} catch(Exception e){
			System.out.println("MarketplaceClient Exception: " +
			e.getMessage());
			e.printStackTrace();
		}
		System.exit(0);
	}
}