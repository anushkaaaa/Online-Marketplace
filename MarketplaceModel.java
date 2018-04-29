import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Honor Pledge:
 * I pledge that I have neither given nor received any help on this assignment.
 * ahpatil
 *
 */
//Fixed: Not using file anymore
// This is the model class for all the actions to perfomed by the marketplace
public class MarketplaceModel extends UnicastRemoteObject implements Marketplace{
    private DatabaseConnection databaseConnection;
    private ResultSet resultSet;
	private String name="";
	public MarketplaceModel(String name) throws RemoteException {
		super(); 
		this.name = name;
		databaseConnection = new DatabaseConnection();
		databaseConnection.connectDb();
	}
	@Override
    public String RegisterUser(String username, String password, String userType){
	    boolean success = false;
	    try{
            resultSet= databaseConnection.getUserInfo();
            //To check whether user already exist
            while(resultSet.next()){
                if(resultSet.getString("username").equalsIgnoreCase(username))
                    return "error";
            }
	        success = databaseConnection.registerUser(username,password,userType);
	        if(success)
	            return "successful";
        }catch (SQLException e) {
            System.out.println("Registration:: error");
        }
        return "unsuccessful";
    }
	// For Authentication
	@Override
 	public String AuthenticateUser(String username, String password){
        try {
            resultSet= databaseConnection.getUserInfo();
            while (resultSet.next()) {
                // if username and password are verified it returns the userType
                if(resultSet.getString("username").equalsIgnoreCase(username)&& resultSet.getString("password").equalsIgnoreCase(password))
                    return resultSet.getString("userType");
            }
        } catch (SQLException e) {
            System.out.println("User Authentication:: Result set error");
        }
		return "";
 	}
	// To create session
	@Override
	public Session create(String role,String username){
        return new Session(role,username);
 	}
	//To browse the items
	@Override
	public List<String> browse(Session session) throws RemoteException{
		List<String> items = new ArrayList<>();
		String content;
		int i = 0;
			try {
                resultSet = databaseConnection.browse();
                while (resultSet.next()) {
                    content = resultSet.getString("itemID")+","+ resultSet.getString("itemName")+","+ resultSet.getString("itemDescription")+","+ resultSet.getString("itemPrice")+","+ resultSet.getString("itemQuantity");
                    items.add(i,content);
                    i++;
                }
                return items;
            } catch (SQLException e) {
                System.out.println("Browse:: Result set error");
            }
        return null;
    }
	//To purchase the items
	@Override
	public synchronized String purchase(Session session, int id) throws RemoteException{
		ResultSet resultSet;
		int quantity=0;
		// Synchronized block
		synchronized (this){
            try {
                resultSet = databaseConnection.getItem(id);
		        while(resultSet.next()){
		            quantity = Integer.parseInt(resultSet.getString("itemQuantity"));
                }
		        resultSet.beforeFirst();
		        if(quantity>0) {
		            quantity--;
		            databaseConnection.updateQuantity(quantity, id);
		            return "Item Purchased!";
                }
            } catch (SQLException e) {
                System.out.println("Purchase:: Result set error");
            }
        }
        return "Item out of stock!";
	}
    //To update the item
    @Override
    public synchronized String update(Session session, String[] item, int id) throws RemoteException{
        // Synchronized block
        synchronized (this){
            try {
				if(databaseConnection.UpdateItem(item,id))
					return "Item updated!";
            } catch (SQLException e) {
                System.out.println("Purchase:: Result set error");
            }
        }
        return "Error updating item, please try again!";
    }
	//To add the items
	@Override
	public synchronized String add(Session session, String[] item) throws RemoteException{
		if(item!=null) {
            try {
                if(databaseConnection.add(item))
					return "Item added!";
            } catch (SQLException e) {
                System.out.println("Adding item:: database error");
            }
        }
		return "Error adding item, please try again!";
	}
    //To update the item
    @Override
    public synchronized String remove(Session session,int id) throws RemoteException{
        // Synchronized block
        synchronized (this){
            try {
                if(databaseConnection.RemoveItem(id))
					return "Item removed!";
            } catch (SQLException e) {
                System.out.println("Purchase:: Result set error");
            }
        }
        return "Error removing item, please try again!";
    }
    //To add the item to cart
    @Override
    public String addToCart(Session session, int id) throws RemoteException{
        try {
            // passing item id and username
            databaseConnection.addToCart(id,session.getDetails().getUsername());
            return "Item added to cart!";
        } catch (SQLException e) {
            System.out.println("Adding to Cart:: Result set error");
        }
        return "Error adding item, please try again!";
    }
    //To purchase all items from the cart
    @Override
    public synchronized String purchaseItems(Session session) throws RemoteException{
        ResultSet resultSet;
        HashMap<Integer,Integer> hmap = new HashMap<>();
        String msg = "";
        int quantity = 0;
        // Synchronized block
        synchronized (this){
            try {
                resultSet = databaseConnection.displayCart(session.getDetails().getUsername());
                while (resultSet.next()) {
                    hmap.put(Integer.parseInt(resultSet.getString("itemId")),Integer.parseInt(resultSet.getString("itemQuantity")));
                }
                for (Map.Entry<Integer, Integer> m : hmap.entrySet()) {
                    if(m.getValue()>0){
                        quantity = m.getValue() -1;
                        databaseConnection.updateCart(quantity,m.getKey(),session.getDetails().getUsername());
                        msg += "Item id: "+m.getKey()+" Purchased! \n";
                    }else
                        msg += "Item id: "+m.getKey()+" is out of stock \n";
                }
            } catch (SQLException e) {
                System.out.println("Purchase Items:: Result set error");
            }
        }
        return msg ;
    }
    //To display the items in cart
    @Override
    public List<String> displayCart(Session session) throws RemoteException{
        List<String> items = new ArrayList<>();
        String content;
        int i = 0;
        try {
            // passing username
            resultSet = databaseConnection.displayCart(session.getDetails().getUsername());
            while (resultSet.next()) {
                content = resultSet.getString("itemID")+","+ resultSet.getString("itemName")+","+ resultSet.getString("itemDescription")+","+ resultSet.getString("itemPrice")+","+ resultSet.getString("itemQuantity");
                items.add(i,content);
                i++;
            }
            return items;
        } catch (SQLException e) {
            System.out.println("Display Cart:: Result set error");
        }
        return null;
    }
}