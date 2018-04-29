import java.util.List;

/**
 * Honor Pledge:
 * I pledge that I have neither given nor received any help on this assignment.
 * ahpatil
 *
 */
// Interface between Admin and Customer Pages
public interface UserPage {
	// To display the items for browsing
	int display(List<String> item);
	// To get the item to be added from user
	String[] displayAddItem();
	//To display selected item
	int displaySelectedItem(String[] item);
	// To display message
	void message(String msg);
}