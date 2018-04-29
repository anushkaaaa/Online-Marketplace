import java.util.Scanner;
/**
 * Honor Pledge:
 * I pledge that I have neither given nor received any help on this assignment.
 * ahpatil
 *
 */
 // It implements UserPage
public class AdminFactory implements UserPage{
	// It displays the details for Administrator Page
	@Override
	public void display(){
		System.out.println("Hello, Welcome to Administrator Page!");
		System.out.println("---------------------------------------");
		System.out.println("Menu");
		System.out.println("a) Browse Items");
		System.out.println("b) Update Items");
		System.out.println("c) Remove Items");
		System.out.println("d) Add Items");
	}
	//It displays message
	@Override
	public void message(String msg){
		System.out.println(msg);
	}
}
