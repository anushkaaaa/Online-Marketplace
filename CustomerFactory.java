import java.util.List;
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
	public int display(List<String> items){
		Scanner sc = new Scanner(System.in);
		System.out.println("Hello, Welcome to Customer Page!");
		System.out.println("---------------------------------------");
		System.out.println("Browse Items");
		System.out.println("ID\t\t\tName\t\t\t\tPrice\t\t\tQuantity");
		for(int i=0; i<items.size();i++){
			// To display properties of the items
			String[] item = items.get(i).split(",");
			for(int j=0; j<item.length ; j++){
				// j=2 is item description, I don't wish to show it in the browse option
				// The select item menu will display the item description
				if(j!=2)
					System.out.print(item[j]+"\t\t\t");
			}
			System.out.println();
		}
		System.out.println("Menu:");
		System.out.println("1) Select Item");
		System.out.println("2) Add Item");
		System.out.println("3) Exit");
		System.out.println("Select any one of the above menu:");
		choice = sc.nextInt();
		return choice;
	}
	// It displays the selected item
	@Override
	public int displaySelectedItem(String[] item){
		Scanner sc = new Scanner(System.in);
		System.out.println("---------------------------------------");
		System.out.println("Id: "+item[0]);
		System.out.println("Name: "+item[1]);
		System.out.println("Description: "+item[2]);
		System.out.println("Price: "+item[3]+"$");
		System.out.println("Quantity: "+item[4]);
		System.out.println("---------------------------------------");
		System.out.println("Menu:");
		System.out.println("1) Purchase Item");
		System.out.println("2) Browse Item");
		System.out.println("3) Exit");
		System.out.println("Select any one of the above menu:");
		choice = sc.nextInt();
		return choice;
	}
	//It displays the user to input the item to be added
	@Override
	public String[] displayAddItem(){
		String[] item = new String[5];
		Scanner sc = new Scanner(System.in);
		System.out.println("---------------------------------------");
		System.out.println("Id:");
		item[0] = sc.nextLine();
		System.out.println("Name: ");
		item[1] = sc.nextLine();
		System.out.println("Description: ");
		item[2] = sc.nextLine();
		System.out.println("Price: ");
		item[3] = sc.nextLine();
		System.out.println("Quantity: ");
		item[4] = sc.nextLine();
		return item;
	}
	//It displays message
	@Override
	public void message(String msg){
		System.out.println(msg);
	}
}