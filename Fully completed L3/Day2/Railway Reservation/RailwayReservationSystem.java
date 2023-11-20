import java.util.*;
import java.io.*;

/*
	1 - Booking
	2 - Availability checking
	3 - Cancellation
	4 - Prepare chart
*/

public class RailwayReservationSystem {
	
	public static void main (String args[]) throws IOException, ClassNotFoundException {
		
		RailwayReservation.getInstance().CLI();
	}
}

class RailwayReservation {
	
	private static RailwayReservation oneInstance;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private BufferedReader br;
	private Map<Integer, Passenger> passMap;
	private Queue<Passenger> waitQueue;
	
	private int passId;
	private int bookingId;
	private int seats;
	
	private RailwayReservation () throws IOException, ClassNotFoundException {
		this.oos = null;
		this.ois = null;
		this.br = new BufferedReader(new InputStreamReader(System.in));
		
		// get the already stored passengers id, booking id
		this.passId = getNumbers()[0];
		this.bookingId = getNumbers()[1];
		
		// getting the passengers map
		this.passMap = getPassengerMap();
		
		// getting the waiting passengers queue
		this.waitQueue = getWaitingQueue();
		
		this.seats = 3;
	}
	
	public static RailwayReservation getInstance () throws IOException, ClassNotFoundException {
		
		if (oneInstance == null)
			oneInstance = new RailwayReservation();
		
		return oneInstance;
	}
	
	private int[] getNumbers () throws IOException, ClassNotFoundException {
		
		int arr[] = {1, 1001};
		
		File newNumbersFile = new File("Numbers.txt");
		
		if (newNumbersFile.exists() && newNumbersFile.length() != 0) {
			ois = new ObjectInputStream(new FileInputStream(newNumbersFile));
			arr = (int[])ois.readObject();
			ois.close();
		}
		else if (!newNumbersFile.exists())
			newNumbersFile.createNewFile();
		
		ois = null;
		return arr;
	}
	
	private Map<Integer, Passenger> getPassengerMap () throws IOException, ClassNotFoundException {
		
		Map<Integer, Passenger> mp = new HashMap<>();
		File passengersMapFile = new File("PassengersMap.txt");
		
		if (passengersMapFile.exists() && passengersMapFile.length() != 0) {
			ois = new ObjectInputStream(new FileInputStream(passengersMapFile));
			mp = (Map<Integer, Passenger>) ois.readObject();
			ois.close();
		}
		else if (!passengersMapFile.exists())
			passengersMapFile.createNewFile();
		
		ois = null;
		return mp;
	}
	
	private Queue<Passenger> getWaitingQueue () throws IOException, ClassNotFoundException {
		
		Queue<Passenger> pass = new LinkedList<>();
		File waitingPassengersQueueFile = new File("WaitingQueue.txt");
		
		if (waitingPassengersQueueFile.exists() && waitingPassengersQueueFile.length() != 0) {
			ois = new ObjectInputStream(new FileInputStream(waitingPassengersQueueFile));
			pass = (Queue<Passenger>) ois.readObject();
			ois.close();
		}
		else if (!waitingPassengersQueueFile.exists())
			waitingPassengersQueueFile.createNewFile();
		
		ois = null;
		return pass;
	}
	
	private void book (String name, int age) {
		
		if (seats == passMap.size()) {
			// the seats are filled then add the passengers to the waiting queue
			waitQueue.add(new Passenger(passId, name, age));
			System.out.println("The seats are fill, so you are in the waiting queue");
		}
		
		else {
			// the seats are available
			passMap.put(passId, new Passenger(passId, name, age));
			System.out.println("The reservation is successfully done");
		}
		System.out.println("Your passenger ID is : " + passId);
		passId++;
	}
	
	private void cancel (int passId) {
		
		Passenger passenger = passMap.get(passId);
		
		if (passenger != null) {
			passMap.remove(passId);
			System.out.println("Your ticket is successfully droped");
			
			// if the queue is not empty then add one person to the main booking
			if (!waitQueue.isEmpty()) {
				Passenger p = waitQueue.remove();
				passMap.put(p.passId, p);
				System.out.println("Successfully one person moved to main list\nId is : " + p.passId);
			}
		}
		
		else {
			System.out.println("Invalid passenger-ID");
		}
	}
	
	public void prepareChart () {
		
		System.out.println("\npass-ID	Name	Age");
		System.out.println("\nBooked Tickets are : ");
		for (Passenger p : passMap.values()) {
			System.out.println(p);
		}
		
		System.out.println("\nWaiting Tickets are : ");
		for (Passenger p : waitQueue) {
			System.out.println(p);
		}
	}
	
	public void CLI () throws IOException {
		
		while (true) {
			
			System.out.println("---------------------------------------------");
			System.out.println("1 -> Booking : ");
			System.out.println("2 -> Availability checking : ");
			System.out.println("3 -> Cancellation : ");
			System.out.println("4 -> Prepare chart : ");
			System.out.println("5 -> Exit : ");
			System.out.print("Enter your choice : ");
			
			int choice = Integer.parseInt(br.readLine());
			
			switch (choice) {
				
				case 1 : {
					
					System.out.print("Enter name : ");
					String name = br.readLine();
					
					System.out.print("Enter age : ");
					int age = Integer.parseInt(br.readLine());
					
					book(name, age);
					
					break;
				}
				
				case 2 : {
					
					if (passMap.size() < seats) {
						System.out.println("Total available seats are : " + (seats - passMap.size()));
					}
					else {
						System.out.println("Seats are full");
						// System.out.println("Total available seats are : " + (seats - passMap.size()));
					}
					break;
				}
				
				case 3 : {
					
					System.out.print("\nEnter passenger Id : ");
					int n = Integer.parseInt(br.readLine());
					
					cancel(n);
					
					break;
				}
				
				case 4 : {
					prepareChart();
					break;
				}
				
				case 5 : {
					
					// write a all objects to the file
					// passMap
					// waitQueue
					// passId
					
					oos = new ObjectOutputStream(new FileOutputStream(new File("Numbers.txt")));
					oos.writeObject(new int[] {passId, bookingId});
					oos.close();
					
					oos = new ObjectOutputStream(new FileOutputStream(new File("PassengersMap.txt")));
					oos.writeObject(passMap);
					oos.close();
					
					oos = new ObjectOutputStream(new FileOutputStream(new File("WaitingQueue.txt")));
					oos.writeObject(waitQueue);
					oos.close();
					
					System.out.println("bye");
					return;
				}
				
				default : System.out.println("You were enter a wrong input");
			}
		}
	}
}

class Passenger implements Serializable {
	
	int passId, age;
	String name;
	
	public Passenger (int passId, String name, int age) {
		
		this.passId = passId;
		this.name = name;
		this.age = age;
	}
	
	public String toString () {
		return passId + "	" + name + "	" + age;
	}
}
