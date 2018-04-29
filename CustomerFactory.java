import java.util.Scanner;
/**
 * Honor Pledge:
 * I pledge that I have neither given nor received any help on this assignment.
 * ahpatil
 *
 */
  // It implements UserPage
public class CustomerFactory implements UserPage{
	private int choice;
	// It displays the details for customer Page and returns choice
	@Override
	public int display(){
		Scanner sc = new Scanner(System.in);
		System.out.println("Hello, Welcome to Customer Page!");
		System.out.println("---------------------------------------");
		System.out.println("Menu");
		System.out.println("1) Browse Items");
		System.out.println("2) Update Items");
		System.out.println("3) Remove Items");
		System.out.println("4) Add Items");
		System.out.println("5) Purchase Items");
		System.out.println("Select any one of the above menu:");
		choice = sc.nextInt();
		return choice;
	}
	//It displays message
	@Override
	public void message(String msg){
		System.out.println(msg);
	}
}
