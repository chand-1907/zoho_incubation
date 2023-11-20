import java.util.*;

class BillGenarator {
	
	Scanner sc;
	public BillGenarator () {
		sc = new Scanner(System.in);
	}
	
	public void start () {
		CLI();
	}
	
	public void CLI () {
		System.out.println("-------------------------------------------------------------------------------------------------------");
		System.out.println("1-to Domestic Calculation : ");
		System.out.println("2-to Comercial Calculation : ");
		System.out.print("Enter your choice : ");
		
		int choice = Integer.parseInt(sc.next());
		
		switch (choice) {
			case 1 : domestic();
			break;
			
			case 2 : comercial();
			break;
			
			default : System.out.println("Wrong input");
		}
		CLI();
	}
	
	public void domestic () {
		System.out.print("\nEnter the unit : ");
		double unit = Integer.parseInt(sc.next());
		double rate = 0;
		
		if (unit < 101) {
			System.out.println("You had no bill\nBecause you consume under 100 Units");
			return;
		}
		else if (unit < 201) {
			rate = 2.5;
		}
		else if (unit < 351) {
			rate = 3.5;
		}
		else if (unit < 501) {
			rate = 6.0;
		}
		else
			rate = 9.0;
		
		System.out.println("\nYour Electric Bill is : " + (rate * unit));
	}
	
	public void comercial () {
		System.out.print("\nEnter the unit : ");
		double unit = Integer.parseInt(sc.next());
		double rate = 0;
		
		if (unit < 301) {
			rate = 5.0;
		}
		else if (unit < 701) {
			rate = 10.0;
		}
		else if (unit < 1501) {
			rate = 30.0;
		}
		else 
			rate = 45.0;
		
		System.out.println("\nYour Electric Bill is : " + (rate * unit));
	}
}

public class ElectricBill {
	public static void main (String args[]) {
		new BillGenarator().start();
	}
}