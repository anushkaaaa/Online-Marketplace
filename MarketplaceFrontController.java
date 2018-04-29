import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
 /**
 * Honor Pledge:
 * I pledge that I have neither given nor received any help on this assignment.
 * ahpatil
 *
 */
 public class MarketplaceFrontController extends UnicastRemoteObject {
	private String name;
	public MarketplaceFrontController(String name) throws RemoteException {
		super();
		this.name=name;
	}
	// gets the username and password from client class
	public void getData(String username, String password){
			try{
				// Instance of Dispatcher
				MarketplaceDispatcher Dispatcher = new MarketplaceDispatcher(name);
				// It will authenticate the username and password
				Dispatcher.check(username, password);
			}catch(Exception e){
				System.out.println("MarketplaceFrontController Exception::"+e.getMessage());
				e.printStackTrace();
		}
	}
}