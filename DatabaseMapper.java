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
public class DatabaseMapper {
    private DatabaseConnection databaseConnection;
    private ResultSet resultSet;
    public DatabaseMapper(){
        super();
        databaseConnection = new DatabaseConnection();
        databaseConnection.connectDb();
    }
    // Register new user
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
    // Authenticate the user
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
    // To check if exist
    public boolean checkId(int id){
        resultSet= databaseConnection.checkId(id);
        try {
            // if result set is empty
            if(resultSet.next()==false)
                return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    //To browse the items
    public List<String> browse(){
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
    public synchronized String purchase(int id){
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
    public synchronized String update(String[] item, int id){
        // Synchronized block
        synchronized (this){
            try {
                if(databaseConnection.UpdateItem(item,id));
                    return "Item updated!";
            } catch (SQLException e) {
                System.out.println("Purchase:: Result set error");
            }
        }
        return "Error updating item, please try again!";
    }
    //To add the items
    public synchronized String add(String[] item){
        if(item!=null) {
            try {
                if(databaseConnection.add(item));
                    return "Item added!";
            } catch (SQLException e) {
                System.out.println("Adding item:: database error");
            }
        }
        return "Error adding item, please try again!";
    }
    //To remove the item
    public synchronized String remove(int id){
        // Synchronized block
        synchronized (this){
            try {
                databaseConnection.RemoveItem(id);
                return "Item removed!";
            } catch (SQLException e) {
                System.out.println("Purchase:: Result set error");
            }
        }
        return "Error removing item, please try again!";
    }
    //To add the item to cart
    public String addToCart(Session session, int id){
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
    public synchronized String purchaseItems(Session session){
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
    public List<String> displayCart(Session session){
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
