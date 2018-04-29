import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
/**
 * Honor Pledge:
 * I pledge that I have neither given nor received any help on this assignment.
 * ahpatil
 *
 */
// This is the model class for all the actions to performed by the marketplace
public class MarketplaceModel extends UnicastRemoteObject implements Marketplace{
    DatabaseMapper databaseMapper = new DatabaseMapper();
	private String name="";
	public MarketplaceModel(String name) throws RemoteException {
		super(); 
		this.name = name;
	}
	// For registering user
	@Override
    public String RegisterUser(String username, String password, String userType){
	    return databaseMapper.RegisterUser(username,password,userType);
    }
	// For Authentication
	@Override
 	public String AuthenticateUser(String username, String password){
       return databaseMapper.AuthenticateUser(username,password);
 	}
	// To create session
	@Override
	public Session create(String role,String username){
        return new Session(role,username);
 	}
	//To browse the items
	@Override
	public List<String> browse(Session session) throws RemoteException{
        return databaseMapper.browse();
    }
    //To check id exist
    @Override
    public boolean checkId(int id) throws RemoteException{
        return databaseMapper.checkId(id);
    }
	//To purchase the items
	@Override
	public synchronized String purchase(Session session, int id) throws RemoteException{
            return databaseMapper.purchase(id);
	}
    //To update the item
    @Override
    public synchronized String update(Session session, String[] item, int id) throws RemoteException{
        return databaseMapper.update(item, id);
    }
	//To add the items
	@Override
	public synchronized String add(Session session, String[] item) throws RemoteException{
		return databaseMapper.add(item);
	}
    //To remove the item
    @Override
    public synchronized String remove(Session session,int id) throws RemoteException{
        return databaseMapper.remove(id);
    }
    //To add the item to cart
    @Override
    public String addToCart(Session session, int id) throws RemoteException{
        return databaseMapper.addToCart(session,id);
    }
    //To purchase all items from the cart
    @Override
    public synchronized String purchaseItems(Session session) throws RemoteException{
        return databaseMapper.purchaseItems(session) ;
    }
    //To display the items in cart
    @Override
    public List<String> displayCart(Session session) throws RemoteException{
        return databaseMapper.displayCart(session);
    }
}