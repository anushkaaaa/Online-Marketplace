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
			int itemID;
			switch (choice){
				case 1: try{
							invoker.displayMessage("Enter the itemID");
							itemID = sc.nextInt();
							//check if id already present
							if(myItem.checkId(itemID))
								message = myItem.purchase(session,itemID);
							else
								message = "Item id doesn't exist";
						}catch (Exception e){
							System.out.println("Item Exception:: " +e.getMessage());
						}
						break;
				case 2: try{
							invoker.displayMessage("Enter the itemID");
							itemID = sc.nextInt();
							//check if id already present
							if(myItem.checkId(itemID)) {
								String[] item = invoker.displayUpdateItem();
								message = myItem.update(session, item, itemID);
							}else
								message = "Item id doesn't exist";
						}catch (Exception e){
							System.out.println("Item Exception:: " +e.getMessage());
						}
						break;
				case 3: try{
							invoker.displayMessage("Enter the itemID");
							itemID = sc.nextInt();
							//check if id already present
							if(myItem.checkId(itemID))
								message = myItem.remove(session,itemID);
							else
								message = "Item id doesn't exist";
						}catch (Exception e){
							System.out.println("Item Exception:: " +e.getMessage());
						}
						break;
				case 4: try{
							invoker.displayMessage("Enter the itemID");
							itemID = sc.nextInt();
							//check if id already present
							if(myItem.checkId(itemID))
								message = myItem.addToCart(session,itemID);
							else
								message = "Item id doesn't exist";
						}catch (Exception e){
							System.out.println("Item Exception:: " +e.getMessage());
						}
						break;
				case 5: try{
							String[] item = invoker.displayAddedItem();
							//check if id already present
							if(!myItem.checkId(Integer.parseInt(item[0])))
								message = myItem.add(session, item);
							else
								message = "Item id already exist";
						}catch (Exception e){
							System.out.println("Item Exception: " +e.getMessage());
						}
						break;
				case 6: try{
							List<String> itemList;
							itemList = myItem.displayCart(session);
							// to display items in cart
							int c = invoker.displayCartContent(itemList);
							getCartChoice(c,invoker);
						}catch (Exception e){
							System.out.println("Item Exception: " +e.getMessage());
						}
						break;
				case 7: invoker.displayMessage("Thank you for visiting us!");
						System.exit(0);
						break;
				default: message= "Wrong choice, Please try again!";
			}
			invoker.displayMessage(message+"\n");
			//browsing option
			getCartChoice(2,invoker);
		} catch(Exception e){
			System.out.println("ItemController Exception:: " +e.getMessage());
			e.printStackTrace();
		}
		System.exit(0);
	}
	// It takes two parameters choice and invoker. Invoker invokes to display the message obtained from the choice.
	private void getCartChoice(int choice, Invoker invoker){
		try{
			String message ="";
			switch (choice){
				case 1: try{
							message = myItem.purchaseItems(session);
							invoker.displayMessage(message);
							getCartChoice(3,invoker);
						}catch (Exception e){
							System.out.println("Item Exception: " +e.getMessage());
						}
				case 2: try{
							List<String> itemList;
							itemList = myItem.browse(session);
							// to display items
							int c = invoker.displayContent(itemList);
							getChoice(c,invoker);
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
