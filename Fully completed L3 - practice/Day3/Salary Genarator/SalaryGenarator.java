import java.util.*;

class Employee {
	
	String name;
	int empID, age, netSalary;
	double basicSalary;
	static int id = 1001;
	
	public Employee (String name, int age, double basicSalary) {
		empID = id++;
		this.name = name;
		this.age = age;
		this.basicSalary = basicSalary;
	}
	
	public void updateSalary (int salary) {
		this.basicSalary = salary;
	}
	
	public String getEmployeeDetails () {
		return "\nEmployee ID : " + empID + "\nName : " + name + "\nAge : " + age + "\nBasic Salary : " + basicSalary + "\n";
	}
	
	public double getBasicSalary () {
		return basicSalary;
	}
}

class EmployeeManager {
	private static EmployeeManager oneInstance = null;
	private HashMap<Integer, Employee> map;
	
	private EmployeeManager () {
		map = new HashMap<>();
		map.put(Employee.id, new Employee("Chandru", 20, 15000));
		map.put(Employee.id, new Employee("Prasanna", 21, 35000));
		map.put(Employee.id, new Employee("Udhaya Kumar", 24, 60000));
	}
	
	public static EmployeeManager getInstance () {
		if (oneInstance == null)
			oneInstance = new EmployeeManager();
		return oneInstance;
	}
	
	public Employee getEmployee (int id) {
		if (!map.containsKey(id))
			return null;
		return map.get(id);
	}
	
	public void addEmployee (String name, int age, int basicSalary) {
		map.put(Employee.id, new Employee(name, age, basicSalary));
	}
}

class SalaryCalculator {
	
	EmployeeManager obj;
	Scanner sc;
	
	public SalaryCalculator () {
		obj = EmployeeManager.getInstance();
		sc = new Scanner(System.in);
	}
	
	public void start () {
		createInterface();
	}
	
	public void createInterface () {
		System.out.println("-------------------------------------------------------------------------------------------------------");
		System.out.println("1-to Enter your Employee ID to check the Net salary : ");
		System.out.println("2-to check freely Net salary : ");
		System.out.print("Enter your choice : ");
		
		int choice = Integer.parseInt(sc.next());
		
		switch (choice) {
			case 1 : operation1();
			break;
			
			case 2 : operation2();
			break;
			
			default : System.out.println("Wrong input");
		}
		
		createInterface();
	}
	
	public void operation1 () {
		System.out.print("\nEnter your Employee ID : ");
		int id = Integer.parseInt(sc.next());
		
		salaryCalculator(id);
	}
	
	public void operation2 () {
		System.out.print("\nEnter your Basic Salary : ");
		int salary = Integer.parseInt(sc.next());
		
		calculate(salary);
	}
	
	public void salaryCalculator (int empID) {
		
		Employee emp = obj.getEmployee(empID);
		
		if (emp == null) {
			System.out.println("Employee ID is not found\nTry again");
			return;
		}
		double basicSalary = emp.getBasicSalary();
	
		System.out.println(emp.getEmployeeDetails());
		
		calculate(basicSalary);
	}
	
	public void calculate (double basicSalary) {
		
		double PA, HRA, TA, PF, IT, others;
		
		HRA = 150;
		TA = 120;
		others = 450;
		PA = basicSalary * 12 / 100;
		
		// a)PF
		PF = basicSalary * 14 / 100;
		
		// b)IT
		IT = basicSalary * 15 / 100;
		
		double netSalary = basicSalary + PA + HRA + TA + others - (PF - IT);
		
		// System.out.println("Hello from Salary Calculator (:");
		System.out.println("Your Net Salary is : " + netSalary + "\n");		
	}
}

public class SalaryGenarator {
	
	public static void main (String args[]) {
		// System.out.println(new Employee("Chandru", 20, 15000).getEmployeeDetails());
		// System.out.println(new Employee("Naveen", 26, 65000).getEmployeeDetails());
		
		new SalaryCalculator().start();
	}
}