import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
 /**
 * Honor Pledge:
 * I pledge that I have neither given nor received any help on this assignment.
 * ahpatil
 *
 */
 // This is the item controller class
 public class ItemController extends UnicastRemoteObject {
	private String name;
	private Session session;
	private Marketplace myItem;
	public ItemController(String name,Session session) throws RemoteException {
		try{
			this.name=name;
			this.session=session;
			myItem = (Marketplace) Naming.lookup(name);
		} catch(Exception e){
			System.out.println("ItemController Exception: " +e.getMessage());
			e.printStackTrace();
		}
	}
	// It takes two parameters choice and invoker. Invoker invokes to display the message obtained from the choice.
	public void getChoice(int choice, Invoker invoker){
		try{
		String message = "";
		switch (choice){
			case 1: try{
						message = myItem.browse(session);
					}catch (Exception e){
						System.out.println("Item Exception:: " +e.getMessage());
					}
					break;
			case 2: try{
						message = myItem.update(session);
					}catch (Exception e){
						System.out.println("Item Exception: " +e.getMessage());
					}
					break;
			case 3: try{
						message = myItem.remove(session);
					}catch (Exception e){
						System.out.println("Item Exception: " +e.getMessage());
					}
					break;
			case 4: try{
						message = myItem.add(session);
					}catch (Exception e){
						System.out.println("Item Exception: " +e.getMessage());
					}
					break;
			case 5: try{
						message = myItem.purchase(session);
					}catch (Exception e){
						System.out.println("Item Exception: " +e.getMessage());
					}
					break;	
			default: message = "Wrong choice, Please try again!";
		}
		//To display the message
		invoker.displayMessage(message);
		} catch(Exception e){
			System.out.println("ItemController Exception:: " +e.getMessage());
			e.printStackTrace();
		}
		System.exit(0);
	}
}