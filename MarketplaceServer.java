import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
// Ryan: Are you using all classes in the util package, if not please only include those you are.
// Fixed: I'm not using any java.util classes
/**
 * Honor Pledge:
 * I pledge that I have neither given nor received any help on this assignment.
 * ahpatil
 *
 */
public class MarketplaceServer extends UnicastRemoteObject{
	private String name;
	public MarketplaceServer(String name) throws RemoteException {
		super(); 
		this.name = name;
	}
	//Fixed: Removed HashMaps
	/**
	 * Implemented remote method from Marketplace interface.
	 */
	public static void main(String args[]) {
		// Set the RMI Security Manager...
		System.setSecurityManager(new SecurityManager());
		try {
			System.out.println("Creating a Marketplace Server!");
			// Location of MarketplaceServer
			String name = "//tesla.cs.iupui.edu:1400/MarketplaceServer";
			//#Fixed: Created an instance of MarketplaceModel
			// Create a new instance of a MarketplaceModel.
			MarketplaceModel model = new MarketplaceModel(name);
			// Create a new instance of a MarketplaceServer.
			MarketplaceServer server = new MarketplaceServer(name);
			System.out.println("MarketplaceModel: binding it to name: " + name);
			// Binds to the RMI Service.
			Naming.rebind(name, model);
			System.out.println("Marketplace Server Ready!");
		} catch (Exception e){
			System.out.println("Exception: " + e.getMessage());
			e.printStackTrace();
		}
	}
}


