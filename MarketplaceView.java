import java.util.Scanner;
 /**
 * Honor Pledge:
 * I pledge that I have neither given nor received any help on this assignment.
 * ahpatil
 *
 */
 public class MarketplaceView extends AbstractFactory{
	private String username;
	private String password;
 	// It prompts for username and password
 	public void prompt(){
		System.out.println("Welcome to Online MarketPlace!");
 		Scanner sc = new Scanner(System.in);
 		System.out.println("Enter your username:");
 		username = sc.nextLine();
 		System.out.println("Enter your password:");
 		password = sc.nextLine();
	}
	// Methods to return username and assword
 	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	//Administrator View
	@Override
	UserPage getAdminview() {
		return new AdminFactory();
	}
	//Customer View
	@Override
	UserPage getCustomerview() {
		return new CustomerFactory();
	}
 }