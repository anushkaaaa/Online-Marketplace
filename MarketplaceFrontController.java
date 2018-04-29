import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;
 /**
 * Honor Pledge:
 * I pledge that I have neither given nor received any help on this assignment.
 * ahpatil
 *
 */
 public class MarketplaceFrontController extends UnicastRemoteObject {
	String name;
	public MarketplaceFrontController(String name) throws RemoteException {
		super();
		this.name=name;
	}
	public static void main(String args[]) throws java.rmi.RemoteException{
		// RMI Security Manager
		System.setSecurityManager(new SecurityManager());
		try{
 		//Address where server is listening
			String name = "//tesla.cs.iupui.edu:1400/MarketplaceServer";
			// Instance of view
			MarketplaceView view = new MarketplaceView();
			// It will prompt for username
			view.prompt();
			// Instance of Dispatcher
			MarketplaceDispatcher Dispatcher = new MarketplaceDispatcher(name);
			// It will authenticate the username and password
			Dispatcher.check(view.getUsername(), view.getPassword());
		} catch(Exception e){
			System.out.println("MarketplaceClient Exception: " +
			e.getMessage());
			e.printStackTrace();
		}
		System.exit(0);
	}
}
