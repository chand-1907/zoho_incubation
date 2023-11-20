import java.util.Arrays;
import java.util.Comparator;

public class Problem4 {
	
	public static void main (String args[]) {
		
		int arr[][] = {{1, 3}, {-2, 2}};
		int k = 1;
		
		Arrays.sort(arr, Comparator.comparing(p -> p[0] * p[0] + p[1] * p[1]));
		
		for (int i=0; i<k; i++)
			System.out.print(Arrays.toString(arr[i]) + " ");
	}
}