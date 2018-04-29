import java.util.List;

/**
 * Honor Pledge:
 * I pledge that I have neither given nor received any help on this assignment.
 * ahpatil
 *
 */
// Invoker class
public class Invoker{
	UserPage displayView;
	//Constructor
	public Invoker(AbstractFactory a, String usertype) {
		// It will obtain the view based on the usertype
		if(usertype.equals("admin"))
			displayView=a.getAdminview();
		else
			displayView=a.getCustomerview();
	}
	//For controlling the displayAddItem method
	public String[] displayAddedItem(){return displayView.displayAddItem();}
	//For controlling the displayUpdateItem method
	public String[] displayUpdateItem() {
		return displayView.displayUpdateItem();
	}
	//For controlling the displaySelectedItem method
	public int displayItem(String[] item) {
		return displayView.displaySelectedItem(item);
	}
	//For controlling the display method
	public int displayContent(List<String> items) {
		return displayView.display(items);
	}
	//For controlling the displayCart method
	public int displayCartContent(List<String> items) {
		return displayView.displayCart(items);
	}
	//For controlling the message method
	public void displayMessage(String msg) {
		displayView.message(msg);
	}

}