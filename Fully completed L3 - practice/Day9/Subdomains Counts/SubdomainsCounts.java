import java.util.*;

public class SubdomainsCounts { 
	
	public static void main (String args[]) {
		
		// String cpdomains[] = {"9001 discuss.ashwin.com"};
		String cpdomains[] = {"900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"};
	
		Map<String, Integer> mp = new HashMap<>();
		
		for (String s : cpdomains) {
			
			int i = 0;
			int num = 0;
			int n = s.length();
			boolean flag = true;
			Stack<Integer> stk = new Stack<>();
			
			while (i < s.length()) {
				if (s.charAt(i) == ' ') flag = false;

				if (flag)
					num = num * 10 + (s.charAt(i) - '0');
				
				if (i != 0 && (!flag && s.charAt(i - 1) == ' ' || s.charAt(i - 1) == '.'))
					stk.push(i);
				i++;
			}
			
			while (!stk.isEmpty()) {
				int ind = stk.pop();
				String sub = s.substring(ind, n);
				if (!mp.containsKey(sub))
					mp.put(sub, 0);
				mp.replace(sub, mp.get(sub) + num);
			}
		}
		System.out.println(mp);
	}
}