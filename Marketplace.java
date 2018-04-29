import java.rmi.RemoteException;
import java.util.List;

/**
 * Honor Pledge:
 * I pledge that I have neither given nor received any help on this assignment.
 * ahpatil
 *
 */ 
 // Interface for RMI
public interface Marketplace extends java.rmi.Remote {	
	// @return string on authentication of the username and password
	String AuthenticateUser(String username, String password) throws RemoteException;
	// @return string on registration
	String RegisterUser(String username, String password, String userType) throws RemoteException;
	// Creates session
	Session create(String role,String username) throws RemoteException;
	// To check if id exist
	boolean checkId(int id) throws RemoteException;
	// menu options and roles
	List<String> browse(Session session) throws RemoteException;
	@RequiresRole("admin")
	String add(Session session, String[] item) throws RemoteException;
	@RequiresRole("admin")
	String update(Session session, String[] item, int id) throws RemoteException;
	@RequiresRole("admin")
	String remove(Session session, int id) throws RemoteException;
	@RequiresRole("customer")
	String purchase(Session session, int item) throws RemoteException;
	@RequiresRole("customer")
	List<String> displayCart(Session session) throws RemoteException;
	@RequiresRole("customer")
	String addToCart(Session session, int id) throws RemoteException;
	@RequiresRole("customer")
	String purchaseItems(Session session) throws RemoteException;
}
