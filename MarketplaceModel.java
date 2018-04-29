import java.io.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Honor Pledge:
 * I pledge that I have neither given nor received any help on this assignment.
 * ahpatil
 *
 */
// This is the model class for all the actions to perfomed by the marketplace
public class MarketplaceModel extends UnicastRemoteObject implements Marketplace{
	public String name="";
	File file = new File("items.txt");
	public MarketplaceModel(String name) throws RemoteException {
		super(); 
		this.name = name;
	}
	// For Admin Authentication
	@Override
 	public String AuthenticateAdmin(String username, String password){
		if(username.equalsIgnoreCase("admin")&&password.equalsIgnoreCase("admin"))
			return "admin";
		return "";
 	}
	// For Customer Authentication
	@Override
	public String AuthenticateCust(String username, String password){
		if(username.equalsIgnoreCase("customer")&&password.equalsIgnoreCase("customer"))
			return "customer";
		return "";
 	}
	// To create session
	@Override
	public Session create(String role){
		Session session = new Session(role);
		return session;
 	}
	//To browse the items
	@Override
	public List<String> browse(Session session) throws RemoteException{
		List<String> items = new ArrayList<>();
		String readline;
		int i = 0;
		if(file.exists()){
			try{
				FileReader fileReader = new FileReader(file);
				BufferedReader br = new BufferedReader(fileReader);
				// Reading line by line from the file
				readline = br.readLine();
				// To store the data from file to list
				while(readline!=null){
					items.add(i,readline);
					i++;
					readline = br.readLine();
				}
				fileReader.close();
				br.close();
			}catch (Exception e){
				System.out.println("File Reading error:");
			}
		}
		return items;
	}
	//To purchase the items
	@Override
	public String purchase(Session session, int id){
		boolean flag = true;
		String readline;
		if(file.exists()) {
			try {
				//For Reading file
				FileReader fileReader = new FileReader(file);
				BufferedReader br = new BufferedReader(fileReader);
				// Reading line by line from the file
				readline = br.readLine();
				//For Writing file
				FileWriter fileWriter = new FileWriter(file);
				BufferedWriter bw = new BufferedWriter(fileWriter);
				while (readline != null) {
						String[] item = readline.split(",");
						// item to be purchased
						if(Integer.parseInt(item[0])==(id)) {
							// checking if item is in the stock
							if(Integer.parseInt(item[4])>0){
								//updating quantity
								int quantity = Integer.parseInt(item[4]) -1;
								item[4] = String.valueOf(quantity);
								String purchasedItem = item[0]+","+item[1]+","+item[2]+","+item[3]+","+item[4];
								bw.write(purchasedItem+ "\n");
							}else
							flag = false;
						}else {
							bw.write(readline+ "\n");
						}
					readline = br.readLine();
				}
				fileReader.close();
				br.close();
				bw.flush();
				bw.close();
			} catch (Exception e) {
				System.out.println("File error:");
			}
		}
		//for out of stock
		if (!flag)
			return "Item out of stock, sorry for the inconvenience!";
		return "Item purchased!";
	}
	//To add the items
	@Override
	public String add(Session session, String[] item){
		if(item!=null) {
			String ItemToBeAdded = item[0]+","+item[1]+","+item[2]+","+item[3]+","+item[4];
			if (file.exists()) {
				try {
					FileWriter fileWriter = new FileWriter(file, true);
					fileWriter.write("\n"+ItemToBeAdded);
					fileWriter.flush();
					fileWriter.close();
				} catch (Exception e) {
					System.out.println("File writing error:");
				}
			}
			return "Item added!";
		}
		return "Error adding item, please try again!";
	}
	}