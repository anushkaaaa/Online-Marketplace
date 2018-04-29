import java.io.Serializable;
 /**
 * Honor Pledge:
 * I pledge that I have neither given nor received any help on this assignment.
 * ahpatil
 *
 */
 // The session class
public class Session implements Serializable{
	private static final long serialVersionUID = 94L;
	private UserType type;
	//Constructor, sets the usertype
	public Session(String role, String username) {
		this.type=new UserType(role,username);
	}
	//returns the user type
	public UserType getDetails() {
		return type;
	}
}