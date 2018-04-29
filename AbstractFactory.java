/**
 * Honor Pledge:
 * I pledge that I have neither given nor received any help on this assignment.
 * ahpatil
 *
 */
 // Class for Abstract Factory Pattern
public abstract class AbstractFactory{
	// To display view for both the user types
	abstract UserPage getAdminview();
	abstract UserPage getCustomerview();
}