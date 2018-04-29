import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * Honor Pledge:
 * I pledge that I have neither given nor received any help on this assignment.
 * ahpatil
 *
 */
public class DatabaseConnection {
    private static Connection connection;
    private ResultSet resultSet;
    // To connect to database
    public void connectDb() {
        String userName = "ahpatil";
        String userPassword = "ahpatil";
        String databaseUrl = "jdbc:mysql://localhost:3306/ahpatil_db";
        try {
            connection = (Connection) DriverManager.getConnection (databaseUrl, userName, userPassword);
            System.out.println("Database connection successful!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // To get userInfo
    public ResultSet getUserInfo() throws SQLException {
        Statement stmt = null;
        // If connection is established
        if (connection != null) {
            // To get the username and password
            String query = "SELECT * FROM users";
            try {
                stmt = connection.createStatement();
                resultSet = stmt.executeQuery(query);
                return resultSet;
            } catch (SQLException e) {
                e.printStackTrace();
            } 
        }
        // If connection isn't established
        System.out.println("Database connection unsuccessful!");
        return null;
    }
    // To register new user
    public boolean registerUser(String username, String password, String userType) throws SQLException {
        Statement stmt = null;
        // If connection is established
        if (connection != null) {
            // To add the username and password of user
            String query = "INSERT INTO users(username,password,userType) "+"VALUES('"+username+"','"+password+"','"+userType+"')";
            try {
                stmt = connection.createStatement();
                stmt.executeUpdate(query);
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        // If connection isn't established
        System.out.println("Database connection unsuccessful!");
        return false;
    }
    // To get item list
    public ResultSet browse() throws SQLException {
        Statement stmt = null;
        // If connection is established
        if (connection != null) {
            // To get data from table item
            String query = "SELECT * FROM items";
            try {
                stmt = connection.createStatement();
                resultSet = stmt.executeQuery(query);
                return resultSet;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else {
            // If connection isn't established
            System.out.println("Database connection unsuccessful!");
        }
        return null;
    }
    // To get a specific item based on the item id
    public ResultSet getItem(int id) throws SQLException {
        Statement stmt = null;
        // If connection is established
        if (connection != null) {
            // To get data of item whose id is selected
            String query = "SELECT * FROM items WHERE itemID="+id;
            try {
                stmt = connection.createStatement();
                resultSet = stmt.executeQuery(query);
                return resultSet;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else {
            // If connection isn't established
            System.out.println("Database connection unsuccessful!");
        }
        return null;
    }
    // To update the quantity after purchase
    public void updateQuantity(int quantity, int id) throws SQLException {
        Statement stmt = null;
        // If connection is established
        if (connection != null) {
            // To update the data for item purchased
            String query = "UPDATE items SET itemQuantity="+quantity+" WHERE itemID="+id;
            try {
                stmt = connection.createStatement();
                stmt.executeUpdate(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else {
            // If connection isn't established
            System.out.println("Database connection unsuccessful!");
        }
    }
    // To add item to the item list
    public boolean add(String[] item) throws SQLException {
        Statement stmt = null;
        // If connection is established
        if (connection != null) {
            // To add the item
            String query = "INSERT INTO items(itemID,itemName,itemDescription,itemPrice,itemQuantity) VALUES('"+Integer.parseInt(item[0])+"','"+item[1]+"','"+item[2]+"','"+Double.valueOf(item[3])+"','"+Integer.parseInt(item[4])+"')";
            try {
                stmt = connection.createStatement();
                stmt.executeUpdate(query);
				return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else {
            // If connection isn't established
            System.out.println("Database connection unsuccessful!");
        }
		return false;
    }
    // To update the item list based on item id
    public boolean UpdateItem(String[] item, int id) throws SQLException {
        Statement stmt = null;
        // If connection is established
        if (connection != null) {
            // To add the item
            String query = "UPDATE items SET itemName='"+item[1]+"',itemDescription='"+item[2]+"',itemPrice='"+Double.valueOf(item[3])+"',itemQuantity='"+Integer.parseInt(item[4])+"' WHERE itemID="+id;
            try {
                stmt = connection.createStatement();
                stmt.executeUpdate(query);
				return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else {
            // If connection isn't established
            System.out.println("Database connection unsuccessful!");
        }
		return false;
    }
    // To remove item from the item list based on id
    public boolean RemoveItem(int id) throws SQLException {
        Statement stmt = null;
        // If connection is established
        if (connection != null) {
            // To delete the item
            String query = "DELETE FROM items WHERE itemID="+id;
            try {
                stmt = connection.createStatement();
                stmt.executeUpdate(query);
				return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else {
            // If connection isn't established
            System.out.println("Database connection unsuccessful!");
        }
		return false;
    }
    // To add item to the cart
    public void addToCart(int id, String username) throws SQLException{
        Statement stmt = null;
        // If connection is established
        if (connection != null) {
            // To add the item to the cart
            String query = "INSERT INTO cart(username,itemId) VALUES('"+username+"','"+id+"')";
            try {
                stmt = connection.createStatement();
                stmt.executeUpdate(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else {
            // If connection isn't established
            System.out.println("Database connection unsuccessful!");
        }
    }
    // To display the items in the cart
    public ResultSet displayCart(String username) throws SQLException{
        Statement stmt = null;
        // If connection is established
        if (connection != null) {
            // To get display the cart for that user
            String query = "SELECT items.itemID,items.itemName,items.itemDescription,items.itemPrice,items.itemQuantity FROM items INNER JOIN cart WHERE items.itemID = cart.itemId AND cart.username='"+username+"'";
            try {
                stmt = connection.createStatement();
                resultSet = stmt.executeQuery(query);
                return resultSet;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else {
            // If connection isn't established
            System.out.println("Database connection unsuccessful!");
        }
        return null;
    }

    public void updateCart(int quantity, int id, String username) {
        Statement stmt = null;
        // If connection is established
        if (connection != null) {
            // To update the quantity and delete the item from cart
            String query1 = "UPDATE items SET itemQuantity="+quantity+" WHERE itemID="+id;
            String query2 = "DELETE FROM cart WHERE username='"+username+"' AND ItemId="+id;
            try {
                stmt = connection.createStatement();
                stmt.executeUpdate(query1);
                stmt.executeUpdate(query2);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else {
            // If connection isn't established
            System.out.println("Database connection unsuccessful!");
        }
    }
}