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
	String AuthenticateAdmin(String username, String password) throws java.rmi.RemoteException;
	String AuthenticateCust(String username, String password) throws java.rmi.RemoteException;
	// Creates session
	Session create(String role) throws java.rmi.RemoteException;
	// menu options and roles
	List<String> browse(Session session) throws java.rmi.RemoteException;
	@RequiresRole("admin")
	String add(Session session, String[] item) throws java.rmi.RemoteException;
	@RequiresRole("customer")
	String purchase(Session session, int item) throws java.rmi.RemoteException;
}
