import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.lang.reflect.Proxy;
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
			// Proxy instance
			Marketplace model = (Marketplace)Proxy.newProxyInstance(Marketplace.class.getClassLoader(),new Class<?>[]{Marketplace.class}, new AuthorizationInvocationHandler(new MarketplaceModel(name)));
			// Binds to the RMI Service.
			Naming.rebind(name, model);
			System.out.println("Marketplace Server Ready!");
		} catch (Exception e){
			System.out.println("Exception: " + e.getMessage());
			e.printStackTrace();
		}
	}
}


