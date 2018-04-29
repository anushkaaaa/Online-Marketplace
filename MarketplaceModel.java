import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
/**
 * Honor Pledge:
 * I pledge that I have neither given nor received any help on this assignment.
 * ahpatil
 *
 */
public class MarketplaceModel extends UnicastRemoteObject implements Marketplace{
 	/*It will authenticate the username and password
	 * @return true on successful authentication else false 
	 */
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
 }
