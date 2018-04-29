/**
 * Honor Pledge:
 * I pledge that I have neither given nor received any help on this assignment.
 * ahpatil
 *
 */
// To display customized exception
public class AuthorizationException extends RuntimeException {
	//Constructor
	public AuthorizationException(String method)
	{
		super("Invalid Authorization - Access Denied to "+method+" () function");
	}
}