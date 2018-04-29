import java.io.Serializable;
 /**
 * Honor Pledge:
 * I pledge that I have neither given nor received any help on this assignment.
 * ahpatil
 *
 */
public class UserType implements Serializable{
	private static final long serialVersionUID = 200496L;
	private String role;
	private String username;
	//constructor, sets the role and username
	public UserType(String role, String username) {
		this.role = role;
		this.username = username;
	}
	// returns the role
	public String getRole(){
		return role;
	}
	 // returns the username
	 public String getUsername(){
		 return username;
	 }
}