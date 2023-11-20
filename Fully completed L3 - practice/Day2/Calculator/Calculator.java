import java.util.Scanner;

// Helper classes

class Percentage {
	public void percentageCLI () {
		
		System.out.print("------------------------------------------------------------------\n");
		System.out.print("1-to Calculate X percentage of Y\n");
		System.out.print("2-to Calculate X is what percentage of Y\n");
		System.out.print("3-to Percentage Increase\n");
		System.out.print("4-to Percentage Decrease\n");
		System.out.print("5-to Exit\n");
		System.out.print("Enter your choice : ");
		int option = Machine.selectOption();
		
		switch (option) {
			case 1 : per1();
			break;
			
			case 2 : per2();
			break;
			
			case 3 : per3();
			break;
			
			case 4 : per4();
			break;
			
			case 5 : return;
			
			default : System.out.println("The selection is invalid!\nTry again");
		}
		System.out.println("");
		percentageCLI();
	}
	
	public void per1 () {
		int arr[] = Machine.getInputs();
		
		double formula = (arr[0] * arr[1]) / 100;
		
		System.out.println("Your result is : " + formula);
	}
	
	public void per2 () {
		int arr[] = Machine.getInputs();
		
		double formula = (arr[1] / arr[0]) * 100;
		
		System.out.println("Your result is : " + formula);
	}
	
	public void per3 () {
		int arr[] = Machine.getInputs();
		int x = arr[0], y = arr[1];
		
		double formula = ((x - y) / y ) * 100;
		
		System.out.println("Your result is : " + formula);
	}
	
	public void per4 () {
		int arr[] = Machine.getInputs();
		int x = arr[0], y = arr[1];
		
		double formula = ((y - x) / y) * 100;
		
		System.out.println("Your result is : " + formula);
	}
}

class Machine {
	
	private String name;
	private static Scanner sc;
	private Percentage per;
	
	private void init () {
		sc = new Scanner(System.in);
	}
	
	public Machine () {
		name = "CASIO";
		init();
	}
	
	public Machine (String name) {
		this.name = name;
		init();
	}
	
	public void start () {
		CLI_1();
	}
	
	public void CLI_1 () {
		
		System.out.println("###################################################################################");
		System.out.print("1-to Addition\n");
		System.out.print("2-to Subraction\n");
		System.out.print("3-to Multiplication\n");
		System.out.print("4-to Division\n");
		System.out.print("5-to Percentage\n");
		System.out.print("6-to Exit\n");
		System.out.print("Enter your option : ");
		
		int option = selectOption();
		
		switch (option) {
			case 1 : add();
			break;
			
			case 2 : sub();
			break;
			
			case 3 : mul();
			break;
			
			case 4 : div();
			break;
			
			case 5 : percentage();
			break;
			
			case 6 : return;
			
			default : System.out.println("The selection is invalid!\nTry again");
		}
		System.out.println("");
		CLI_1();
	}
	
	public static int[] getInputs () {
		int arr[] = new int[2];
		
		System.out.print("Enter Num-1 : ");
		arr[0] = selectOption();
		
		System.out.print("Enter Num-2 : ");
		arr[1] = selectOption();
	
		return arr;
	}
	
	public void add () {
		
		System.out.println("\n++++++++++++++++++++++++++++++++++");
		int arr[] = getInputs();
		System.out.println("++++++++++++++++++++++++++++++++++");
		System.out.println("Your result : " + (arr[0] + arr[1]));
	}
	
	public void sub () {
		System.out.println("\n----------------------------------");
		int arr[] = getInputs();
		System.out.println("----------------------------------");
		System.out.println("Your result : " + (arr[0] - arr[1]));
	}
	
	public void mul () {
		System.out.println("\n**********************************");
		int arr[] = getInputs();
		System.out.println("**********************************");
		System.out.println("Your result : " + (arr[0] * arr[1]));
	}
	
	public void div () throws ArithmeticException {
		System.out.println("\n//////////////////////////////////");
		int arr[] = getInputs();
		System.out.println("//////////////////////////////////");
		System.out.println("Your result : " + (arr[0] / arr[1]));
	}
	
	public void percentage () {
		if (per == null)
			per = new Percentage();
		per.percentageCLI();
	}
	
	public static int selectOption () throws NumberFormatException {
		return Integer.parseInt(sc.next());
	}
}

public class Calculator {
	public static void main (String args[]) {
		new Machine().start();
	}
}