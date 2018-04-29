import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Scanner;

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
			Scanner sc = new Scanner(System.in);
			String message ="";
		switch (choice){
			case 1: try{
						invoker.displayMessage("Enter the item id: ");
						int itemId = sc.nextInt();
						// Details about the selected item
						List<String> itemList = myItem.browse(session);
						String[] item = itemList.get(itemId-1).split(",");
						int c = invoker.displayItem(item);
						doAction(c,invoker,itemId);
					}catch (Exception e){
						System.out.println("Item Exception:: " +e.getMessage());
					}
					break;
			case 2: try{
						String[] item = invoker.displayAddedItem();
						message = myItem.add(session, item);
					}catch (Exception e){
						System.out.println("Item Exception: " +e.getMessage());
					}
					break;
			case 3: message="Thank you for visiting us!";
					break;
			default: message= "Wrong choice, Please try again!";
		}
			invoker.displayMessage(message);
		} catch(Exception e){
			System.out.println("ItemController Exception:: " +e.getMessage());
			e.printStackTrace();
		}
		System.exit(0);
	}
	// It takes three parameters choice, invoker and itemID. Invoker invokes to display the message obtained from the choice.
	public void doAction(int c, Invoker invoker, int itemID){
		try{
			String message ="";
			switch (c){
				case 1: try{
						message = myItem.purchase(session,itemID);
						}catch (Exception e){
							System.out.println("Item Exception:: " +e.getMessage());
						}
						break;
				case 2: try{
						List<String> itemList;
						itemList = myItem.browse(session);
						// to display items for browsing
						int choice = invoker.displayContent(itemList);
						getChoice(choice,invoker);
						}catch (Exception e){
							System.out.println("Item Exception: " +e.getMessage());
						}
						break;
				case 3: message="Thank you for visiting us!";
					break;
				default: message= "Wrong choice, Please try again!";
			}
			invoker.displayMessage(message);
		} catch(Exception e){
			System.out.println("ItemController Exception:: " +e.getMessage());
			e.printStackTrace();
		}
		System.exit(0);
	}
}