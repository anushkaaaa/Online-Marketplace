import java.util.Scanner;
/**
 * Honor Pledge:
 * I pledge that I have neither given nor received any help on this assignment.
 * ahpatil
 *
 */
  // It implements UserPage
public class CustomerFactory implements UserPage{
	// It displays the details for customer Page
	@Override
	public void display(){
		System.out.println("Hello, Welcome to Customer Page!");
		System.out.println("---------------------------------------");
		System.out.println("Menu");
		System.out.println("a) Browse Items");
		System.out.println("b) Purchase Items");
	}
	//It displays message
	@Override
	public void message(String msg){
		System.out.println(msg);
	}
}