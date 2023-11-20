import java.util.*;
import java.io.*;

public class FoodOrderingSystem {
	
	public static void main (String args[]) throws Exception {
		
		FoodOrdering.getInstance().CLI();
	}
}

class FoodOrdering {
		
	private static FoodOrdering oneInstance;
	private Map<Integer, User> userMap;
	private Map<Integer, Restaurant> restaurantMap;
	private Map<Integer, Order> orderMap;
	private BufferedReader br;
	private FileReader fr;
	private FileWriter fw;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private File file;
	
	private final String ADMIN_USERNAME = "admin";
	private final String ADMIN_PASSWORD = "admin";
	private int userId;
	private int orderId;
	private int restaurantId;
	
	private FoodOrdering () throws Exception {
		this.br = new BufferedReader(new InputStreamReader(System.in));
		this.oos = null;
		this.ois = null;
		this.file = null;
		
		// fetch the data from the file named numbers
		this.userId = getNumbers()[0];
		this.orderId = getNumbers()[1];
		this.restaurantId = getNumbers()[2];
		
		// data structures get from the files
		this.userMap = getUserMap();
		this.restaurantMap = getRestaurantMap();
		this.orderMap = getOrderMap();
		
		// add the default foods in the restaurant map
		defaultFoodAdder(restaurantMap);
	}
	
	public static FoodOrdering getInstance () throws Exception {
		
		if (oneInstance == null)
			oneInstance = new FoodOrdering();
		return oneInstance;
	}
	
	private void defaultFoodAdder (Map<Integer, Restaurant> mp) {
		
		if (mp.size() == 0) {
			// create a new restaurant with name, and add foods to it
			mp.put(restaurantId, new Restaurant(restaurantId, "Banana Leaf"));
			mp.get(restaurantId).foods.add(new Food("Dosa", 50));
			mp.get(restaurantId).foods.add(new Food("Idly", 15));
			mp.get(restaurantId).foods.add(new Food("Poori", 30));
			
			this.restaurantId++;
			
			mp.put(restaurantId, new Restaurant(restaurantId, "Ya Mohideen"));
			mp.get(restaurantId).foods.add(new Food("Chicken Biriyani", 180));
			mp.get(restaurantId).foods.add(new Food("Mutton Biriyani", 230));
			mp.get(restaurantId).foods.add(new Food("Chicken grill(Full)", 450));
			
			this.restaurantId++;
		}
	}
	
	private Map<Integer, Restaurant> getRestaurantMap () throws Exception {
		
		Map<Integer, Restaurant> mp = new HashMap<>();
		file = new File("RestaurantMap.txt");
		
		if (file.exists() && file.length() != 0) {
			ois = new ObjectInputStream(new FileInputStream(file));
			mp = (Map<Integer, Restaurant>)ois.readObject();
			ois.close();
		}
		else if (!file.exists())
			file.createNewFile();
		
		file = null;
		ois = null;
		return mp;
	}
	
	private Map<Integer, User> getUserMap () throws Exception {
		
		Map<Integer, User> mp = new HashMap<>();
		file = new File("UserMap.txt");
	
		if (file.exists() && file.length() != 0) {
			// file is exist and the file has a object content, determine by the length by 0
			ois = new ObjectInputStream(new FileInputStream(file));
			mp = (Map<Integer, User>)ois.readObject();
			ois.close();
		}
		else if (!file.exists())
			file.createNewFile();
		
		file = null;
		ois = null;
		return mp;
	}
	
	private Map<Integer, Order> getOrderMap () throws Exception {
		
		Map<Integer, Order> mp = new HashMap<>();
		file = new File("OrderMap.txt");
		
		if (file.exists() && file.length() != 0) {
			ois = new ObjectInputStream(new FileInputStream(file));
			mp = (Map<Integer, Order>)ois.readObject();
			ois.close();
		}
		else if (!file.exists())
			file.createNewFile();
		
		file = null;
		ois = null;
		return mp;
	}
	
	private int[] getNumbers () throws Exception {
		
		int arr[] = {1001, 10001, 101};
		file = new File("Numbers.txt");
		
		if (file.exists() && file.length() != 0) {
			// file is exist and the file has a object content, determine by the length by 0
			ois = new ObjectInputStream(new FileInputStream(file));
			arr = (int[])ois.readObject();
			ois.close();
		}
		else if (!file.exists())
			file.createNewFile();
		
		file = null;
		ois = null;
		return arr;
	}
	
	private void signUp (String name, String password) {
		userMap.put(userId, new User(userId, name, password));
		System.out.println("Account is successfully created\nYour user id is : " + userId);
		userId++;
	}
	
	private void login (int userId, String password) throws Exception {
		
		User user = userMap.get(userId);
		
		if (user != null && user.password.equals(password)) {
			System.out.println("Successfully login");
			CLI2(user);
		}
		else {
			System.out.println("User-Id or Password is incorrect\nTry again");
		}
	}
	
