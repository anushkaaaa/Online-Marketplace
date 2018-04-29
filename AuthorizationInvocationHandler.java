import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
/**
 * Honor Pledge:
 * I pledge that I have neither given nor received any help on this assignment.
 * ahpatil
 *
 */
// This is the Authorization Invocation Handler class
public class AuthorizationInvocationHandler implements InvocationHandler, Serializable {
	private static final long serialVersionUID = -17717L;
	private Object objectImpl;
public AuthorizationInvocationHandler(Object impl) {
	this.objectImpl = impl;
	}
@Override
public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
	if (method.isAnnotationPresent(RequiresRole.class)) {
		RequiresRole test = method.getAnnotation(RequiresRole.class);
		Session session = (Session) args[0];
		UserType user = session.getDetails();
		if (user.getRole().equals(test.value())) {
			return method.invoke(objectImpl, args);
		} else {
			throw new AuthorizationException(method.getName());
		}
		} else {
			return method.invoke(objectImpl, args);
		}   
	}
}