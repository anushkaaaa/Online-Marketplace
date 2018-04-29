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
	//constructor, sets the role
	public UserType(String role)
	{
		this.role = role;
	}
	// returns the role
	public String getRole(){
		return role;
	}
}