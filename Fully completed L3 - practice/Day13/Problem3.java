public class Problem3 {
	
	public static void main (String args[]) {
		
		int arr[] = {197, 130, 1};
		System.out.println(UTFEncoding(arr));		
	}
	
	public static boolean UTFEncoding (int arr[]) {
		
		int still = 0;
		
		for (int i : arr) {
				
			if (still == 0) {
				
				if ((i >> 5) == 0b110) still = 1;
				else if ((i >> 4) == 0b1110) still = 2;
				else if ((i >> 5) == 0b11110) still = 3;
				else if ((i >> 7) != 0) return false;
			}
			
			else {
				if ((i >> 6) != 0b10) return false;
				still--;
			}
		}
		return still == 0;
	}
}