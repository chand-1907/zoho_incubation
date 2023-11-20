import java.util.Arrays;

public class BagOfTokens {
	
	public static void main (String args[]) {
		int tokens[] = {100,200,400,300,400};
		int p = 200;
		int score = 0;
		
		Arrays.sort(tokens);
		
		int i = 0, j = tokens.length - 1;
	
		while (i <= j) {
			if (p  >= tokens[i]) {
				p -= tokens[i++];
				score++;
			}
			
			else if (p + tokens[j] >= tokens[i] && score != 0 && i != j) {
				p += tokens[j--];
				score--;
			}
			
			else
				break;
		}
		
		System.out.println(score);
	}
}