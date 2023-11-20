public class Problem2 {
	
	public static void main (String args[]) {
		
		String str = "cac";
		
		wraproundString(str);
		
	}
	
	public static void contiguousOnly (String str) {
		int finalRes = 0, curRes = 0;
		
		for (int i=0; i<str.length(); i++) {
			
			if (i > 0 && (str.charAt(i) - str.charAt(i - 1) == 1 || Math.abs(str.charAt(i) - str.charAt(i - 1)) == 25)) {
				curRes++;
			}
			else {
				finalRes = Math.max(finalRes, curRes);
				curRes = 1;
			}
		}
		
		System.out.println((finalRes * (finalRes + 1) / 2));
	}
	
	public static void wraproundString(String p) {
        
		int arr[] = new int[26];
		
		int curRes = 0;
		
		for (int i=0; i<p.length(); i++) {
			
			if (i > 0 && (p.charAt(i - 1) - p.charAt(i) == 1 || p.charAt(i - 1) - p.charAt(i) == 25))
				curRes++;
			else
				curRes = 1;
			
			int ind = p.charAt(i) - 'a';
			arr[ind] = Math.max(arr[ind], curRes);
		}
		
		int sum = 0;
		for (int i : arr)
			sum += i;
		
		System.out.println(sum);
	}
}