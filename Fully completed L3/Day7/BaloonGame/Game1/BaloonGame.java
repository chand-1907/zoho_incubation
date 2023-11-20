import java.io.*;

public class BaloonGame {
	
	static String[][] grid;
	
	public static void main (String args[]) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.print("Enter rows-M : ");
		int m = Integer.parseInt(br.readLine());
		
		System.out.print("Enter columns-N : ");
		int n = Integer.parseInt(br.readLine());
		
		grid = createGrid(m, n);
		
		System.out.println("\nWelcome to our baloon game :-");
		
		char c = 'y';
		
		while (c != 'n') {
			
			System.out.print("Enter the column number : ");
			int col = Integer.parseInt(br.readLine());
			
			if (col <= 0 || col > n) {
				System.out.println("column is out of bounds!");
				continue;
			}
			
			System.out.print("Enter the color of baloon : ");
			String color = br.readLine();
			
			if (modifyGrid(col - 1, color)) {
				displayGrid();
				break;
			}
			
			System.out.println("Contents of matrix : \n");
			displayGrid();
			
			System.out.print("Do you wish to continue the game (y/n) : ");
			c = br.readLine().charAt(0);
		}
	}
	
	public static void displayGrid () {
		
		for (String[] i : grid) {
			for (String s : i)
				System.out.print(s + "	");
			System.out.println("");
		}
	}
	
	public static boolean modifyGrid (int col, String color) {
		
		
		int i = grid.length - 1;
		for (; i >= 0; i--) {
			if (grid[i][col].equals("-"))
				break;
		}
		
		grid[i][col] = color;
		
		if (i == 0) {
			System.out.println("column is filled\nterminated");
			return true;
		}
		return false;
	}
	
	public static String[][] createGrid (int m, int n) {
		
		String[][] grid = new String[m][n];
		
		for (int i=0; i<m; i++)
			for (int j=0; j<n; j++)
				grid[i][j] = "-";
		
		return grid;
	}
}