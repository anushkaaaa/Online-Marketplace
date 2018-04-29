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
	private int choice;
	// It prompts for login and registration
 	public void prompt(){
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to Online MarketPlace!");
		System.out.println("-------------------------------");
		System.out.println("Select any one option:");
		System.out.println("1. Login");
		System.out.println("2. Register");
		choice = sc.nextInt();
	}
	// It prompts for username and password
	public void login(){
		Scanner sc = new Scanner(System.in);
		System.out.println("Login:");
		System.out.println("Enter your username:");
		username = sc.nextLine();
		System.out.println("Enter your password:");
		password = sc.nextLine();
	}
	 // It prompts for new username and password
	 public void register(){
		 Scanner sc = new Scanner(System.in);
		 System.out.println("Register:");
		 System.out.println("Enter username:");
		 username = sc.nextLine();
		 System.out.println("Enter password:");
		 password = sc.nextLine();
		 System.out.println("Select the user type:");
		 System.out.println("1. admin");
		 System.out.println("2. customer");
		 choice = sc.nextInt();
	 }
	 // To display message
	 public void message(String msg){
 		System.out.println(msg);
	 }
	 // Methods to return username and password
	 public String getUsername() {
 		return username;
 	}
 	public String getPassword() {
 		return password;
 	}
 	public int getChoice(){
 		return choice;
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