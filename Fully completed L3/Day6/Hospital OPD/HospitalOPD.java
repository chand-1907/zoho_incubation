import java.util.*;
import java.io.*;

public class HospitalOPD {
	public static void main (String args[]) throws IOException {
		new Hospial("Rajappa").CLI();
	}
}

class Hospial {
	
	String name;
	Map<String, Patient> patientMap;
	Queue<Patient> visitingQueue;
	Patient lastPatient = null;
	BufferedReader br;
	
	public Hospial (String name) {
		this.name = name;
		patientMap = new HashMap<>();
		visitingQueue = new LinkedList<>();
		br = new BufferedReader(new InputStreamReader(System.in));
		patientMap.put("8754153785", new Patient("Chandru", "8754153785"));
		patientMap.put("8220613338", new Patient("Naveen", "8220613338"));
		patientMap.put("7708644713", new Patient("Muniyandi", "7708644713"));
	}
	
	public void CLI () throws IOException {
		
		while (true) {
			
			System.out.println("-----------------------------------------------------");
			System.out.println("1 -> Enter into Hospital :");
			System.out.println("2 -> Visit patient OPD :");
			System.out.println("3 -> To check the visiting queue :");
			System.out.println("4 -> Exit :");
			System.out.print("Enter your choice : ");
			int choice = Integer.parseInt(br.readLine());
			
			switch (choice) {
				
				case 1 : {
					System.out.print("Enter your Phone-number : ");
					String phone = br.readLine();
					
					Patient patient = patientMap.get(phone); 
					
					if (patient != null) {
						greeting(patient);
					}
					
					else {
						System.out.println("It Seems you are not have an accout : \nCreate new account : ");
						System.out.print("Enter your name : ");
						String name = br.readLine();
					
						System.out.print("Enter your Phone-Number : ");
						String phoneNumber = br.readLine();
						
						if (!patientMap.containsKey(phoneNumber)) {
							patientMap.put(phoneNumber, new Patient(name, phoneNumber));
							System.out.println("Account is successfully created");
						}
						
						greeting(patientMap.get(phoneNumber));
					}
					break;
				}
				
				case 2 : {
					// out patient details
					BufferedReader b = new BufferedReader(new FileReader("HospitalInOut.txt"));
					
					System.out.println("\nIn Time		" + "1st Medic	 " + "2nd Medic	 " + "Doctor		" + "Out Time	" + "Waiting Time");
					
					String temp = "";
					
					while ((temp = b.readLine()) != null)
						System.out.println(temp);
					
					b.close();
					break;
				}
				
				case 3 : {
					
					System.out.print("Enter time to display the visiting queue : ");
					int time = Integer.parseInt(br.readLine());
					
					System.out.println("\nWaiting queue : ");
					
					for (Patient p : patientMap.values()) {
						if (!p.stk.isEmpty() && p.stk.peek()[0] > time) {
							System.out.println(p);
						}
					}
					
					break;
				}
				
				case 4 : {
					System.out.println("\nBye!");
					return;
				}
				
				default : System.out.println("You were entered a wrong input");
			}
		}
	}
	
	public void greeting (Patient patient) throws IOException {
		
		// add the patient to visiting queue
		visitingQueue.add(patient);
		
		System.out.println("_____Welcome_" + patient.getName() + "______");
		System.out.print("Enter your In-Time : ");
		int inTime = Integer.parseInt(br.readLine());
		
		System.out.print("Enter your Doctor-Visiting-Time : ");
		int doctorVisitingTime = Integer.parseInt(br.readLine());
	
		gotoHospital(patient, inTime, doctorVisitingTime);
		
	}
	
	public void gotoHospital (Patient patient, int inTime, int doctorVisitingTime) throws IOException {
		
		int firstMedicFree = 0;
		int secondMedicFree = 0;
		int doctorFree = 0;
		int parmacyFree = 0;
		int outTime = 0;
		int waitingTime = 0;
		int currentWait = 0;
		
		if (lastPatient == null) {
			Integer[] calc = new Integer[6];
			calc[0] = inTime;
			calc[1] = inTime + 5;
			calc[2] = calc[1] + 5;
			calc[3] = calc[2] + doctorVisitingTime;
			calc[4] = calc[3] + 5;
			calc[5] = waitingTime;
			lastPatient = patient;
			patient.stk.push(calc);
			
			writeToFile(calc);
		}
		
		else {
			Integer[] lastArr = lastPatient.stk.peek();
			Integer[] arr = new Integer[6];
			
			// in time
			arr[0] = inTime; // 306
			
			// first medic free time
			firstMedicFree = lastArr[1] - inTime; // 310 - 306 = 4
			currentWait = firstMedicFree > 0 ? firstMedicFree : 0;
			waitingTime += currentWait;
			// waitingTime += firstMedicFree > 0 ? firstMedicFree : 0;
			
			arr[1] = arr[0] + currentWait + 5; // 306 + 4 + 5 = 315
			
			// second medic free
			secondMedicFree = lastArr[2] - arr[1]; // 315 - 315 = 0
			currentWait = secondMedicFree > 0 ? secondMedicFree : 0;
			waitingTime += currentWait;
			// waitingTime += secondMedicFree > 0 ? secondMedicFree : 0;
			
			arr[2] = arr[1] + currentWait + 5; // 315 + 0 + 5 = 320
			
			// doctor free time
			doctorFree = lastArr[3] - arr[2]; // 324 - 320 = 4
			currentWait = doctorFree > 0 ? doctorFree : 0;
			waitingTime += currentWait;
			// waitingTime += doctorFree > 0 ? doctorFree : 0;
			
			arr[3] = arr[2] + currentWait + doctorVisitingTime;
			
			// pharmacy free time
			parmacyFree = lastArr[4] - arr[3]; // 330 - 332 = -2
			currentWait = parmacyFree > 0 ? parmacyFree : 0;
			waitingTime += currentWait;
			// waitingTime += parmacyFree > 0 ? parmacyFree : 0;
			
			arr[4] = arr[3] + currentWait + 5;
			
			// total waitingTime
			arr[5] = waitingTime;
			
			patient.stk.push(arr);
			lastPatient = patient;
			
			writeToFile(arr);
			
			// for sorry to patient if they wait for sometime
			if (waitingTime != 0) {
				System.out.println("Sorry for your waiting to treat");
			}
		}
	}
	
	public void writeToFile (Integer[] arr) throws IOException {
		FileWriter fw = new FileWriter("HospitalInOut.txt", true);
		String temp = " " + arr[0] + "     	  " + arr[1] + "		  " + arr[2] + "		  " + arr[3] + "		  " + arr[4] + "		  " + arr[5] + "\n"; 
		fw.write(temp);
		fw.close();
	}
}

class Patient {
	
	String name;
	String phoneNumber;
	// int inTime;
	// int outTime;
	// int waitingTime;
	// int doctorVisitingTime;
	Stack<Integer[]> stk;
	
	public Patient (String name, String phoneNumber) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		// this.inTime = inTime;
		// this.doctorVisitingTime;
		this.stk = new Stack<>();
	}
	
	public String getName () {
		return name;
	}
	
	public String toString () {
		return name + "		" + phoneNumber;
	}
}