	public void CLI () throws Exception {
		
		while (true) {
			
			System.out.println("------------------------------------------------------------------");
			System.out.println("1 -> Login : ");
			System.out.println("2 -> Sign-Up : ");
			System.out.println("3 -> Exit : ");
			System.out.print("Enter your choice : ");
			
			int choice = Integer.parseInt(br.readLine());
			
			switch (choice) {
				
				case 1 : {
					
					System.out.print("Enter your user-ID : ");
					int userId = Integer.parseInt(br.readLine());
					
					System.out.print("Enter your password : ");
					String password = br.readLine();
					
					login(userId, password);
					
					break;
				}
				
				case 2 : {
					
					System.out.print("\nEnter your name : ");
					String name = br.readLine();
					
					System.out.print("Enter password : ");
					String password = br.readLine();
					
					signUp(name, password);
					
					break;
				}
				
				case 3 : {
					
					// when the program is end then commit the all datastrctures into files
					
					oos = new ObjectOutputStream(new FileOutputStream(new File("UserMap.txt")));
					oos.writeObject(userMap);
					oos.close();
					
					oos = new ObjectOutputStream(new FileOutputStream(new File("RestaurantMap.txt")));
					oos.writeObject(restaurantMap);
					oos.close();
					
					oos = new ObjectOutputStream(new FileOutputStream(new File("OrderMap.txt")));
					oos.writeObject(orderMap);
					oos.close();
					
					oos = new ObjectOutputStream(new FileOutputStream(new File("Numbers.txt")));
					oos.writeObject(new int[] {userId, orderId, restaurantId});
					oos.close();
					
					System.out.println("\nBye");
					return;
				}
				
				default : System.out.println("Wrong input");
			}
		}
	}
	
	private void CLI2 (User user) throws Exception {
		
		System.out.println("_____Welcome_" + user.name + "_____");
		
		while (true) {
			
			System.out.println("------------------------------------------------------------------");
			System.out.println("1 -> Order food : ");
			System.out.println("2 -> Cancel food : ");
			System.out.println("3 -> Order History : ");
			System.out.println("4 -> Exit : ");
			System.out.print("Enter your choice : ");
			
			int choice = Integer.parseInt(br.readLine());
			
			switch (choice) {
				
				case 1 : {
					
					boolean loopControl = true;
					
					System.out.println("ID	Name");
					
					for (Restaurant rs : restaurantMap.values())
						System.out.println(rs);
					
					
					System.out.print("\nPick a id to list that particular restaurant food : ");
					
					int id = Integer.parseInt(br.readLine());
					Restaurant restaurant = restaurantMap.get(id);
					
					if (restaurant != null) {
						// the restaurant is exist in the map
						restaurant.listFood();
						
						System.out.print("\nEnter a food id to order a food : ");
						int foodId = Integer.parseInt(br.readLine());
						
						orderFood(user, restaurant, foodId);
					}
					else {
						// restaurant not exists
						System.out.println("Restaurant is not exist\nTry again");
					}
					
					break;
				}
				
				case 2 : {
					
					System.out.print("Enter the order id : ");
					int id = Integer.parseInt(br.readLine());
					
					cancelFood(user, id);
					
					break;
				}
				
				case 3 : {
					fetchOrderHistory(user);
					break;
				}
				
				case 4 : {
					System.out.println("Return to login");
					return;
				}
				
				default : System.out.println("Wrong input");
			}
		}
	}
	
	private void cancelFood (User user, int orderId) {
		Order order = orderMap.get(orderId);
		
		if (order != null && user.orderSet.contains(orderId)) {
			order.cancel = true;
			System.out.println("Your order cancel successfully");
		}
		else {
			System.out.println("Cannot find the order");
		}
	}
	
	private void orderFood (User user, Restaurant res, int id) throws Exception {
		user.orderSet.add(orderId);
		orderMap.put(orderId, new Order(orderId, user.userId, user.name, res.id, res.foods.get(id - 1).food, res.foods.get(id - 1).price, "ORDER"));
		System.out.println("Your order Id is : " + orderId);
		orderId++;
	}
	
	private void fetchOrderHistory (User user) {
		for (Order o : orderMap.values()) {
			if (o.userId == user.userId) {
				System.out.println(o);
				// if the food is canceled
				System.out.println(o.cancel());
			}
		} 
	}
}

class User implements Serializable {
	
	int userId;
	String name, password;
	Set<Integer> orderSet;
	
	public User (int userId, String name, String password) {
		this.userId = userId;
		this.name = name;
		this.password = password;
		orderSet = new HashSet<>();
	}
	
	public String toString () {
		return userId + "	" + name + "	" + password;
	}
}

class Restaurant implements Serializable {
	
	int id;
	String name;
	List<Food> foods;
	
	public Restaurant (int id, String name) {
		this.id = id;
		this.name = name;
		this.foods = new ArrayList<>();
	}
	
	public String toString () {
		return id + "	" + name;
	}
	
	public void listFood () {
		
		int id = 1;
		
		for (Food f : foods)
			System.out.println((id++) + "	" + f);
	}
}

class Order implements Serializable {
	
	int orderId, userId, restaurantId, price;
	String user, oc, food;
	boolean cancel;
	
	public Order (int orderId, int userId, String user, int restaurantId, String food, int price, String oc) {
		
		this.orderId = orderId;
		this.userId = userId;
		this.user = user;
		this.restaurantId = restaurantId;
		// this.restaurant = restaurant;s
		this.food = food;
		this.price = price;
		this.oc = oc;
		this.cancel = false;
	}
	
	public String toString () {
		return orderId + "	" + userId + "	" + user + "	" + restaurantId + "	" + food + "	" + price + "	" + oc;
	}
	
	public String cancel () {
		if (cancel)
			return orderId + "	" + userId + "	" + user + "	" + restaurantId + "	" + food + "	" + price + "	" + "CANCEL";
		return "";
	}
}

class Food implements Serializable {
	
	String food;
	int price;
	
	public Food (String food, int price) {
		this.food = food;
		this.price = price;
	}
	
	public String toString () {
		return food + "	" + price;
	}
}