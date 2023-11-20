import java.util.*;

public class BoggleBoard {
	
	public static void main (String args[]) {
		
		char[][] board = {
                {'M', 'S', 'E'},
                {'R', 'A', 'T'},
                {'L', 'O', 'N'}
        };
		
		Set<String> s = new HashSet<>();
		s.add("STAR");
		s.add("NOTE");
		s.add("SAND");
		s.add("STONE");
		
		System.out.println(boggleBoard(board, s));
	
	}
	
	public static boolean isValid (boolean visit[][], int i, int j) {
		return (i >= 0 && i < visit.length && j >= 0 && j < visit[0].length) && !visit[i][j];
	}
	
	public static void searchBoggle (char[][] board, boolean visit[][], Set<String> words, Set<String> result, int r, int c, String path) {
		
		visit[r][c] = true;
		
		path += board[r][c];
		
		if (words.contains(path))
			result.add(path);
	
		for (int i=-1; i<=1; i++) {
			for (int j = -1; j<=1; j++) {
				if (isValid(visit, r + i, c + j)) {
					searchBoggle(board, visit, words, result, (r + i), (c + j), path);
				}
			}
		}
		visit[r][c] = false;
	}
	
	public static Set<String> boggleBoard (char[][] board, Set<String> words) {
		
		Set<String> result = new HashSet<>();
		
		if (board.length == 0) return result;
		
		int m = board.length;
		int n = board[0].length;
		
		boolean visit[][] = new boolean[m][n];
		
		for (int i=0; i<m; i++) {
			for (int j = 0; j<n; j++) {
				searchBoggle(board, visit, words, result, i, j, "");
			}
		}
		return result;
	}
}