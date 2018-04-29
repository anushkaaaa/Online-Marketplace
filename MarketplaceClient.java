import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
 /**
 * Honor Pledge:
 * I pledge that I have neither given nor received any help on this assignment.
 * ahpatil
 *
 */
 public class MarketplaceClient extends UnicastRemoteObject {
	 protected MarketplaceClient() throws RemoteException {
	 }
	 public static void main(String args[]){
		// RMI Security Manager
		System.setSecurityManager(new SecurityManager());
		try{
 			//Address where server is listening
			String name = "//in-csci-rrpc01.cs.iupui.edu:1400/MarketplaceServer";
			// Instance of view
			MarketplaceView view = new MarketplaceView();
			// Instance of FrontController
			MarketplaceFrontController FrontController = new MarketplaceFrontController(name);
			// It will prompt for login or registration option
			view.prompt();
			switch(view.getChoice()){
				case 1: view.login();
						// It will authenticate the username and password
						FrontController.getData(view.getUsername(), view.getPassword());
						break;
				case 2: view.register();
						String userType="";
						if(view.getChoice()==1)
							userType = "admin";
						else if(view.getChoice()==2)
							userType = "customer";
						else {
							view.message("Wrong input, please try again!");
							System.exit(0);
						}
						// It will register the username and password
						FrontController.registerUser(view.getUsername(), view.getPassword(),userType);
						break;
				default:view.message("Wrong input, please try again!");
						break;
			}
		} catch(Exception e){
			System.out.println("MarketplaceClient Exception: " +
			e.getMessage());
			e.printStackTrace();
		}
		System.exit(0);
	}
}