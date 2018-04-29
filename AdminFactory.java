import java.util.List;
import java.util.Scanner;
/**
 * Honor Pledge:
 * I pledge that I have neither given nor received any help on this assignment.
 * ahpatil
 *
 */
 // It implements UserPage
public class AdminFactory implements UserPage{
	private int choice;
	// It displays the details for Administrator Page and returns choice
	@Override
	public int display(List<String> items){
		Scanner sc = new Scanner(System.in);
		System.out.println("Hello, Welcome to Administrator Page!");
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
		System.out.println("---------------------------------------");
		System.out.println("Menu:");
		System.out.println("1) Purchase Item");
		System.out.println("2) Update Item");
		System.out.println("3) Remove Item");
		System.out.println("4) Add Item to Cart");
		System.out.println("5) Add new Item to Inventory");
		System.out.println("6) View cart");
		System.out.println("7) Exit");
		System.out.println("Select any one of the above menu:");
		choice = sc.nextInt();
		return choice;
	}
	// It displays the cart
	@Override
	public int displayCart(List<String> items){
		if(!items.isEmpty()){
			Scanner sc = new Scanner(System.in);
			System.out.println("---------------------------------------");
			System.out.println("Items in cart:");
			System.out.println("ID\t\t\tName\t\t\t\tPrice\t\t\tQuantity");
			for (int i = 0; i < items.size(); i++) {
				// To display properties of the items
				String[] item = items.get(i).split(",");
				for (int j = 0; j < item.length; j++) {
					// j=2 is item description, I don't wish to show it in the browse option
					// The select item menu will display the item description
					if (j != 2)
						System.out.print(item[j] + "\t\t\t");
				}
				System.out.println();
			}
			System.out.println("---------------------------------------");
			System.out.println("Menu:");
			System.out.println("1) Purchase Items");
			System.out.println("2) Browse Items");
			System.out.println("3) Exit");
			System.out.println("Select any one of the above menu:");
			choice = sc.nextInt();
			return choice;
		}else
			System.out.println("Your Cart is empty! \n");
		return 2;
	}
	//It displays the user to input the item to be added
	@Override
	public String[] displayAddItem(){
		String[] item = new String[5];
		Scanner sc = new Scanner(System.in);
		System.out.println("---------------------------------------");
		System.out.println("id: ");
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
	//It displays the user to input the item to be updated
	@Override
	public String[] displayUpdateItem(){
		String[] item = new String[5];
		Scanner sc = new Scanner(System.in);
		System.out.println("---------------------------------------");
		System.out.println("Enter the updated name: ");
		item[1] = sc.nextLine();
		System.out.println("Enter the updated description: ");
		item[2] = sc.nextLine();
		System.out.println("Enter the updated price: ");
		item[3] = sc.nextLine();
		System.out.println("Enter the updated quantity: ");
		item[4] = sc.nextLine();
		return item;
	}
	//It displays message
	@Override
	public void message(String msg){
		System.out.println(msg);
	}
}
