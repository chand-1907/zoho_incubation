import java.io.*;
import java.util.*;

public class BaloonGame {
	
	static String[][] grid;
	static int[] row;
	static int rowCount;
	static int mainCount;
	
	public static void main (String args[]) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.print("Enter rows-M : ");
		int m = Integer.parseInt(br.readLine());
		
		System.out.print("Enter columns-N : ");
		int n = Integer.parseInt(br.readLine());
		
		grid = createGrid(m, n);
		
		System.out.println("\nWelcome to our baloon game :-");
		
		char c = 'y';
		int ct  = 0;
		
		while (c != 'n') {
			
			System.out.print("Enter the column number : ");
			int col = Integer.parseInt(br.readLine());
			
			if (col <= 0 || col > n) {
				System.out.println("column is out of bounds!");
				continue;
			}
			
			System.out.print("Enter the color of baloon : ");
			String color = br.readLine();
			
			ct++;
			
			if (modifyGrid(col - 1, m, n, color) || ct == (m * n)) {
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
	
	public static boolean modifyGrid (int col, int m, int n, String color) {
		
		if (rowCount == m) {
			mainCount++;
			rowCount = 0;
		}
		
		if (grid[m - mainCount - 1][col].equals("-")) {
			grid[m - mainCount - 1][col] = color;
			row[col]++;
			rowCount++;
		}
		
		else {
			int i=m - mainCount - 1;
			int j = 0;
			for (;j<n; j++) {
				if (row[j] == mainCount)
					break;
			}
			grid[i][j] = color;
			row[j]++;
			rowCount++;
		}
		
		// System.out.println(Arrays.toString(row));
		return false;
	}
	
	public static String[][] createGrid (int m, int n) {
		
		String[][] grid = new String[m][n];
		
		for (int i=0; i<m; i++)
			for (int j=0; j<n; j++)
				grid[i][j] = "-";
		
		row = new int[n];
		rowCount = 0;
		mainCount = 0;
		return grid;
	}
}