public class Problem1 {
	
	public static void main (String args[]) {
		String grid[][] = {
			// {"o","a","a","n"},{"e","t","a","e"},{"i","h","k","r"},{"i","f","l","v"}
				{"a","b"},{"c","d"}
		};
		
		// String arr[] = {"oath","pea","eat","rain"};
		String arr[] = {"abcb"};
		
		int m = grid.length, n = grid[0].length;
		
		boolean visit[][] = new boolean[m][n];
		
		for (String s : arr) {
			
			for (int i=0; i<m; i++) {
				
				for (int j=0; j<n; j++) {
					
					if (s.startsWith(grid[i][j])) {
						dfs(grid, 0, s, i, j, visit);
					}
				}
			}
		}
	}
	
	public static boolean dfs (String grid[][], int i, String str, int x, int y, boolean[][] visit) {
		
		if (i == str.length()) {
			System.out.println(str);
			return true;
		}
		
		if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || str.charAt(i) != grid[x][y].charAt(0))
			return false;
		
		visit[x][y] = true;
		
		boolean f = false;
		
		f = dfs(grid, i + 1, str, x + 1, y, visit); 
		if (!f)
			f = dfs(grid, i + 1, str, x - 1, y, visit); 
		if (!f)
			f = dfs(grid, i + 1, str, x, y + 1, visit); 
		if (!f)
			f = dfs(grid, i + 1, str, x, y - 1, visit); 
		visit[x][y] = false;
		return f;
	}
}