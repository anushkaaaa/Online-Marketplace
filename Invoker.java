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
	//For controlling the display method
	public int displayContent() {
		return displayView.display();
	}
	//For controlling the message method
	public void displayMessage(String msg) {
		displayView.message(msg);
	}
}