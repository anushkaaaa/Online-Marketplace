/**
 * Honor Pledge:
 * I pledge that I have neither given nor received any help on this assignment.
 * ahpatil
 *
 */ 
 // Interface for RMI
public interface Marketplace extends java.rmi.Remote {	
	/*@return true or false on authentication of the username and password
	 * @throws java.rmi.RemoteException
	 */
	public String AuthenticateAdmin(String username, String password) throws java.rmi.RemoteException;
	public String AuthenticateCust(String username, String password) throws java.rmi.RemoteException;
}
