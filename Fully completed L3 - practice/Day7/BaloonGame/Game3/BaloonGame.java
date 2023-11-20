import java.io.*;
import java.util.*;

public class BaloonGame {
	
	static String[][] grid;
	static int[] row;
	static int rowCount;
	static int mainCount;
	static Map<Integer, Set<String>> mp;
	static int ct;
	
	public static void main (String args[]) throws IOException {
		
		ct = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		mp = new HashMap<>();
		
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
			
			if (modifyGrid(col - 1, m, n, color)) {
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
		
		int st = 0;
		if ((m * n) == ct) {
			return true;
		}
		
		if (rowCount == m) {
			mainCount++;
			rowCount = 0;
		}
		
		if (grid[m - mainCount - 1][col].equals("-")) {
			grid[m - mainCount - 1][col] = color;
			row[col]++;
			rowCount++;
			st = col;
			if (!mp.containsKey(col))
				mp.put(col, new HashSet<>());
			mp.get(col).add(color);
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
			st = j;
			
			if (!mp.containsKey(j))
				mp.put(j, new HashSet<>());
			mp.get(j).add(color);
		}
		
		System.out.println(mp.get(st));
		
		if (m - mainCount == 1) {
			if (mp.get(st).size() == 1) {
				rowCount--;
				mainCount--;
				ct -= n;
				mp.get(st).clear();
				burst(st, m);
				return true;
			}
		}
		
		ct++;
		// System.out.println(Arrays.toString(row));
		return false;
	}
	
	public static void burst (int col, int m) {
		
		for (int i=0; i<m; i++) {
			grid[i][col] = "-";
		}
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