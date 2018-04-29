import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
/**
 * Honor Pledge:
 * I pledge that I have neither given nor received any help on this assignment.
 * ahpatil
 *
 */
// This is the model class for all the actions to perfomed by the marketplace
public class MarketplaceModel extends UnicastRemoteObject implements Marketplace{
	public String name="";
	public MarketplaceModel(String name) throws RemoteException {
		super(); 
		this.name = name;
	}
	// For Admin Authentication
	@Override
 	public String AuthenticateAdmin(String username, String password){
		if(username.equalsIgnoreCase("admin")&&password.equalsIgnoreCase("admin"))
			return "admin";
		return "";
 	}
	// For Customer Authentication
	@Override
	public String AuthenticateCust(String username, String password){
		if(username.equalsIgnoreCase("customer")&&password.equalsIgnoreCase("customer"))
			return "customer";
		return "";
 	}
	// To create session
	@Override
	public Session create(String role){
		Session session = new Session(role);
		return session;
 	}
	//To browse the items
	@Override
	public String browse(Session session){
		return "Welcome, you can now browse items!";
	}
	//To update the items
	@Override
	public String update(Session session){
		return "Welcome, you can now update items!";
	}
	//To remove the items
	@Override
	public String remove(Session session){
		return "Welcome, you can now remove items!";
	}
	//To add the items
	@Override
	public String add(Session session){
		return "Welcome, you can now add items!";
	}
	//To purchase the items
	@Override
	public String purchase(Session session){
		return "Welcome, you can now purchase items.";
	}
}